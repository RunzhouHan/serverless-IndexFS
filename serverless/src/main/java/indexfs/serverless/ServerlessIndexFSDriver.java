package main.java.indexfs.serverless;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import com.google.gson.JsonObject;

public class ServerlessIndexFSDriver {
	
	private ServerlessIndexFSConfig config;
	
	private String zeroth_server;
	private int zeroth_port; 
	
	private int tcp_port;
		
	/**
	 * Serverless IndexFS server instance.
	 */
	private ServerlessIndexFSServer index_srv_;
	
	/**
	 * The ID allocated to the serverless IndexFS server instance.
	 */
	private int serverless_server_id;
	
	/**
	 * Constructor.
	 */
	public ServerlessIndexFSDriver(ServerlessIndexFSConfig config, ServerlessIndexFSParsedArgs parsed_args) {
		this.config = config;
		this.zeroth_server = parsed_args.zeroth_server;
		this.zeroth_port = parsed_args.zeroth_port;
		this.serverless_server_id = parsed_args.deployment_id;
		this.tcp_port = Integer.parseInt("0");
	    this.index_srv_ = new ServerlessIndexFSServer(config);
	}


	/**
	 *  Monitoring thread for failure handling.
	 *  Not implemented in this version 
	 */
	public void SetupMonitoring() {
		;
	}
	
	/**
	 * Start a serverless IndexFS server instance.
	 * @throws IOException 
	 */
	public void StartServer() throws IOException {
//		System.out.println("Following " + config.GetMetaDBNum() + " MetaDB server(s) detected: ");
//		System.out.println(Arrays.toString(config.GetMetaDBList().toArray()));
//		System.out.println(Arrays.toString(config.GetPortList().toArray()));
//	    System.out.println("\nIndexFS is ready for service, listening to incoming clients ... \n");
//	    index_srv_.StartHTTPServer(this.http_port);
	}
	
	/**
	 * Parse the client request arguments and pass to IndexFS server methods.
	 * @param op_type Operation types, including Mknod, Mkdir, Getattr, Chown, Chmod and FlushDB
	 * @param path Path of the target object.
	 * @param OID OID of the target object.
	 */
	public int proceedClientRequest(ServerlessIndexFSParsedArgs parsed_args) {
		
		OID obj_id = parsed_args.obj_id;
		
		if (obj_id != null) {
			// TODO Remove prints after testing.
			System.out.println(obj_id);
			System.out.println(parsed_args.op_type);
			if (parsed_args.op_type.equals("Mknod")) {
				System.out.println("Mknod:"+parsed_args.path+", "+obj_id+", ");
				index_srv_.Mknod(parsed_args.path, obj_id, 0, zeroth_port);
			}
			else if (parsed_args.op_type == "Mkdir") {
				int server_id = ThreadLocalRandom.current().nextInt(0, config.GetMetaDBNum());
				index_srv_.Mkdir(parsed_args.path, obj_id, 0, server_id, 0, zeroth_port);
			}
			
			else if (parsed_args.op_type == "Getattr") {
				StatInfo info = index_srv_.Getattr(parsed_args.path, obj_id, zeroth_port);
				// TODO Send object metadata back to IndexFS client.
			}
			
			else if (parsed_args.op_type == "Chown") {
				index_srv_.Chown(parsed_args.path, obj_id, (short)0, (short)0, zeroth_port);
			}

			else if (parsed_args.op_type == "Chmod") {
				index_srv_.Chmod(parsed_args.path, obj_id, 0, zeroth_port);
			}
			
			else if (parsed_args.op_type == "FlushDB") {
				index_srv_.FlushDB(zeroth_port);
			}
		}
		return 0;
	}

	/**
	 * Shut down the server thread (If applicable).
	 * Currently leave it blank.
	 */
	public void Shutdown() {
		// TODO Auto-generated method stub
	}
}
