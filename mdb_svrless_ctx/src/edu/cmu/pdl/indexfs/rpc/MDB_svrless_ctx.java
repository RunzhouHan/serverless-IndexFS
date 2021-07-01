package edu.cmu.pdl.indexfs.rpc;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
//import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import com.google.gson.JsonObject;
import static org.junit.Assert.*;


import edu.cmu.pdl.indexfs.rpc.*;

public class MDB_svrless_ctx {

	public void Flush(MetaDBService.Client mdb_client) {
		assertNotNull(mdb_client);
		System.out.println("Client rpc invoked: Flush");
		try {
			mdb_client.Flush();
		} catch (IOError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServerInternalError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void newFile(MetaDBService.Client mdb_client, KeyInfo_THRIFT keyinfo) {
		assertNotNull(mdb_client);
		System.out.println("Client rpc invoked: newFile");
		try {
			mdb_client.NewFile(keyinfo);
		} catch (IOError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServerInternalError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void newDirectory(MetaDBService.Client mdb_client, KeyInfo_THRIFT keyinfo, short zeroth_server, long inode_no) {
		assertNotNull(mdb_client);
		System.out.println("Client rpc invoked: newDirectory");
		try {
			mdb_client.NewDirectory(keyinfo, zeroth_server, inode_no);
		} catch (IOError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServerInternalError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static JsonObject main(JsonObject args) {
		MDB_svrless_ctx mdb_svrless_ctx = new MDB_svrless_ctx();

		try {		
//	 		String ip = "10.0.0.47"; //server ip, local
//	 		String ip = "10.128.0.2"; //server ip, GCE
	 		String ip = args.getAsJsonPrimitive("ip").getAsString();
	 		int port = 10086; // port
			TTransport socket = new TSocket(ip,port);
			TProtocol protocol = new TBinaryProtocol(socket);
        	MetaDBService.Client mdb_client = new MetaDBService.Client(protocol);
        	
    		KeyInfo_THRIFT key_root_dir = new KeyInfo_THRIFT();
    		key_root_dir.parent_id_ = 0;
    		key_root_dir.partition_id_ = 1;
    		key_root_dir.file_name_ = "root";
    		short zeroth_server = 0;
    		long inode_no = 15;
        	
            KeyInfo_THRIFT key_file = new KeyInfo_THRIFT();
            key_file.parent_id_ = 1;
            key_file.partition_id_ = 0;
//            key_file.file_name_ = "0";
            
        	socket.open(); 
        	
            mdb_svrless_ctx.newDirectory(mdb_client, key_root_dir, zeroth_server, inode_no);
            for (int i =0; i< 100 ; i++) {
            	key_file.file_name_ = String.valueOf(i);
            	mdb_svrless_ctx.newFile(mdb_client, key_file);
            	mdb_svrless_ctx.newFile(mdb_client, key_file);
            }
            mdb_svrless_ctx.Flush(mdb_client);
//            mdb_svrless_ctx.newFile(mdb_client, key_root_dir);
            socket.close(); 
            JsonObject response = new JsonObject();
            response.addProperty("Cheers", "serverless client");
            return response;
		}
		catch (TTransportException e) {
            e.printStackTrace();
        } 
		catch (TException e) {  
            e.printStackTrace();  
        }
	}
}
