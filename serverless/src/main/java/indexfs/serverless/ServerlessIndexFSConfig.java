package main.java.indexfs.serverless;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;


import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class ServerlessIndexFSConfig {
	
	/**
	 * Serverless IndexFS deployment ID. 
	 */
	private int serverless_server_id;  
	
	
	/**
	 * Serverless IndexFS deployment ID. 
	 */
	private int deployment_num;  
	
	/**
	 * IP of the server to handle the first request for MetaDB configuration
	 */
	private String zeroth_server;
	
	/**
	 * The port of zeroth server. 
	 */
	private int zeroth_port;
	
	/**
	 * The port of serverless TCP server. This is a hard coded port number. 
	 */
	private int tcp_port;
	
	/**
	 * The port of serverless TCP server. This is a hard coded port number. 
	 */
	private int http_port;
	
	/**
	 * Total number of MetaDB servers.
	 */
	private short NumofMetaDBs;
	
	/**
	 * Total number of IndexFS client threads.
	 */
	private int NumofClients;

	
	/**
	 * IP of client node. A client node may have multiple client threads
	 */
	private String client_ip;
	
	/**
	 * List of MetaDB server IPs.
	 */
	private List<String> MetaDB_list;
	
	/**
	 * List of MetaDB server port.
	 */
	private List<Integer> port_list;
		
	/**
	 * MetaDB server ID to IP map.
	 */
	private Map<Integer, String> MetaDB_map;
	
	/**
	 * MetaDB server ID to port map.
	 */
	private Map<Integer, Integer> port_map;

	/**
	 * Serverless IndexFS cache capacity.
	 */
	public final int cache_capacity = 500000; // need to check IndexFS setting
	
	/**
	 * Serverless IndexFS write-back cache capacity before commit.
	 * Used when group a number of write operations to the same server.
	 */
	public final int NumtoCommit = 10000; // need to check IndexFS setting
	
	/**
	 * Serverless IndexFS write-back cache commit time limit.
	 */
	public final int TimetoCommit = 1000; // need to check IndexFS setting
	
	/**
	 * Serverless IndexFS write-back cache commit time limit.
	 */
	public final int DefaultClientNum = 1; // need to check IndexFS setting

	
	/**
	 * Constructor.
	 * 
	 * @param zeroth_server the first server for server information request.
	 * @param zeroth_port the first server's port numnber.
	 * @param serverless_server_id serverless IndexFS server ID.
	 * @param tcp_port the port number of serverless TCP payload server.
	 */
	public ServerlessIndexFSConfig(ServerlessIndexFSParsedArgs parsed_args) {
		this.zeroth_server = parsed_args.zeroth_server;
		this.zeroth_port = parsed_args.zeroth_port;
		this.serverless_server_id = parsed_args.deployment_id;
		this.deployment_num = parsed_args.deployment_num;
		this.MetaDB_list = BuildMetaDBList();
		this.port_list = BuildPortList();
		this.NumofMetaDBs = CountMetaDBNum();
		this.MetaDB_map = BuildMetaDBMap();
		this.port_map = BuildPortMap();
		this.tcp_port= parsed_args.client_port;
		this.client_ip = parsed_args.client_ip;
		if (parsed_args.client_num > 0) {
			this.NumofClients = parsed_args.client_num;
		}
		else {
			this.NumofClients = DefaultClientNum;
			System.out.println("Invalid client number: " + parsed_args.client_num
					+ ". Set to default value 1");
		}
	}
	
	/**
	 *  Send RPC to MetaDB for MetaDB cluster IP list
	 * @return list of MetaDB cluster IP address.
	 */
	private List<String> BuildMetaDBList() {
		ServerlessIndexFSCtx mdb_svrless_ctx = new ServerlessIndexFSCtx();
		TTransport socket = new TSocket(zeroth_server,zeroth_port);
		TProtocol protocol = new TBinaryProtocol(socket);
    	MetaDBService.Client mdb_client = new MetaDBService.Client(protocol);
    	this.MetaDB_list = null;
    	try {
			socket.open();
			this.MetaDB_list = mdb_svrless_ctx.GetServerList(mdb_client);
	    	socket.close(); 
		} catch (TTransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	return this.MetaDB_list;
	}
	
	/**
	 *  Send RPC to MetaDB for MetaDB cluster IP list
	 * @return list of MetaDB cluster IP address.
	 */
	private List<Integer> BuildPortList() {
		ServerlessIndexFSCtx mdb_svrless_ctx = new ServerlessIndexFSCtx();
		TTransport socket = new TSocket(zeroth_server,zeroth_port);
		TProtocol protocol = new TBinaryProtocol(socket);
    	MetaDBService.Client mdb_client = new MetaDBService.Client(protocol);
    	this.port_list = null;
    	try {
			socket.open();
			this.port_list = mdb_svrless_ctx.GetPortList(mdb_client);
	    	socket.close(); 
		} catch (TTransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	return this.port_list;
	}
	
	/**
	 * Get total number of MetaDB servers.
	 * @return number of MetaDB servers.
	 */
	private short CountMetaDBNum() {
		// Number of server usually will not exceed short's capacity
		return (short) this.MetaDB_list.size();
	}
	
	/**
	 * Build MetaDB server IP to ID map
	 * @return MetaDB server IP to ID map.
	 */
	private Map<Integer, String> BuildMetaDBMap() {
		int sid = 0;
		this.MetaDB_map = new HashMap<Integer, String>();
		for (String srv_ip : this.MetaDB_list ) {
			this.MetaDB_map.put(sid, srv_ip);
			sid++;
		}
		return this.MetaDB_map;
	}
	
	/**
	 * Build MetaDB server port to ID map
	 * @return MetaDB server IP to ID map.
	 */
	private Map<Integer, Integer> BuildPortMap() {
		int sid = 0;
		this.port_map = new HashMap<Integer, Integer>();
		for (Integer srv_ip : this.port_list ) {
			this.port_map.put(sid, srv_ip);
			sid++;
		}
		return this.port_map;
	}
	
	
	/**
	 * A public API to get serverless TCP server port.
	 * @return serverless TCP server port.
	 */
	public int GetTCPPort() {
		return this.tcp_port;
	}
	
	/**
	 * A public API to get serverless HTTP server port.
	 * @return serverless TCP server port.
	 */
	public int GetHTTPPort() {
		return this.http_port;
	}
	
	/**
	 * A public API to get serverless IndexFS server ID.
	 * @return serverless IndexFS server ID.
	 */
	public int GetSvrID() {
		return this.serverless_server_id;
	}
	
	/**
	 * A public API to get serverless IndexFS server ID.
	 * @return serverless IndexFS server ID.
	 */
	public int GetDeploymentNum() {
		return this.deployment_num;
	}
	
	/**
	 * A public API to get total number of MetaDB servers.
	 * @return Number of MetaDB in total.
	 */
	public short GetMetaDBNum() {
		return this.NumofMetaDBs;
	}
	
	/**
	 * A public API to get serverless IndexFS server ID list.
	 * @return MetaDB server IP list.
	 */
	public List<String> GetMetaDBList() {
		return this.MetaDB_list;
	}
	
	/**
	 * A public API to get serverless IndexFS server port list.
	 * @return MetaDB server port list.
	 */
	public List<Integer> GetPortList() {
		return this.port_list;
	}
	
	/**
	 * A public API to get serverless IndexFS server ID map.
	 * @return MetaDB server ID-IP mapping.
	 */
	public Map<Integer, String> GetMetaDBMap() {
		return this.MetaDB_map;
	}
	
	/**
	 * A public API to get serverless IndexFS server port map.
	 * @return MetaDB server ID-IP mapping.
	 */
	public Map<Integer, Integer> GetPortMap() {
		return this.port_map;
	}
	
	/**
	 * A public API to get number of IndexFS client threads.
	 * @return NumofClients.
	 */
	public int GetClientNum() {
		return this.NumofClients;
	}
	
	/**
	 * A public API to get number of IndexFS client IP.
	 * @return NumofClients.
	 */
	public String GetClientIP() {
		return this.client_ip;
	}
	
	public void PrintMetaDBList() {
		if (this.MetaDB_list != null) {
			System.out.println("MetaDB server list: ");
			for(int i=0; i < this.MetaDB_list.size(); i++){
			    System.out.println(this.MetaDB_list.get(i) 
			    		+ ":" + this.port_list.get(i));
			} 
		}
		else {
			System.out.println("MetaDB server is null!");
		}
	}
}
