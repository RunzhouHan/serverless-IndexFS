package edu.cmu.pdl.indexfs.srvless;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class srvless_IndexFS_TCPlistener {
	//public class srvless_IndexFS_TCPlistener {
	/**
	 * TCP server listening to Json payload generated by workload from IndexFS client.
	 */
    private ServerSocket serverSocket;
    public static int clients = 0;
    

    private void establish(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("IndexFS TCP server has been established on port " + port);
    }

    private void accept() throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();
            Runnable r = new MyThreadHandler(socket);
            Thread t = new Thread(r);
            t.start();
        }
    }

    private static class MyThreadHandler implements Runnable {
        private Socket socket;

        MyThreadHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            clients++;
            System.out.println(clients + " JSONClient(s) connected on port: " + socket.getPort());

            try {
            	JSONObject payload = receiveJSON();
                printJSON(payload);
            } catch (IOException e) {

            } finally {
                try {
                    closeSocket();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void closeSocket() throws IOException {
            socket.close();
        }

        /**
         * use the JSON Protocol to receive a json object as
         * String from the client and reconstructs that object
         * @return JSONObejct with the same state (data) as
         * the JSONObject the client sent as a String msg.
         * @throws IOException
         */
        public JSONObject receiveJSON() throws IOException {

	    	DataInputStream inputStream = null;
        	String strInputstream = "";         
        	inputStream = new DataInputStream (socket.getInputStream ());
        	ByteArrayOutputStream baos = new ByteArrayOutputStream ();
            byte [] by = new byte [2048];
        	int n;
        	
            while ((n = inputStream.read (by)) != -1) {
            	baos.write (by, 0, n);     	
            }
            strInputstream = new String (baos.toByteArray ());        	

            //Process client data  
            //Restore the data received by the socket to JSONObject
             JSONObject json = new JSONObject (strInputstream);       
             String op = (String) json.get("key");  
             System.out.println (op);
             switch (op) {	  
             
             }
             
            return json;
        }

	    public void printJSON(JSONObject jsonObject) throws IOException {
	    	System.out.println("Sent to server: " + " " + jsonObject.get("key").toString());
	    }
    }

    public void start(int port) throws IOException{
        establish(port);
        accept();
    }
}
