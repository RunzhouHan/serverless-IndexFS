package main.java.indexfs.serverless;

import java.io.IOException;

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
	
	public static ServerlessIndexFSDriver driver;
	
	public static ServerlessIndexFSTCPClient tcpClient;

	/*
	 *  Terminate the serverless IndexFS server.
	 */
//	public static void SignalHandler(int sig) {
//	  if (driver != null) {
//	    driver.Shutdown();
//	  }
//	  LOG.info("Receive external signal to stop server " + deployment_id + " ...");
//	}
	
	/*
	 *  Terminate the serverless IndexFS server when receive SIGTERM sent from Kubernetes.
	 */
//    Runtime.getRuntime().addShutdownHook(new Thread() {
//    @Override
//        public void run() {
//            System.out.println("Shutdown Hook Registered.");
//        }   
//    }); 
	
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
//	if (TCP_CLIENT_START == false) {
//
//		System.out.println("============================================================");
//		System.out.println("set indexfs server rank " + parsed_args.deployment_id);
//
//		config = new ServerlessIndexFSConfig(parsed_args);
//		config.PrintMetaDBList();
//		
//		driver = new ServerlessIndexFSDriver(config, parsed_args); // serverless run uncomment this
//		
//		if (driver != null) {
//			try {
//				driver.StartServer();
//				// After the first success request, switch to TCP communication
//				tcpClient = new ServerlessIndexFSTCPClient(config, driver);
//				}
//			catch (IOException e) {
//				//TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//		if (tcpClient != null) {
//			TCP_CLIENT_START = true;
//			System.out.println("indexfs TCP client " + parsed_args.deployment_id + " initialized");
//			
//			// TCP reserves port 0
//			long duration1,duration2,duration3 = 0;
//			long startTime = System.nanoTime();
//			tcpClient.connect(parsed_args.client_ip, parsed_args.client_port);
//			long t1 = System.nanoTime();
//			duration1 = (t1 - startTime)/1000000;
//
//			tcpClient.receivePayload();
//			long t2 = System.nanoTime();
//			duration2 = (t2 - t1)/1000000;
//			
//			tcpClient.disconnect();
//			long endTime = System.nanoTime();
//			duration3 = (endTime - startTime)/1000000;
//			System.out.println("tcpClient.connect() duration(ms): " + duration1);
//			System.out.println("tcpClient.receivePayload() duration(ms): " + duration2);
//			System.out.println("tcp communication duration(ms): " + duration3);
//		}
//		else 
//			System.out.println("indexfs TCP client " + parsed_args.deployment_id + "initialization failed");
//	}
//	
//	else {
//		if (tcpClient != null) {
//			System.out.println("TCP communication has already been established with " + parsed_args.client_ip +
//								":"+ parsed_args.client_ip);
//			tcpClient.receivePayload();
//			tcpClient.disconnect();
//		}
//		else 
//			System.out.println("Error: TCP flag (true) conflicts with TCP client status (null)");
//	}
//
//	LOG.info("Everything disposed, server will now shutdown");
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
    	args1.addProperty("op_type", "Mknod");
    	args1.addProperty("path", file_path);
    	args1.addProperty("client_ip",  "127.0.0.1");
    	args1.addProperty("client_port", 2004);
    	args1.add("OID", OID);

		ServerlessIndexFSInputParser parser = new ServerlessIndexFSInputParser();
		ServerlessIndexFSParsedArgs parsed_args = parser.inputJsonParse(args1);

		if (TCP_CLIENT_START == false) {

			System.out.println("============================================================");
			System.out.println("set indexfs server rank " + parsed_args.deployment_id);

			config = new ServerlessIndexFSConfig(parsed_args);
			config.PrintMetaDBList();
			
			driver = new ServerlessIndexFSDriver(config, parsed_args); // serverless run uncomment this
			
			if (driver != null) {
				try {
					driver.StartServer();
					// After the first success request, switch to TCP communication
					tcpClient = new ServerlessIndexFSTCPClient(config, driver);
					}
				catch (IOException e) {
					//TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (tcpClient != null) {
				TCP_CLIENT_START = true;
				System.out.println("indexfs TCP client " + parsed_args.deployment_id + " initialized");
				// TCP reserves port 0
				long duration1,duration2,duration3 = 0;
				long startTime = System.nanoTime();
				tcpClient.connect(parsed_args.client_ip, parsed_args.client_port);
				long t1 = System.nanoTime();
				duration1 = (t1 - startTime)/1000000;
	
				tcpClient.receivePayload();
				long t2 = System.nanoTime();
				duration2 = (t2 - t1)/1000000;
				
				tcpClient.disconnect();
				long endTime = System.nanoTime();
				duration3 = (endTime - startTime)/1000000;
				System.out.println("tcpClient.connect() duration(ms): " + duration1);
				System.out.println("tcpClient.receivePayload() duration(ms): " + duration2);
				System.out.println("tcp communication duration(ms): " + duration3);
			}
			else 
				System.out.println("indexfs TCP client " + parsed_args.deployment_id + "initialization failed");
		}
		
		else {
			if (tcpClient != null) {
				System.out.println("TCP communication has already been established with " + parsed_args.client_ip +
									":"+ parsed_args.client_ip);
				tcpClient.receivePayload();
				tcpClient.disconnect();
			}
			else 
				System.out.println("Error: TCP flag (true) conflicts with TCP client status (null)");
		}

		LOG.info("Everything disposed, server will now shutdown");
    } // main end.
}
