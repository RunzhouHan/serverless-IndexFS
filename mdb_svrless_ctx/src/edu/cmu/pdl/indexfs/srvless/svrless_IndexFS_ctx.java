package edu.cmu.pdl.indexfs.srvless;

import org.apache.thrift.TException;

import static org.junit.Assert.*;

import java.util.List;

public class svrless_IndexFS_ctx {
			
	public List<String> GetServerList(MetaDBService.Client mdb_client) {
		assertNotNull(mdb_client);
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
	
	public void Flush(MetaDBService.Client mdb_client) {
		assertNotNull(mdb_client);
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
	
	public int FetchDir(int dir_id) {
		return 0;
	}
//		  int s;
//		  DirIndexEntry* index_entry = NULL;
//
//		  DirCtrlBlock* ctrl_blk = ctrl_table_->Fetch(dir_id);
//		  index_entry = index_cache_->Get(dir_id);
//		  if (index_entry == NULL) {
//		    RawLock lock(ctrl_blk);
//		    index_entry = index_cache_->Get(dir_id);
//		    if (index_entry == NULL) {
//		      DirIndex* dir_idx = NULL;
//		      s = FetchDirIndex_Unlocked(dir_id, &dir_idx);
//		      if (s.ok()) {
//		        index_entry = index_cache_->Insert(dir_idx);
//		      }
//		    }
//		  }
//
//		  if (!s.ok()) {
//		    ctrl_table_->Release(ctrl_blk);
//		    if (s.IsNotFound() && ctrl_blk->Empty()) {
//		      ctrl_table_->Evict(dir_id);
//		    }
//		    return DirGuard::DirData(NULL, NULL);
//		  }
//
//		  DLOG_ASSERT(index_entry != NULL);
//		  DLOG_ASSERT(index_entry->index->FetchDirId() == dir_id);
//		  return DirGuard::DirData(ctrl_blk, index_entry);
//		}

	public int NextInode(MetaDBService.Client mdb_client) {
		return 0;
	}
	
	
	
}
