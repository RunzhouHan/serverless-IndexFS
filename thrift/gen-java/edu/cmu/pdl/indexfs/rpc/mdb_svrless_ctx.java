package java edu.cmu.pdl.indexfs.rpc;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
 
import java edu.cmu.pdl.indexfs.rpc.*;



public class MDB_svrless_ctx {

	public void newFile(KeyInfo_THRIFT keyinfo) {
		if (mdb_client == NULL)
			return 1;
		return mdb_client.newFile(key);
	}

	public void newDirectory(KeyInfo_THRIFT keyinfo, short zeroth_server, long inode_no) {
		if (mdb_client == NULL)
			return 1;
		return mdb_client.newDirectory(key);
	}
	public static void main(String[] args) {
		MDB_svrless_ctx mdb_svrless_ctx = new MDB_svrless_ctx();
		try {		
	 		String ip = "10.0.0.48"; //server ip
	 		// int port = 9090; // port
			TTransport socket = new TSocket(ip,port);
			TProtocol protocol = new TCompactProtocol(socket);
        	MetaDBService.Client mdb_client = new MetaDBService.Client(protocol);
        	socket.open(); 

            KeyInfo_THRIFT key_root_dir = new KeyInfo_THRIFT();
            key_root_dir.parent_id_ = 0;
            key_root_dir.partition_id_ = 1;
            key_root_dir.file_name_ = "root";

            KeyInfo_THRIFT key_file = new KeyInfo_THRIFT();
            key_file.parent_id_ = 0;
            key_file.partition_id_ = 1;
            key_file.file_name_ = "file0";

            short zeroth_server = 0;
            long inode_no = 15;

            mdb_svrless_ctx.newDirectory(key_root_dir, 0, inode_no);
            mdb_svrless_ctx.newFile(key_root_dir, 0, inode_no);
            // System.out.println(client.get(u.uid));  
            socket.close(); 
		}catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {  
            e.printStackTrace();  
        }
		// wrapper.newFile();
	}
 
}
