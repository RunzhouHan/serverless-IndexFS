package main.java.indexfs.serverless;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
//import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerlessIndexFSMain {
	public static final Logger LOG = LoggerFactory.getLogger(ServerlessIndexFSMain.class.getName());
	
	public static ServerlessIndexFSConfig config;
	
	public static final String PACKAGE_VERSION = "0.0.0";
	
	public static boolean TCP_CLIENT_START = false;
		
	public static final String FLAGS_logfn = "indexfs_server";
	
	public static final int FLAGS_srvid = -1;
	  
	public static final int FLAGS_logbufsecs = 5;
	
	public static final String FLAGS_log_dir = "/";
	
	public static ServerlessIndexFSTCPClient[] tcpClients;
	

	/*
	 *  Terminate the serverless IndexFS server.
	 */
	public static void SignalHandler(int sig, ServerlessIndexFSParsedArgs parsed_args,
			ServerlessIndexFSServer index_srv_) throws IOException {
		Shutdown(index_srv_);
		System.out.println("Receive external signal to stop deployment " + parsed_args.deployment_id + " ...");
	}
	
	
	public static void Shutdown(ServerlessIndexFSServer index_srv_) throws IOException {
		index_srv_.StopRPC();
	}
	
	public static boolean initializeTCPClients(ServerlessIndexFSServer index_srv_, InMemoryStatInfoCache cache,
			CountDownLatch latch) {
		long startTime = System.nanoTime();
		for(int id=0; id<config.GetClientNum(); id++) {
			try {
				tcpClients[id] = new ServerlessIndexFSTCPClient(config, id, index_srv_, cache, latch);
				tcpClients[id].connect(config.GetClientIP(), id+2004);
				System.out.println("IndexFS IndexFS deployment " + config.GetSvrID()
						+ "has established TCP connection with: " + config.GetClientIP() + ":" + (id+2004));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("tcpClient.connect() duration(ms): " + (System.nanoTime() - startTime)/1000000);
		return true;
	}
	
   
	/**
	 * serverless version of main method.
	 * @param args
	 * @return
	 * @throws IOException 
	 */
//	public static JsonObject main(JsonObject args) throws IOException { //serverless run uncomment this.
//		
//		ServerlessIndexFSInputParser parser = new ServerlessIndexFSInputParser();
//		ServerlessIndexFSParsedArgs parsed_args = parser.inputJsonParse(args);
//	
//		if (config == null) {
//			config = new ServerlessIndexFSConfig(parsed_args);
//		}
//		
//		/**
//		 * Serverless IndexFS.
//		 */
//		ServerlessIndexFSServer index_srv_ = new ServerlessIndexFSServer(config);
//		
//		/**
//		 * Serverless cache.
//		 */
//		InMemoryStatInfoCache cache = new InMemoryStatInfoCache(config, config.cache_capacity, 0.75F);
//	    // System.out.println("Serverless cache capacity: " + cache.size());
//
//				
//		tcpClients = new ServerlessIndexFSTCPClient[config.GetClientNum()];
//		
//	    CountDownLatch latch = new CountDownLatch(config.GetClientNum());
//
//		if (TCP_CLIENT_START == false) {
//
//			System.out.println("============================================================");
//			System.out.println("Set serverless IndexFS deployement " + parsed_args.deployment_id);
//
//			TCP_CLIENT_START = initializeTCPClients(index_srv_, cache, latch);
//			System.out.println("Serverless IndexFS deployement " + parsed_args.deployment_id
//					+ " has connected to " + config.GetClientNum() + " client threads");
//			
//			if (TCP_CLIENT_START) {
//
//				for (int i=0; i<config.GetClientNum(); i++) {					
//					tcpClients[i].start();	
//				}
//				
//				try {
//					latch.await();
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//			}
//			else 
//				System.out.println("Serverless IndexFS " + parsed_args.deployment_id
//						+ "failed to establish TCP connections to all client threads");
//		}
//		
//		else {
//			if (tcpClients != null) {
//				for (int i=0; i<config.GetClientNum(); i++) {
//					if(tcpClients[i].checkConnection())
//						tcpClients[i].disconnect();
//						System.out.println("TCP communication disconnected with client thread " + i); 
//				}
//			}
//			else 
//				System.out.println("Error: TCP flag (true) conflicts with TCP client status (null)");
//		}
//			    
//		Shutdown(index_srv_);
//		
//		System.out.println("============================================================");
//		System.out.println("Everything disposed, server will now shutdown");
//	
//		return args;
//	}

	/**
	 * Local main method.
	 */
    public static void main(String[] args) throws IOException { // local run uncomment this

		String file_name = "file_0";
		String file_path = '/' + file_name;
		long dir_id = 0;
		int path_depth = 0;
    	
    	JsonObject OID = new JsonObject();
    	OID.addProperty("dir_id", dir_id);
    	OID.addProperty("path_depth", path_depth);
    	OID.addProperty("obj_name", file_name);
    	
    	JsonObject args1 = new JsonObject();
    	args1.addProperty("zeroth_server", "127.0.0.1");
    	args1.addProperty("zeroth_port", 10086);
    	args1.addProperty("instance_id", 0);
    	args1.addProperty("deployment_id", 0);
    	args1.addProperty("deployment_num", 1);
    	args1.addProperty("op_type", "Mknod");
    	args1.addProperty("path", file_path);
    	args1.addProperty("client_ip",  "127.0.0.1");
    	args1.addProperty("client_port", 2004);
    	args1.addProperty("client_num", 1);
    	args1.add("OID", OID);

		ServerlessIndexFSInputParser parser = new ServerlessIndexFSInputParser();
		ServerlessIndexFSParsedArgs parsed_args = parser.inputJsonParse(args1);
		
		
		if (config == null) {
			config = new ServerlessIndexFSConfig(parsed_args);
		}
		
		/**
		 * Serverless IndexFS.
		 */
		ServerlessIndexFSServer index_srv_ = new ServerlessIndexFSServer(config);
		
		/**
		 * Serverless cache.
		 */
		InMemoryStatInfoCache cache = new InMemoryStatInfoCache(config, config.cache_capacity, 0.75F);
//	    System.out.println("Serverless cache capacity: " + cache.size());

				
		tcpClients = new ServerlessIndexFSTCPClient[config.GetClientNum()];
		
	    CountDownLatch latch = new CountDownLatch(config.GetClientNum());

		
		if (TCP_CLIENT_START == false) {

			System.out.println("============================================================");
			System.out.println("Set serverless IndexFS deployement " + parsed_args.deployment_id);

			TCP_CLIENT_START = initializeTCPClients(index_srv_, cache, latch);
			System.out.println("Serverless IndexFS deployement " + parsed_args.deployment_id
					+ " has connected to " + config.GetClientNum() + " client threads");
			
			if (TCP_CLIENT_START) {

				for (int i=0; i<config.GetClientNum(); i++) {					
					tcpClients[i].start();	
				}
				
				try {
					latch.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			else 
				System.out.println("Serverless IndexFS " + parsed_args.deployment_id
						+ "failed to establish TCP connections to all client threads");
		}
		
		else {
			if (tcpClients != null) {
				for (int i=0; i<config.GetClientNum(); i++) {
					if(tcpClients[i].checkConnection())
						tcpClients[i].disconnect();
						System.out.println("TCP communication disconnected with client thread " + i); 
				}
			}
			else 
				System.out.println("Error: TCP flag (true) conflicts with TCP client status (null)");
		}
			    
		Shutdown(index_srv_);
		
		LOG.info("Everything disposed, server will now shutdown");
    } // main end.
}
