package edu.cmu.pdl.indexfs.srvless;

import java.io.IOException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

//import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class svrless_IndexFS_main {
	
	public static final String PACKAGE_VERSION = "0.0.0";
	
	public static boolean SERVER_START = false;
		
	public static final String FLAGS_logfn = "indexfs_server";
	
	public static final int FLAGS_srvid = -1;
	  
	public static final int FLAGS_logbufsecs = 5;
	
	public static final String FLAGS_log_dir = "/";
	
	public static srvless_IndexFS_driver driver;
	
	public static final Logger LOG = LoggerFactory.getLogger(svrless_IndexFS_main.class.getName());
	
	public static String zeroth_server;
	
	public static String zeroth_port;
	
	public static String instance_id;
	
	public static String deployment_id;
	
	public static String op_type;
	
	public static String path;
	
	public static JsonObject OID;
	
	public static void SignalHandler(int sig) {
	  if (driver != null) {
	    driver.Shutdown();
	  }
	  LOG.info("Receive external signal to stop server " + deployment_id + " ...");
	}
	
	/**
	 * serverless version of main method.
	 * @param args
	 * @return
	 */
	public static JsonObject main(JsonObject args) { //serverless run uncomment this.
		/*
		// Terminate the serverless IndexFS server when receive SIGTERM sent from Kubernetes.
        Runtime.getRuntime().addShutdownHook(new Thread() {
        @Override
            public void run() {
                System.out.println("Shutdown Hook Registered.");
            }   
        }); 
		
        try {
		// Management server IP address.
		zeroth_server = args.getAsJsonPrimitive(srvless_IndexFS_keys.zeroth_server).getAsString();  //serverless run uncomment this
        } catch (JsonParseException ex) {
        	LOG.info("Please provide IP address of the management server.");
        }
        
        try {
		// Management server port.
		zeroth_port = args.getAsJsonPrimitive(srvless_IndexFS_keys.zeroth_port).getAsString();  //serverless run uncomment this
        } catch (JsonParseException ex) {
        	LOG.info("Please provide port number of the management server.");
        }
        
        try {        
		// Pod ID.
		instance_id = args.getAsJsonPrimitive(srvless_IndexFS_keys.instance_id).getAsString();  //serverless run uncomment this	
	    } catch (JsonParseException ex) {
	    	LOG.info("Please provide instance id.");
	    }
        
        try {
		// Serverless function ID (IndexFS server ID). 
		deployment_id = args.getAsJsonPrimitive(srvless_IndexFS_keys.deployment_id).getAsString();  //serverless run uncomment this
		} catch (JsonParseException ex) {
			LOG.info("Please provide deployment id for the serverless function.");
		}
        
        try {
		// Operation type (a metadata operation parameter).
		path = args.getAsJsonPrimitive(srvless_IndexFS_keys.op_type).getAsString();  //serverless run uncomment this
	    } catch (JsonParseException ex) {
	    	LOG.info("Please provide the metadata operation type.");
	    }
        
        try {
		// Object path (a metadata operation parameter).
		path = args.getAsJsonPrimitive(srvless_IndexFS_keys.path).getAsString();  //serverless run uncomment this
	    } catch (JsonParseException ex) {
	    	LOG.info("Please provide the object path of the metadata operation.");
	    }
	    
        try {
		// OID (a metadata operation parameter). 
		OID = args.getAsJsonPrimitive(srvless_IndexFS_keys.OID).getAsJsonObject();
	    } catch (JsonParseException ex) {
	    	LOG.info("Please provide OID of the metadata operation." );
	    }
				

		if (SERVER_START == false) {
			LOG.info("============================================================");
			LOG.info("set indexfs server rank " + deployment_id);
			driver = new srvless_IndexFS_driver(zeroth_server, zeroth_port, instance_id, deployment_id); // serverless run uncomment this
			try {
				driver.StartServer();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SERVER_START = true;
		}
		
		if(SERVER_START) {
			driver.proceedClientRequest(op_type, path, OID);
		}
		
		LOG.info("Everything disposed, server will now shutdown");
		*/
		
		JsonObject ret = new JsonObject();
		ret.addProperty("return value", "Succeed!");
		return ret;
	}
      
	/**
	 * Local main method.
	 */
//    public static void main(String[] args) throws IOException { // local run uncomment this
//		  driver = new srvless_IndexFS_driver(args[0], args[1], args[2], args[3]); // local run uncomment this
//	}
}
