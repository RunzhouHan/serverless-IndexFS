package main.java.indexfs.serverless;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;

import org.apache.commons.logging.LogFactory;

/**
 * TCP server listening to Json payload generated by workload from IndexFS client.
 */
public class ServerlessIndexFSTCPClient extends Thread {
    private static final org.apache.commons.logging.Log LOG = LogFactory.getLog(ServerlessIndexFSTCPClient.class);
    
    /**
     * This is the maximum amount of time a call to connect() will block. Calls to connect() occur when
     * establishing a connection to a new client.
     */
    private static final int CONNECTION_TIMEOUT = 5000;
    
    /**
     * Mapping from instances of ServerlessHopsFSClient to their associated TCP client object. Recall that each
     * ServerlessHopsFSClient represents a particular client of HopsFS that the NameNode is talking to. We map
     * each of these "HopsFS client" representations to the TCP Client associated with them.
     */
//    private final ConcurrentHashMap<ServerlessHopsFSClient, Client> tcpClients;
    
    /**
     * The deployment number of the local serverless name node instance.
     */
    private final int serverless_server_id;
    
    
    /**
     * Client Socket.
     */    
    private Socket clientSocket;
    
    
    /**
     * Serverless IndexFS I/O driver.
     */
    private ServerlessIndexFSDriver driver;
    
    /**
     * Number of Serverless IndexFS clients.
     */
    public static int clients = 0;
    
    /**
     * Array of IndexFS client IP
     */
    public ArrayList<String> client_ips;
    
    private ServerlessIndexFSInputParser parser;
    
       
    /**
     * Constructor
     * @param config
     * @param driver
     */
    public ServerlessIndexFSTCPClient(ServerlessIndexFSConfig config, ServerlessIndexFSDriver driver_) {
    	this.serverless_server_id = config.GetSvrID();
    	driver = driver_;
    	this.client_ips = new ArrayList<String>();
    	this.parser = new ServerlessIndexFSInputParser();
    }
    
    /**
     * Connect to IndexFS client (TCP server) with given IP & port
     * @param client_ip
     * @param client_port
     * @throws IOException
     */
    public void connect(String client_ip, int client_port) throws IOException {
    	System.out.println("ServerlessIndexFSTCPClient.connect");
//    	clientSocket = new Socket(client_ip, client_port);
    	// Wait for the server to start
    	while(true) {
    		try {
    			clientSocket = new Socket(client_ip, client_port);
    			break;
    		} catch (ConnectException e) {
    			System.out.println("Connect failed, waiting and trying again");
    			try {
    				Thread.sleep(1000);//1 seconds
		        } catch(InterruptedException ie){
    		        ie.printStackTrace();
    		    }
    		}
    	}
        String ready = "Serverless IndexFS TCP client has been connected to IndexFS client";
        System.out.println(ready);
        try {
			PrintWriter outToClient = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
	        outToClient.print(ready);
            outToClient.flush();
		} catch (IOException e) {
			if (clientSocket != null) {
                clientSocket.close();
                System.out.println(String.format("Could not connect to: %s on port: %d", client_ip, client_port));
            }
		}
    	client_ips.add(client_ip);
    	System.out.println("IndexFS TCP client has been established on port " + client_port);
    }

    /**
     * 
     * @throws IOException
     */
    public void receivePayload() throws IOException {
    	System.out.println("ServerlessIndexFSTCPClient.receivePayload");
    	DataInputStream in = new DataInputStream(clientSocket.getInputStream());
    	DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
    	PrintWriter strwriter = new PrintWriter(clientSocket.getOutputStream());
    	InputStreamReader reader = new InputStreamReader(in);
    	BufferedReader b_reader = new BufferedReader(reader);
    	ServerlessIndexFSParsedArgs parsed_args = new ServerlessIndexFSParsedArgs();
    	
        try {
        	String inputLine;
        	long duration_parse = 0; 
        	long duration_one = 0;
        	long duration_proceed = 0;
        	long tmp1, tmp2;
        	long startTime = System.nanoTime();
        	int i = 0;
    		tmp1 = System.nanoTime();

            while ((inputLine = b_reader.readLine()) != null) {
            	if (inputLine.length() == 1) {
            		System.out.println("KILL SIGNAL: " + inputLine);
            		break;
            	}
            	tmp1 = System.nanoTime();
            	parsed_args = parser.inputStringParse(inputLine);
            	
            	duration_parse += System.nanoTime()-tmp1;
            	tmp2 = System.nanoTime();
            	int op_type = -1;
            	if (parsed_args != null) {
            		op_type = driver.proceedClientRequest(parsed_args);
            	}
    			if(op_type == 1) {
    				// Read operation. Send result back to IndexFS client
    				System.out.println("Send out read result: " + String.valueOf(driver.stat.id));
    				strwriter.write(String.valueOf(driver.stat.id));
    				strwriter.flush();
    			}
    			duration_one = System.nanoTime() - tmp2;
    			duration_proceed += duration_one;
    			if ((duration_one/1000000) > 10) {
    				System.out.println("file_" + i + " duration " + duration_one/1000000);
    			}
    			i++;
			}
			long endTime = System.nanoTime();
			long duration = (endTime - startTime)/1000000;
			System.out.println("Client I/O request finished");
			System.out.println("readline parse duration(ms): " + duration_parse/1000000);
			System.out.println("readline proceed duration(ms): " + duration_proceed/1000000);
			System.out.println("readline duration(ms): " + duration);
			
			strwriter.close();
			
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace(); disconnect();;
        }
    }
    
    /**
     * Disconnect from TCP server
     */
    public void disconnect() {
    	System.out.println("ServerlessIndexFSTCPClient.disconnect");
        try {
	        clientSocket.close();
	        System.out.println("disconnected TCP communication between serverless IndexFS and client");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("failed to disconnect TCP communication between serverless IndexFS and client");
		}  

    }
}
