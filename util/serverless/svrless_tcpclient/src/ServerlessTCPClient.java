import java.io.*;  
import java.net.*;
import com.google.gson.JsonObject; 


public class ServerlessTCPClient {
	
	public static String ip;
	
	public static int port;
	
	public static boolean reachable = false;
	
    public static JsonObject main(JsonObject args){
//	public static void main(String[] args) {  
		if (args.has("ip"))  {
			ip = args.getAsJsonPrimitive("ip").getAsString(); 
	    } 
		else {
	    	System.out.println("Please provide server ip");
	    }

		if (args.has("port"))  {
			port = args.getAsJsonPrimitive("port").getAsInt(); 
	    } 
		else {
	    	System.out.println("Please provide server port");
	    }

		try{      
			// Replace with your server IP & port
			Socket soc=new Socket(ip,port);  
			DataOutputStream out=new DataOutputStream(soc.getOutputStream());  
			out.writeUTF("Hello");
			out.flush();
			out.close();  
			soc.close();
			reachable = true;
		} catch(Exception e){
			e.printStackTrace();
		}  
		
        JsonObject response = new JsonObject();
		
		if (reachable) {
			response.addProperty(ip,"reachable");
		}
		else {
			response.addProperty(ip,"unreachable");
		}
	    
		return response;
    }
}