package edu.cmu.pdl.indexfs.srvless;

import org.apache.hadoop.hdfs.server.namenode.ServerlessNameNode;
import org.apache.hadoop.hdfs.serverless.tcpserver.NameNodeTCPClient;
import org.apache.hadoop.hdfs.serverless.zookeeper.SyncZKClient;

public class MDB_srvless_server {
	/* Serverless IndexFS configuration */
	private GetConfig config;
	
	/* HTTP listener */
	
	/* serverless IndexFS context */
	private svrless_IndexFS_ctx ctx_;
	
	private static DirIndex didx;
	private Writeback write_back;
	private Cache lru_cache;
	private Writeback.operation_param op;
	private Cache.Object object;
	
	private ServerlessNameNode namenode;
	private NameNodeTCPClient tcp_client;
	private final String NN = "NN0";
	
	/* Constructor */	
	public MDB_srvless_server(GetConfig config) {
		this.config = config;
		ctx_ = new svrless_IndexFS_ctx();
		didx = new DirIndex(config.GetSrvNum());
		didx.config_ = config;
		write_back = new Writeback(config);
		lru_cache = new Cache(config.LRU_capacity);
		op = write_back.new operation_param();
		object = lru_cache.new Object();
//		namenode = new ServerlessNameNode(null, null, NN);
//		tcp_client = new NameNodeTCPClient(NN, );
	}
	
	private void reset_op() {
		op.op_type = 0;
		op.ino = 0;
		op.key.file_name_ = null;
		op.key.parent_id_ = 0;
		op.key.partition_id_ = 0;
	}
	
	
	// Prepares the control data for a given directory
	// and performs a series of local consistency checks:
	//
	// EXCEPTION: if we don't have the index for the given directory
	// NOTE: by ``index'' we means the directory partition map
	// EXCEPTION: if we are not responsible for the specified object name
	//
	private void _DIR_GUARD(int dir_id, String obj_name) {                        
//	  DirGuard::DirData dir_data = ctx_->FetchDir(dir_id);                    \
//	  MaybeThrowUnknownDirException(dir_data);                                \
//	  DirGuard dir_guard(dir_data);                                           \
//	  DirLock lock(&dir_guard);                                               \
//	  if (!obj_name.empty()) {                                                \
//	    obj_idx = dir_guard.GetIndex(obj_name);                               \
//	    MaybeThrowRedirectException(dir_guard, obj_idx, ctx_->GetMyRank());   \
	  }

	// Obtain the directory lock.
	//
	// INPUT: directory id (int64_t)
	//
	private void DIR_LOCK(int dir_id) {
//	  int obj_idx = 0;                  \
//	  _DIR_GUARD(dir_id, std::string()) \
	}
	
	// Obtain the object lock.
	//
	// INPUT: object id structure (struct OID)
	//
	// All object locks are currently represented by parent directory locks.
	//
	private void OBJ_LOCK(OID obj_id) {
		_DIR_GUARD(obj_id.dir_id, obj_id.obj_name);
	}
	
	// Fetch server id from local options
	public int GetMyRank() {
		return config.GetSrvId();
	}

	// Fetch the total number of servers from local options
	public int GetNumServers() {
		return config.GetSrvNum();
	}
	
