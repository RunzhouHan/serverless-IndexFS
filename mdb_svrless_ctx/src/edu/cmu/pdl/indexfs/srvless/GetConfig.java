package edu.cmu.pdl.indexfs.srvless;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class GetConfig {
	
	// Hard code serverless function id as 0 for now
	private final int serverless_function_id = 0;  
	private List<String> server_list;
	public String zeroth_server;
	public int zeroth_port = 10086;
	public int LRU_capacity = 1000;
	
	public List<String> GetSrvList() {
		// Send RPC to metadb for server IP list
		svrless_IndexFS_ctx mdb_svrless_ctx = new svrless_IndexFS_ctx();
		TTransport socket = new TSocket(zeroth_server,zeroth_port);
		TProtocol protocol = new TBinaryProtocol(socket);
    	MetaDBService.Client mdb_client = new MetaDBService.Client(protocol);
    	server_list = null;
    	try {
			socket.open();
	    	server_list = mdb_svrless_ctx.GetServerList(mdb_client);
	    	socket.close(); 
		} catch (TTransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	return server_list;
	}
	
	public int GetSrvId() {
		return serverless_function_id;
	}
	
	public short GetSrvNum() {
		// Send RPC to metadb for srv num
		if (server_list == null)
			server_list = GetSrvList();
		// Number of server usually will not exceed short's capacity
		return (short) server_list.size();
	}
	
	public Map<Integer, String> GetSrvMap() {
		Map<Integer, String> server_map = new HashMap<Integer, String>();
//		int num_servers_ = GetSrvNum();
		if (server_list == null)
			server_list = GetSrvList();
		int sid = 0;
		for (String srv_ip : server_list ) {
			server_map.put(sid, srv_ip);
			sid++;
		}
		return server_map;
	}
}
