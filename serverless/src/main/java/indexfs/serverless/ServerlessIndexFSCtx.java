package main.java.indexfs.serverless;

import org.apache.thrift.TException;

import java.util.List;

public class ServerlessIndexFSCtx {
			
	public List<String> GetServerList(MetaDBService.Client mdb_client) {
		List<String> server_list = null;		
		try {
			server_list = mdb_client.GetServerList();
		} catch (ServerInternalError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return server_list;
	}
	
	public List<Integer> GetPortList(MetaDBService.Client mdb_client) {
		List<Integer> port_list = null;		
		try {
			port_list = mdb_client.GetPortList();
		} catch (ServerInternalError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return port_list;
	}
	
	public void Flush(MetaDBService.Client mdb_client) {
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
	
    public StatInfo GetEntry(MetaDBService.Client mdb_client, KeyInfo_THRIFT keyinfo) {
		StatInfo info = new StatInfo();
		try {
			info = mdb_client.GetEntry(keyinfo);
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
    	return info;
    }
    			
    public void PutEntry(MetaDBService.Client mdb_client, KeyInfo_THRIFT keyinfo,
    		 StatInfo info) {
		try {
			mdb_client.PutEntry(keyinfo, info);
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
    
	public long NextInode(MetaDBService.Client mdb_client) {
		long ino = 0;
		try {
			ino = mdb_client.ReserveNextInodeNo();
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
		return ino;
	}
}