	/*
	private Object ResolvePath(String path) {
//		object.zeroth_server = -1;
		object.dir_id = -1;
		object.path_depth = 0;
		object.obj_name = "/";
//		if (path == null) {
//			System.out.print("Empty path");
//		}
		assert path.isEmpty(): "Empty path";
//		if (path[0] != '/') {
//			System.out.print("Relative path");
//		}
		assert path.charAt(0) != '/': "Relative path";

		if (path.length() == 1) {
			return object; // This is root, which always exists
		}

//		if (path.at(path.length() - 1) == '/') {
//			return Status::InvalidArgument("Path ends with slash");
//		}
		assert path.charAt(-1) == '/': "Path ends with slash";
		
//		object.zeroth_server = 0;
		object.dir_id = 0;
		int now = 0, last = 0, end = path.lastIndexOf("/");
		while (last < end) {
			now = path.indexOf("/", last + 1);
			if (now - last > 1) {
				object.path_depth++;
				object.obj_name = path.substring(last + 1, now - last - 1);
//				LookupEntry* entry = lookup_cache_->Get(*oid);
				lru_cache.refer(object);
				if(object.dir_id == 0) {
					
				}
					
				
				if (entry == NULL || env_->NowMicros() > entry->lease_due) {
					LookupInfo info;
					s = Lookup(*oid, *zeroth_server, &info, entry != NULL);
					if (!s.ok()) {
						lookup_cache_->Release(entry);
						return s;
					}
					if (entry == NULL) {
						entry = lookup_cache_->New(*oid, info);
					} else {
						entry->uid = info.uid;
						entry->gid = info.gid;
						entry->perm = info.perm;
						entry->lease_due = info.lease_due;
					}
					DLOG_ASSERT(entry->inode_no == info.id);
					DLOG_ASSERT(entry->zeroth_server == info.zeroth_server);
				}
				*zeroth_server = entry->zeroth_server;
				oid->dir_id = entry->inode_no;
				lookup_cache_->Release(entry);
			}ctx_NextInode()
			last = now;
		}
		oid->path_depth++;
		oid->obj_name = path.substr(end + 1);
		return s;
		}
	*/
	
	
	public void FlushDB() {
		op.op_type = 8;
		write_back.operation_counter(-1, op);
		reset_op();
	}
	
	// Creates a new file under a given directory.
	//
	// REQUIRES: the specified file name must not collide with existing names.
	//
	public void Mknod(char[] path, OID obj_id, int perm) {
		int server_id = didx.GetServer(path);
		int obj_idx = 0;
		OBJ_LOCK(obj_id);		
		op.op_type = 0;
		op.key.parent_id_ = obj_id.dir_id;
		op.key.partition_id_ = (short) obj_idx;
		op.key.file_name_ = obj_id.obj_name;
		
		// TASK-I: link the new file
		write_back.operation_counter(server_id, op);
		reset_op();

		// TASK-II: increase directory size. Don't support it in serverless function for now.
		/*
		  DLOG_ASSERT(dir_guard.HasPartitionData(obj_idx));
		  dir_guard.InceaseAndGetPartitionSize(obj_idx, 1);
	
		  TriggerDirSplitting(obj_id.dir_id, obj_idx, dir_guard);
		*/	
	}

	// Creates a new directory under a given parent directory.
	// The requesting client should suggest 2 servers
	// so that one of the two may become the home server for the new directory.
	//
	// REQUIRES: hint servers must cover the entire virtual server space.
	// REQUIRES: the specified directory name must not collide with existing names.
	//
	public void Mkdir(char[] path, OID obj_id,
			int perm, int hint_server1, int hint_server2) {
		int obj_idx = 0;
		OBJ_LOCK(obj_id);

		op.op_type = 9;
		// TASK-I: allocate a new inode number
		int new_inode = write_back.operation_counter(-1, op);
		reset_op();
  
		// TASK-II: link the new directory
		op.op_type = 1;
		op.key.parent_id_ = obj_id.dir_id;
		op.key.partition_id_ = (short) obj_idx;
		op.key.file_name_ = obj_id.obj_name;
		write_back.operation_counter(hint_server1, op);
		reset_op();

		// TASK-III: install the zeroth partition
		/* 
		int home_srv = (U16INT(hint_server1)) % ctx_->GetNumServers();
		if (home_srv == ctx_->GetMyRank()) {
			CreateZeroth(new_inode, hint_server1);
		} else {
			RPC_CreateZeroth(rpc_, home_srv, new_inode, hint_server1);
		}
		 */
		// TASK-IV: increase the directory size
		/*
		DLOG_ASSERT(dir_guard.HasPartitionData(obj_idx));
		dir_guard.InceaseAndGetPartitionSize(obj_idx, 1);

		TriggerDirSplitting(obj_id.dir_id, obj_idx, dir_guard);
		*/
	}
	
	public void GetEntry() {
		;
	}
	
}
