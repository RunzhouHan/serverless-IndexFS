package main.java.indexfs.serverless;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import com.google.gson.JsonObject;

public class ServerlessIndexFSDriver {
	
	private ServerlessIndexFSConfig config;
	
	private int zeroth_port; 

		
	/**
	 * Serverless IndexFS server instance.
	 */
	private ServerlessIndexFSServer index_srv_;
	
	/**
	 * The ID allocated to the serverless IndexFS server instance.
	 */
	private int serverless_server_id;
	
	/**
	 * 0: write
	 * 1: read 
	 */
	private int op_type;
	
	/**
	 * A stat buffer for read result
	 */
	private StatInfo stat;
	
	private OID obj_id;
		
	
	/**
	 * Constructor.
	 */
	public ServerlessIndexFSDriver(ServerlessIndexFSConfig config, ServerlessIndexFSParsedArgs parsed_args) {
		this.config = config;
		this.zeroth_port = parsed_args.zeroth_port;
		this.serverless_server_id = parsed_args.deployment_id;
	    this.obj_id = new OID();
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
	 * Shut down the server thread (If applicable).
	 * Currently use it close rpc connections.
	 * @throws IOException 
	 */
	public void Shutdown() throws IOException {
		this.index_srv_.StopRPC();
	}
	
	/**
	 * Parse the client request arguments and pass to IndexFS server methods.
	 * @param op_type Operation types, including Mknod, Mkdir, Getattr, Chown, Chmod and FlushDB
	 * @param path Path of the target object.
	 * @param OID OID of the target object.
	 */
	public int proceedClientRequest(ServerlessIndexFSParsedArgs parsed_args) {
		
		obj_id = parsed_args.obj_id;
		
		if (obj_id != null) {
			// TODO Remove prints after testing.
			if (parsed_args.op_type.equals("Mknod")) {
				this.index_srv_.Mknod(parsed_args.path, obj_id, 0, zeroth_port);
				this.op_type = 0;
			}
			else if (parsed_args.op_type.equals("Mkdir")) {
				int server_id = ThreadLocalRandom.current().nextInt(0, config.GetMetaDBNum());
				this.index_srv_.Mkdir(parsed_args.path, obj_id, 0, server_id, 0, zeroth_port);
				this.op_type = 0;
			}
			
			else if (parsed_args.op_type.equals("Getattr")) {
				stat = this.index_srv_.Getattr(parsed_args.path, obj_id, zeroth_port);
				this.op_type = 1;
			}
			
			else if (parsed_args.op_type.equals("Chown")) {
				this.index_srv_.Chown(parsed_args.path, obj_id, (short)0, (short)0, zeroth_port);
				this.op_type = 0;
			}

			else if (parsed_args.op_type.equals("Chmod")) {
				this.index_srv_.Chmod(parsed_args.path, obj_id, 0, zeroth_port);
				this.op_type = 0;
			}
			
			else if (parsed_args.op_type.equals("FlushDB")) {
				this.index_srv_.FlushDB(zeroth_port);
				this.op_type = 0;
			}
		}
		return this.op_type;
	}


}
