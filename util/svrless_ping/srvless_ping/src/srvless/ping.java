package srvless;

import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

public class ping {
	
	public static String ip_;
	
	public static boolean reachable;
	
	public static final Logger LOG = LoggerFactory.getLogger(ping.class.getName());
	
    public static JsonObject main(JsonObject args){
        try{            
    		if (args.has("ip"))  {
    			// Operation type (a metadata operation parameter).
    			ip_ = args.getAsJsonPrimitive("ip").getAsString();  //serverless run uncomment this
		    } else {
		    	LOG.info("Please provide target node IP address.");
		    }
            InetAddress ip = InetAddress.getByName(ip_);
 
            reachable = ip.isReachable(10000);

        } catch (Exception e){
            e.printStackTrace();
        }
        
        JsonObject response = new JsonObject();
        if (reachable) {
        	response.addProperty(ip_, "reachable");
        }
        else {
        	response.addProperty(ip_, "unreachable");
        }
        return response;
    }
}