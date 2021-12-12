package edu.cmu.pdl.indexfs.srvless;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import com.google.gson.JsonObject;

public class Serverless_IndexFS_driver {
	
	private Config config;
	
	private String zeroth_server;
	private int zeroth_port; 
	
	private int tcp_port;
		
	/**
	 * Serverless IndexFS server instance.
	 */
	private Serverless_IndexFS_server index_srv_;
	
	/**
	 * The ID allocated to the serverless IndexFS server instance.
	 */
	private int serverless_server_id;

	/**
	 * Constructor.
	 */
	public Serverless_IndexFS_driver(String zeroth_server, String zeroth_port, 
			String instance_id, String serverless_server_id) {
		this.zeroth_server = zeroth_server;
		this.zeroth_port = Integer.parseInt(zeroth_port);
		this.serverless_server_id = Integer.parseInt(serverless_server_id);
		this.tcp_port = Integer.parseInt("0");
		this.config = new Config(this.zeroth_server, this.zeroth_port, 
				this.serverless_server_id, this.tcp_port);
	    this.index_srv_ = new Serverless_IndexFS_server(config);

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
		System.out.println("Following " + config.GetMetaDBNum() + " MetaDB server(s) detected: ");
		System.out.println(Arrays.toString(config.GetMetaDBList().toArray()));
		System.out.println(Arrays.toString(config.GetPortList().toArray()));
	    System.out.println("\nIndexFS is ready for service, listening to incoming clients ... \n");

//	    index_srv_.StartHTTPServer(this.http_port);

	    /**
	     * For temporary test purpose. Start. 
	     */
//	    OID oid = new OID();
//	    // Create a directory
//	    oid.dir_id = 0;
//	    oid.obj_name = "dir_0";
//	    oid.path_depth = 0;
//	    index_srv_.Mkdir("/dir_0", oid, 0, 0, 1, 10086);
//		System.out.println("Create a directory, succeed!\n");	 
//	    // Flush
//	    index_srv_.FlushDB(10086);
//		System.out.println("Flush a directory, succeed!\n");	    
//
//		try {
//			TimeUnit.SECONDS.sleep(2);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	    // Read the directory metadata
//	    StatInfo dir_stat = index_srv_.Getattr("/dir_0", oid, 10086);
//		System.out.println("Stat a directory, succeed!\n");	    
//	    
//		// Create a number of files under the directory
//	    int n = 1000000;
//	    oid.dir_id = dir_stat.id;
//	    oid.path_depth = 1;
//		long start = System.currentTimeMillis();
//
//		for (int i = 0; i < n; i++) {
//		    oid.obj_name = "file_"+i;
//		    index_srv_.Mknod("/dir_0/"+oid.obj_name, oid, 0, 10086);
//			System.out.println("Create a file, succeed!\n");
//		}
//	    // Flush
//	    index_srv_.FlushDB(10086);
//        long finish_1 = System.currentTimeMillis();
//	    long timeElapsed_1 = finish_1 - start;
//        System.out.println("Write " + n + " files took: " + timeElapsed_1); 
//	    // Read the file metadata
//		for (int i = 0; i < n; i++) {
//		    oid.obj_name = "file_"+i;
//		    index_srv_.Getattr("/dir_0/"+oid.obj_name, oid, 10086);
//			System.out.println("Stat a file, succeed!\n");	    
//		}	    
//        long finish_2 = System.currentTimeMillis();
//	    long timeElapsed_2 = finish_2 - start;
//        System.out.println("Stat "+ n + " files took: " + timeElapsed_2); 
	    /**
	     * For temporary test purpose. end. 
	     */
	}

	/**
	 * Helper function to deserialize JSONed OID.
	 * @param oid
	 * @return
	 */
	private OID JsonToOID(JsonObject oid) {
		OID obj_id = new OID();
		obj_id.dir_id = oid.getAsJsonPrimitive("dir_id").getAsLong();
		obj_id.path_depth = oid.getAsJsonPrimitive("path_depth").getAsShort();
		obj_id.obj_name = oid.getAsJsonPrimitive("obj_name").getAsString();
		return obj_id;
	}
	
	/**
	 * Parse the client request arguments and pass to IndexFS server methods.
	 * @param op_type Operation types, including Mknod, Mkdir, Getattr, Chown, Chmod and FlushDB
	 * @param path Path of the target object.
	 * @param OID OID of the target object.
	 */
	public void proceedClientRequest(String op_type, String path, 
			JsonObject oid) {
		
		OID obj_id = JsonToOID(oid);
		
		if (op_type == "Mknod") {
			index_srv_.Mknod(path, obj_id, 0, zeroth_port);
		}
		else if (op_type == "Mkdir") {
			int server_id = ThreadLocalRandom.current().nextInt(0, config.GetMetaDBNum());
			index_srv_.Mkdir(path, obj_id, 0, server_id, 0, zeroth_port);
		}
		
		else if (op_type == "Getattr") {
			StatInfo info = index_srv_.Getattr(path, obj_id, zeroth_port);
			// TODO Send object metadata back to IndexFS client.
		}
		
		else if (op_type == "Chown") {
			index_srv_.Chown(path, obj_id, (short)0, (short)0, zeroth_port);
		}

		else if (op_type == "Chmod") {
			index_srv_.Chmod(path, obj_id, 0, zeroth_port);
		}
		
		else if (op_type == "FlushDB") {
			index_srv_.FlushDB(zeroth_port);
		}
	}

	/**
	 * Shut down the server thread (If applicable).
	 * Currently leave it blank.
	 */
	public void Shutdown() {
		// TODO Auto-generated method stub
	}
}