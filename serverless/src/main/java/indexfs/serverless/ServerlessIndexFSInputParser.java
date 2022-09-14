package main.java.indexfs.serverless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


public class ServerlessIndexFSInputParser {
	public static final Logger LOG = LoggerFactory.getLogger(ServerlessIndexFSInputParser.class.getName());
	
	private String[] splited_args_string; 
	
	private final int NUM_OF_PARAMS = 9;
	
	private ServerlessIndexFSParsedArgs parsed_args;
	
	/**
	 * Constructor
	 */
	ServerlessIndexFSInputParser() {
		this.parsed_args = new ServerlessIndexFSParsedArgs();
	}
	
	/**
	 * Helper function to deserialize JSONed OID.
	 * @param oid
	 * @return
	 */
	private OID JsonToOID(JsonObject oid_json) {
		OID obj_id = new OID();
		obj_id.dir_id = oid_json.getAsJsonPrimitive("dir_id").getAsLong();
		obj_id.path_depth = oid_json.getAsJsonPrimitive("path_depth").getAsShort();
		obj_id.obj_name = oid_json.getAsJsonPrimitive("obj_name").getAsString();
		return obj_id;
	}
	
	/**
	 * Parse the input string
	 * @param args
	 * @return
	 */
	public ServerlessIndexFSParsedArgs inputStringParse(String args) {
		splited_args_string = args.split("\\s+");
		if (splited_args_string.length != NUM_OF_PARAMS) {
			System.out.println("ServerlessIndexFSInputParser:ServerlessIndexFSParsedArgs(): missing parameter(s) in tcp payload\n"
					+ args);
		}
		else {
			parsed_args.zeroth_server = splited_args_string[0];
			parsed_args.zeroth_port = Integer.parseInt(splited_args_string[1]);
			parsed_args.instance_id  = Integer.parseInt(splited_args_string[2]);
			parsed_args.deployment_id  = Integer.parseInt(splited_args_string[3]);
			parsed_args.op_type = splited_args_string[4];
			parsed_args.path = splited_args_string[5];
			parsed_args.obj_id.dir_id = Integer.parseInt(splited_args_string[6]);
			parsed_args.obj_id.path_depth = (short) Integer.parseInt(splited_args_string[7]);
			parsed_args.obj_id.obj_name = splited_args_string[8];
		}
		return parsed_args;
	}
	
	public ServerlessIndexFSParsedArgs inputJsonParse(JsonObject args) {
		if (args.has(ServerlessIndexFSKeys.zeroth_server)) {
		// Management server IP address.
			parsed_args.zeroth_server = args.getAsJsonPrimitive(ServerlessIndexFSKeys.zeroth_server).getAsString();  //serverless run uncomment this
        } else {
        	LOG.info("Please provide IP address of the management server.");
        }
        
		if (args.has(ServerlessIndexFSKeys.zeroth_port))  {
		// Management server port.
			parsed_args.zeroth_port = args.getAsJsonPrimitive(ServerlessIndexFSKeys.zeroth_port).getAsInt();  //serverless run uncomment this
        } else {
        	LOG.info("Please provide port number of the management server.");
        }
        
        if (args.has(ServerlessIndexFSKeys.instance_id))  {        
		// Pod ID.
        	parsed_args.instance_id = args.getAsJsonPrimitive(ServerlessIndexFSKeys.instance_id).getAsInt();  //serverless run uncomment this	
	    } else {
	    	LOG.info("Please provide instance id.");
	    }
        
	    if (args.has(ServerlessIndexFSKeys.deployment_id))  {
		// Serverless function ID (IndexFS server ID). 
	    	parsed_args.deployment_id = args.getAsJsonPrimitive(ServerlessIndexFSKeys.deployment_id).getAsInt();  //serverless run uncomment this
		} else {
			LOG.info("Please provide deployment id for the serverless function.");
		}
        
		if (args.has(ServerlessIndexFSKeys.op_type))  {
		// Operation type (a metadata operation parameter).
			parsed_args.op_type = args.getAsJsonPrimitive(ServerlessIndexFSKeys.op_type).getAsString();  //serverless run uncomment this
	    } else {
	    	System.out.println("Please provide the metadata operation type.");
	    }
        
	    if (args.has(ServerlessIndexFSKeys.path))  {
		// Object path (a metadata operation parameter).
	    	parsed_args.path = args.getAsJsonPrimitive(ServerlessIndexFSKeys.path).getAsString();  //serverless run uncomment this
	    } else {
	    	System.out.println("Please provide the object path of the metadata operation.");
	    }
	    
	    if (args.has(ServerlessIndexFSKeys.client_ip))  {
		// Object path (a metadata operation parameter).
	    	parsed_args.client_ip = args.getAsJsonPrimitive(ServerlessIndexFSKeys.client_ip).getAsString();  //serverless run uncomment this
	    } else {
	    	System.out.println("Please provide the object path of the metadata operation.");
	    }

	    if (args.has(ServerlessIndexFSKeys.client_port))  {
		// Object path (a metadata operation parameter).
	    	parsed_args.client_port = args.getAsJsonPrimitive(ServerlessIndexFSKeys.client_port).getAsInt();  //serverless run uncomment this
	    } else {
	    	System.out.println("Please provide the object path of the metadata operation.");
	    }
	    
	    if (args.has(ServerlessIndexFSKeys.OID))  {
		// OID (a metadata operation parameter). 
	    	parsed_args.obj_id = JsonToOID(args.getAsJsonObject(ServerlessIndexFSKeys.OID));
	    } else {
	    	System.out.println("Please provide OID of the metadata operation." );
	    }
		return parsed_args;
	}
}
