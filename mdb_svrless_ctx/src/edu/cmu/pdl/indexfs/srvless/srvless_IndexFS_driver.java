package edu.cmu.pdl.indexfs.srvless;

import static org.junit.Assert.assertNotNull;

public class srvless_IndexFS_driver {
	
	private static GetConfig config;
	
	private static String zeroth_server;
	private static int zeroth_port; 
	
	private static int HTTP_listener;
	private static MDB_srvless_server index_srv_;
	

	/* Constructor */
	public srvless_IndexFS_driver(String ip, String port) {
		zeroth_server = ip;
		zeroth_port = Integer.parseInt(port);
		config = new GetConfig();
	    assertNotNull(config);		
		config.zeroth_server = zeroth_server;
		config.zeroth_port = zeroth_port;
	}
	
	public void SetupMonitoring() {
		/* Monitor is not implemented in this version */
	}
	
	public void StartServer() {
//		HTTP_listener = new HTTP_listener();
	    index_srv_ = new MDB_srvless_server(config);
	    assertNotNull(index_srv_);
	    
	    // For temporary test purpose. Start. 
//	    index_srv_.newFile("File_1", 0);
	    // For temporary test purpose. end. 

	    System.out.println("IndexFS is ready for service, listening to incoming clients ... ");
	}
}
