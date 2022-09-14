package main.java.indexfs.serverless;

import java.util.Map;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class ServerlessIndexFSRPCClient {
	
	private TTransport socket;
	
	private TProtocol protocol;
	
	public MetaDBService.Client mdb_client;
		
	private String server_addr;
	
	private Integer port;
	
	public boolean CONNECTED = false;

	
	/**
	 * Constructor
	 * @param config
	 * @param server_id
	 */
	public ServerlessIndexFSRPCClient(ServerlessIndexFSConfig config, int server_id) {
		this.server_addr = config.GetMetaDBMap().get((int)server_id);
		this.port = config.GetPortMap().get((int)server_id);
		this.socket = new TSocket(server_addr,port);
		this.protocol = new TBinaryProtocol(socket);
		this.mdb_client = new MetaDBService.Client(protocol);
	}
	
	public void open() {
    	try {
			socket.open();
			CONNECTED = true;
		} catch (TTransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void close() {
        socket.close(); 
        CONNECTED = false;
	}
	
	public String getServerAddr() {
		return this.server_addr;
	}
	
	public Integer getPort() {
		return this.port;		
	}
	
	public boolean checkConnection() {
		return CONNECTED;
	}
}
