package srvless;

import java.net.InetAddress;
import com.google.gson.JsonObject;

public class ping {
    public static JsonObject main(JsonObject args){
        try{
//	 		InetAddress ip = args.getAsJsonPrimitive("ip").getAsString();
            InetAddress address = InetAddress.getByName("10.128.0.2");
            boolean reachable = address.isReachable(10000);
            System.out.println("Is host reachable? " + reachable);
        } catch (Exception e){
            e.printStackTrace();
        }
        JsonObject response = new JsonObject();
        response.addProperty("Cheers", "ping 10.128.0.2 done");
        return response;
    }
}