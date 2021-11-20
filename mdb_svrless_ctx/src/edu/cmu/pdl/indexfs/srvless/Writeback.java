package edu.cmu.pdl.indexfs.srvless;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;


import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class Writeback {
	
	private GetConfig config;
	
	// Commit upon a number of operations to a specific server or reach the limited time
	private static final int NumtoCommit = 1000;  // need to check IndexFS setting
	private static final int TimetoCommit = 3;
	
	private int[] op_count;
	
	public class operation_param {
		public int op_type;
		public KeyInfo_THRIFT key;
		public long ino;
	}
	
	
	private svrless_IndexFS_ctx mdb_svrless_ctx;
	// Server id to ip map
	private Map<Integer, String> server_map;
	// Server id to operation map
	private HashMap<Integer, List<operation_param>> op_map;
	
	// operation types
	public enum ops_indexfs {
		Mknod,
		Mkdir,
		Chmod,
		Chown,
		Getattr,
		AccessDir,
		ReadDir,
		Flush,
		NextInode
	}
	
	/* Constructor */
	public Writeback(GetConfig config) {
		this.config = config;
		mdb_svrless_ctx = new svrless_IndexFS_ctx();
		server_map = config.GetSrvMap();
		op_map = new HashMap<Integer, List<operation_param>>();
		initialize_map(config.GetSrvNum());
		op_count = new int[config.GetSrvNum()];
	}
	
	private void initialize_map(int srv_num) {
		for (int i = 0; i < srv_num; i++) {
			op_map.put(i, new ArrayList<operation_param>());
		}
	}

	private void empty_map(int server_id) {
		op_map.get(server_id).clear();
	}
	
	/* Start a session with a server to commit all operations to that server */
	private int commit(short server_id, int port) {
		try {
			TTransport socket = new TSocket(server_map.get((int)server_id),port);
			TProtocol protocol = new TBinaryProtocol(socket);
	    	MetaDBService.Client mdb_client = new MetaDBService.Client(protocol);
	    	socket.open(); 
	//    	long start = System.currentTimeMillis();
	    	for (int i = 0; i < NumtoCommit; i++) {
	    		operation_param op_param = op_map.get((int)server_id).get(i);
	    		int op_type = op_param.op_type;
	    		switch (op_type) {
	    			// mknod
	    			case 0:
	                	mdb_svrless_ctx.newFile(mdb_client, op_param.key);
	    				break;
	    			// mkdir
	    			case 1:
	    		        mdb_svrless_ctx.newDirectory(mdb_client, op_param.key, server_id, op_param.ino);
	    				break;
	    			// chmod
	    			case 3:
	    				break;
	    			// chown
	    			case 4:
	    				break;
	    			// getattr
	    			case 5:
	    				break;
	    			// accessdir
	    			case 6:
	    				break;	    
	    			// readdir
	    			case 7:
	    				break;
	    			// flush
	    			case 8:
	    	            mdb_svrless_ctx.Flush(mdb_client);
	    				break;
	    			case 9:
	    				// To be implemented
	    	            mdb_svrless_ctx.NextInode(mdb_client);
	    				break;	    				
	    		}
	    	}
	//        long finish = System.currentTimeMillis();
    //	      long timeElapsed = finish - start;
	//        System.out.println(timeElapsed); 
	        socket.close(); 
		}
		catch (TTransportException e) {
			e.printStackTrace();
		} 
		catch (TException e) {  
		      e.printStackTrace();  
		}
		return 0;
	}
	
	/* Insert write operations to the counter buffer and commit when reach the batch job size or reach time limitation. 
	 * Clear buffer after commit. */
	public int operation_counter(int server_id, operation_param op_param) {
		int ret = 0;
		if (server_id < 0) 
			server_id = ThreadLocalRandom.current().nextInt(0, config.GetSrvNum() + 1);
		
		if (op_map.get(server_id).size() < NumtoCommit) {
			op_map.get(server_id).add(op_param);
		}
		else {
			short server_id_ = server_id > Short.MAX_VALUE ? Short.MAX_VALUE : server_id < Short.MIN_VALUE ? Short.MIN_VALUE : (short)server_id;
			ret = commit(server_id_, config.zeroth_port);
			empty_map(server_id);
		}
		return ret;
	}
}
