import java.net.InetAddress;
import com.google.gson.JsonObject;

public class ServerlessPing {
	
	public static String ip_;
	
	public static InetAddress ip;
	
	public static boolean reachable;
		
    public static JsonObject main(JsonObject args){
//    public static void main(String[] args){
		if (args.has("ip"))  {
			ip_ = args.getAsJsonPrimitive("ip").getAsString(); 
	    } 
		else {
	    	System.out.println("Please provide param ip");
	    }

		try {
			ip = InetAddress.getByName(ip_);
			System.out.println(ip_ + " is successfully converted");
		} catch (Exception e) {
			e.printStackTrace();
		}
        try{            
            reachable = ip.isReachable(10000);
            System.out.println("Is host reachable? " + reachable);
        } catch (Exception e){
            e.printStackTrace();
        }

        JsonObject response = new JsonObject();

		if (reachable) {
			response.addProperty(ip_,"reachable");
		}
		else {
			response.addProperty(ip_,"unreachable");
		}
	        return response;
    }
}
