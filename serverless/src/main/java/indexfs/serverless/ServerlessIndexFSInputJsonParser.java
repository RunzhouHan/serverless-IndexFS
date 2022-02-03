package main.java.indexfs.serverless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


public class ServerlessIndexFSInputJsonParser {
	public static final Logger LOG = LoggerFactory.getLogger(ServerlessIndexFSInputJsonParser.class.getName());

	public String zeroth_server;
	
	public int zeroth_port;
	
	public int instance_id;
	
	public int deployment_id;
	
	public String op_type;
	
	public String path;
	
	public JsonObject oid_json;
		
	public OID obj_id;
	
	public String client_ip;
	
	public int client_port;
	
	/**
	 * Constructor.
	 */
	public ServerlessIndexFSInputJsonParser() {
		this.obj_id = new OID();
	}
	
	/**
	 * Helper function to deserialize JSONed OID.
	 * @param oid
	 * @return
	 */
	private void JsonToOID() {
		obj_id.dir_id = oid_json.getAsJsonPrimitive("dir_id").getAsLong();
		obj_id.path_depth = oid_json.getAsJsonPrimitive("path_depth").getAsShort();
		obj_id.obj_name = oid_json.getAsJsonPrimitive("obj_name").getAsString();
	}
	
	
	public void inputParse(JsonObject args) {
		if (args.has(ServerlessIndexFSKeys.zeroth_server)) {
		// Management server IP address.
        	zeroth_server = args.getAsJsonPrimitive(ServerlessIndexFSKeys.zeroth_server).getAsString();  //serverless run uncomment this
        } else {
        	LOG.info("Please provide IP address of the management server.");
        }
        
		if (args.has(ServerlessIndexFSKeys.zeroth_port))  {
		// Management server port.
        	zeroth_port = args.getAsJsonPrimitive(ServerlessIndexFSKeys.zeroth_port).getAsInt();  //serverless run uncomment this
        } else {
        	LOG.info("Please provide port number of the management server.");
        }
        
        if (args.has(ServerlessIndexFSKeys.instance_id))  {        
		// Pod ID.
        	instance_id = args.getAsJsonPrimitive(ServerlessIndexFSKeys.instance_id).getAsInt();  //serverless run uncomment this	
	    } else {
	    	LOG.info("Please provide instance id.");
	    }
        
	    if (args.has(ServerlessIndexFSKeys.deployment_id))  {
		// Serverless function ID (IndexFS server ID). 
        	deployment_id = args.getAsJsonPrimitive(ServerlessIndexFSKeys.deployment_id).getAsInt();  //serverless run uncomment this
		} else {
			LOG.info("Please provide deployment id for the serverless function.");
		}
        
		if (args.has(ServerlessIndexFSKeys.op_type))  {
		// Operation type (a metadata operation parameter).
        	op_type = args.getAsJsonPrimitive(ServerlessIndexFSKeys.op_type).getAsString();  //serverless run uncomment this
	    } else {
	    	System.out.println("Please provide the metadata operation type.");
	    }
        
	    if (args.has(ServerlessIndexFSKeys.path))  {
		// Object path (a metadata operation parameter).
        	path = args.getAsJsonPrimitive(ServerlessIndexFSKeys.path).getAsString();  //serverless run uncomment this
	    } else {
	    	System.out.println("Please provide the object path of the metadata operation.");
	    }
	    
	    if (args.has(ServerlessIndexFSKeys.client_ip))  {
		// Object path (a metadata operation parameter).
	    	client_ip = args.getAsJsonPrimitive(ServerlessIndexFSKeys.client_ip).getAsString();  //serverless run uncomment this
	    } else {
	    	System.out.println("Please provide the object path of the metadata operation.");
	    }

	    if (args.has(ServerlessIndexFSKeys.client_port))  {
		// Object path (a metadata operation parameter).
	    	client_port = args.getAsJsonPrimitive(ServerlessIndexFSKeys.client_port).getAsInt();  //serverless run uncomment this
	    } else {
	    	System.out.println("Please provide the object path of the metadata operation.");
	    }
	    
	    if (args.has(ServerlessIndexFSKeys.OID))  {
		// OID (a metadata operation parameter). 
	    	oid_json = args.getAsJsonObject(ServerlessIndexFSKeys.OID);
        	JsonToOID();
	    } else {
	    	System.out.println("Please provide OID of the metadata operation." );
	    }
		
	}
}
