package main.java.indexfs.serverless;

public class ServerlessIndexFSParsedArgs {
	public String zeroth_server;
	
	public int zeroth_port;
	
	public int instance_id;
	
	public int deployment_id;
	
	public String op_type;
	
	public String path;
			
	public OID obj_id;
	
	public String client_ip;
	
	public int client_port;
	
	public int client_num;
	/**
	 * Constructor.
	 */
	public ServerlessIndexFSParsedArgs() {
		this.obj_id = new OID();
	}
}
