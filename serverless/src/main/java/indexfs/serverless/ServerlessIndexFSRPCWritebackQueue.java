package main.java.indexfs.serverless;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.ThreadLocalRandom;


import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class ServerlessIndexFSRPCWritebackQueue {
	
	/**
	 * Serverless IndexFS configuration.
	 */
	private ServerlessIndexFSConfig config;
	
	/**
	 * Serverless IndexFS write-back cache capacity before commit.
	 */
	private int NumtoCommit;  
	
	/**
	 * Serverless IndexFS write-back cache commit time limit.
	 */
	private int TimetoCommit; 
	
	/**
	 * MetaDB RPC interface for serverless IndexFS.
	 */
	private ServerlessIndexFSCtx mdb_svrless_ctx;
	
	/**
	 * Server id to ip map. This is set to be public because it will be used in 
	 * srvless_IndexFS_server.NextInode().
	 */
	public Map<Integer, String> server_map;
	
	/**
	 * Server id to ip map. This is set to be public because it will be used in 
	 * srvless_IndexFS_server.NextInode().
	 */
	public Map<Integer, Integer> port_map;
	
	/**
	 * Server id to operation map
	 */
	private HashMap<Integer, ArrayList<ServerlessIndexFSOperationParameters>> op_map;
	
	private Timer timer;
	
	// operation types
	public enum ops_indexfs {
		Mknod,
		Mkdir,
		Chmod,
		Chown,
		Flush
	}
	
	/**
	 *  Constructor.
	 *  
	 *  @param config Configuration passed to the serverless IndexFS server.
	 */
	public ServerlessIndexFSRPCWritebackQueue(ServerlessIndexFSConfig config) {
		this.config = config;
		this.mdb_svrless_ctx = new ServerlessIndexFSCtx();
		this.server_map = config.GetMetaDBMap();
		this.port_map = config.GetPortMap();
		this.op_map = new HashMap<Integer, ArrayList<ServerlessIndexFSOperationParameters>>();
		initialize_map(config.GetMetaDBNum());
		this.NumtoCommit = config.NumtoCommit;
		this.TimetoCommit = config.TimetoCommit;
		
		// Timer for periodical commit.
//		timer = new Timer();
//		timer.schedule(new TimerTask() {
////		timer.scheduleAtFixedRate(new TimerTask() {
//		  @Override
//		  public void run() {
//			  for (short i = 0; i < config.GetMetaDBNum(); i++) {
//				try {
//				  commit(i, 10086);
//				} catch (IndexOutOfBoundsException e) {
//					e.printStackTrace();					
//				}
//				System.out.println("Reached write-back time limit. Commit to MetaDB server " + i);
//				empty_map(i);			  
//			  }
//		  }
//		}, TimetoCommit);
////		}, TimetoCommit, TimetoCommit);
	}
	
	private void initialize_map(int srv_num) {
		for (int i = 0; i < srv_num; i++) {
			op_map.put(i, new ArrayList<ServerlessIndexFSOperationParameters>());
		}
	}

	private void empty_map(int server_id) {
		op_map.get(server_id).clear();
	}
	
	/**
	 *  Start a session with a server to commit all operations to that server 
	 */
	private int commit(short server_id, int port) {
		int ret = 0;
//		System.out.println("Server ID: " + server_id + ": " + server_map.get((int)server_id) + ": " + port);
		try {
			System.out.println("ServerlessIndexFSRPCWritebackQueue: commit(): " + "commit to " + server_map.get((int)server_id) + ":" + port_map.get((int)server_id));
			TTransport socket = new TSocket(server_map.get((int)server_id),port_map.get((int)server_id));
			TProtocol protocol = new TBinaryProtocol(socket);
	    	MetaDBService.Client mdb_client = new MetaDBService.Client(protocol);
	    	socket.open(); 
	    	for (int i = 0; i < NumtoCommit; i++) {
//				System.out.println("start committing changes!");
	    		ServerlessIndexFSOperationParameters op_param = op_map.get((int)server_id).get(i);
	    		int op_type = op_param.op_type;
//				System.out.println("operation type: " + op_type);
	    		switch (op_type) {
	    			// mknod
	    			case 0:
	                	mdb_svrless_ctx.newFile(mdb_client, op_param.key);
//	                	System.out.println("Mknod commited");
	    				break;
	    			// mkdir
	    			case 1:
	    		        mdb_svrless_ctx.newDirectory(mdb_client, op_param.key, server_id, op_param.ino);
//	    				System.out.println("newDirectory committed!");
	    				break;
	    			// chmod
	    			case 3:
	    				break;
	    			// chown
	    			case 4:
	    				break;
	    			// flush
	    			case 5:
	    	            mdb_svrless_ctx.Flush(mdb_client);
	    				break; 			
	    		}
	    	}
	        socket.close(); 
		}
		catch (TTransportException e) {
			e.printStackTrace();
		} 
		catch (TException e) {  
		      e.printStackTrace();  
		}
		return ret;
	}
	
	/**
	 *  Insert write operations to the counter buffer and commit when reach the batch job size or reach time limitation. 
	 * Clear buffer after commit. cache.get(path)
	 */
	public long write_counter(int server_id, int port, ServerlessIndexFSOperationParameters op_param) {
		int ret = 0;
		if (server_id < 0) 
			server_id = ThreadLocalRandom.current().nextInt(0, config.GetMetaDBNum());

		// Group a number of operations to a specific server or reach the limited time before commit.
		if (op_map.get(server_id).size() < NumtoCommit-1) {
			op_map.get(server_id).add(op_param);
		}
		else {
			op_map.get(server_id).add(op_param);
			short server_id_ = server_id > Short.MAX_VALUE ? Short.MAX_VALUE : server_id < Short.MIN_VALUE ? Short.MIN_VALUE : (short)server_id;
			ret = commit(server_id_, port);
			System.out.println("ServerlessIndexFSRPCWritebackQueue: write_counter(): " + "commit to " + String.valueOf(server_id_));
			empty_map(server_id);
		}
		
		// Periodically commit to MetaDB, regardless of there's enough number of operations or not.
		// For cases with limited number of writes.
//		timer.scheduleAtFixedRate(new TimerTask() {
//			  @Override
//			  public void run() {
//				  for (short i = 0; i < config.GetMetaDBNum(); i++) {
//					commit(i, port);
//					System.out.println("Reached write-back time limit. Commit to MetaDB server " + i);
//					empty_map(i);			  
//				  }
//			  }
//			}, TimetoCommit, TimetoCommit);
		return ret;
	}
}
