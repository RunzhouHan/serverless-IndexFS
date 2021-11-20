package edu.cmu.pdl.indexfs.srvless;

public class svrless_IndexFS_main {
	
//	private static final String PACKAGE_VERSION = "0.0.0";
	
	private static srvless_IndexFS_driver driver;
	
//	public static JsonObject main(JsonObject args) { //serverless run uncomment this
//	String ip = args.getAsJsonPrimitive("ip").getAsString();  //serverless run uncomment this
//	String port = args.getAsJsonPrimitive("port").getAsString();  //serverless run uncomment this
	public static void main(String[] args) { // local run uncomment this
	driver = new srvless_IndexFS_driver(args[0], args[1]); // local run uncomment this
	driver.StartServer();
		
/* Test can be like this */
//		for (int i = 0; i< 10 ; i++) {
//        	char[] file_name_ = ("f_" + i).toCharArray();
//			System.out.println(server_map.get(server_id));
//        }
	}
}
