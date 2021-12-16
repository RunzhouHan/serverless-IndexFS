package srvless;

import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

public class ping {
	
	public static String ip_;
	
	public static InetAddress ip;
	
	public static boolean reachable;
	
	public static final Logger LOG = LoggerFactory.getLogger(ping.class.getName());
	
    public static JsonObject main(JsonObject args){
//    public static void main(String[] args){

		if (args.has("ip"))  {
			// Operation type (a metadata operation parameter).
			ip_ = args.getAsJsonPrimitive("ip").getAsString();  //serverless run uncomment this
	    } 
//    	if (args.length == 1) {
//    		ip_ = args[0];
//    	}
		else {
//	    	LOG.info("Please provide target node IP address.");
	    	System.out.println("Please provide param ip");
	    }
		
		try {
			ip = InetAddress.getByName(ip_);
			System.out.println(ip_ + " is successfully converted");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
        try{            
            reachable = ip.isReachable(10000);
            System.out.println("Is host reachable? " + reachable);
        } catch (Exception e){
            e.printStackTrace();
        }
        */
        JsonObject response = new JsonObject();
        response.addProperty("Status", "Done");
        return response;
    }
}