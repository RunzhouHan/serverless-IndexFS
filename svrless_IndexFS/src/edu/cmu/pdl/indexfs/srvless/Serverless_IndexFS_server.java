package edu.cmu.pdl.indexfs.srvless;

import java.io.IOException;
//import org.apache.hadoop.hdfs.serverless.zookeeper.SyncZKClient;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class Serverless_IndexFS_server {
	/**
	 * Serverless IndexFS configuration
	 */
	private Config config;
	
	/**
	 * MetaDB APIs interface.
	 */
	private Serverless_IndexFS_ctx ctx;
	
	/**
	 * TCP payload listener
	 */
//	private srvless_IndexFS_TCPlistener tcp_srv_;

	/**
	 * An HTTP server listening to incoming metadata operation requests from IndexFS client.
	 */
//	private srvless_IndexFS_HTTPlistener http_srv_;
	
	/**
	 * Directory index used to decide which server should a metadata operation should 
	 * be forwarded to.
	 */
	private static DirIndex didx;
	
	/**
	 * An buffer used as a write-back cache which groups a number of write operations
	 * before starts a RPC socket to a specific server.
	 */
	private RPC_writeback_queue queue;

	/**
	 * An LRU cache maintains metadata of most recently written/read objects.
	 */
	LinkedHashMap<String, StatInfo> cache;

	/**
	 * Stores every necessary for a metadata operation.
	 */
	private Operation_parameters op;
	
	/**
	 * Object metadata.
	 */
	private StatInfo stat;
		
	/**
	 * Server id to ip map. This is set to be public because it will be used in 
	 * srvless_IndexFS_server.NextInode().
	 */
	public Map<Integer, String> server_map;	

	/** 
	 * Constructor
	 */	
	@SuppressWarnings("serial")
	public Serverless_IndexFS_server(Config config) {
		this.config = config;
		this.ctx = new Serverless_IndexFS_ctx();
		Serverless_IndexFS_server.didx = new DirIndex(config.GetMetaDBNum());
		Serverless_IndexFS_server.didx.config_ = config;
		this.queue = new RPC_writeback_queue(config);
		
		cache = new LinkedHashMap<String, StatInfo>(config.LRU_capacity+1, .75F, true) {
	        // This method is called just after a new entry has been added
	        public boolean removeEldestEntry(Map.Entry<String, StatInfo> eldest) {
	            return size() > config.LRU_capacity;
	        }
	    };
	    
		this.server_map = config.GetMetaDBMap();
		this.op = new Operation_parameters();
		this.stat = new StatInfo();
//		this.tcp_srv_ = new srvless_IndexFS_TCPlistener();
	}
	
    /**
     * Start the TCP server. TCP is much faster than HTTP.
     * OpenWhisk currently doesn't support inbounds TCP request so we don't use it for now.
     * @throws IOException
     */
	public void StartTCPServer(int tcp_port) throws IOException {
//		tcp_srv_.start(tcp_port);
	}
	
    /**
     * Start the HTTP server.
     * @throws IOException
     */
	public void StartHTTPServer(int http_port) throws IOException {
//		http_srv_.start(http_port);
	}
    
	/**
	 * Reset operation parameters.
	 */
	private void reset_op() {
		op.op_type = 0;
		op.ino = 0;
//		op.key.file_name_ = null;
//		op.key.parent_id_ = 0;
//		op.key.partition_id_ = 0;
	}
	
	/**
	 * Fill in StatInfo (object metadata) before put into LRU cache.
	 */
	private void fillInStat(boolean embeded, long ino, int mode) {
		stat.id = ino;
		stat.size = -1;
		stat.mode = mode; 
		stat.zeroth_server = 1;
		stat.uid = -1;
		stat.gid = -1;
		stat.ctime = System.currentTimeMillis();
		stat.mtime = 0;
		stat.is_embedded = embeded;
	}
	
	
	private void OBJ_LOCK(OID obj_id) {
//		_DIR_GUARD(obj_id.dir_id, obj_id.obj_name);
	}
	
	/**
	 *  Send RPC to MetaDB to greb the next available inode number.
	 * @return inode number.
	 */
	private long NextInode(int server_id, int port) {
		long ino = 0;
		TTransport socket = new TSocket(queue.server_map.get((int)server_id),port);
		TProtocol protocol = new TBinaryProtocol(socket);
    	MetaDBService.Client mdb_client = new MetaDBService.Client(protocol);
    	try {
			socket.open();
			ino = ctx.NextInode(mdb_client);
	    	socket.close(); 
		} catch (TTransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	return ino;
	}
	
	/**
	 * Helper function for Chown and Chmod.
	 */
	/*
	private void SetDirAttr(OID obj_id, short obj_idx,
//	        DirGuard& dir_guard, 
	        							StatInfo info) {
//	  dir_guard.Lock_AssertHeld();
	  assert(info.mode);
//	  LeaseEntry* lease = NULL;
//	  lease = lease_table_->Get(obj_id);
//	  if (lease == NULL) {
//	    lease = lease_table_->New(obj_id, info);
//	  }
//	  LeaseGuard lease_guard(lease);
//	  WriteLock lock(lease, &dir_guard, ctx_->GetEnv());
	  MaybeThrowException(ctx_->Setattr_Unlocked(obj_id, obj_idx, info));
	  DLOG_ASSERT(lease->inode_no == info.id);
	  lease->uid = info.uid;
	  lease->gid = info.gid;
	  lease->perm = info.mode;
	  DLOG_ASSERT(lease->zeroth_server == info.zeroth_server);
	}
	*/
	
	/**
	 * Fetch server id from local options
	 * @return
	 */
	public int GetMyRank() {
		return config.GetSrvID();
	}

	/**
	 * Fetch the total number of servers from local options
	 * @return
	 */
	public int GetNumServers() {
		return config.GetMetaDBNum();
	}	
	
	/**
	 * Flush changes to MetaDB.
	 * @param port
	 */
	public void FlushDB(int port) {
		op.op_type = 5;
		queue.write_counter(-1, port, op);
		reset_op();
	}
	
	/**
	 *  Creates a new file under a given directory.
	 *  REQUIRES: the specified file name must not collide with existing names.
	 * @param path
	 * @param obj_id
	 * @param perm
	 * @param port
	 */
	public void Mknod(String path, OID obj_id, int perm, int port) {
		// Put file metadata into LRU_cache.	
		fillInStat(true, -1, 0);
		cache.put(path, stat);
		
		// To be removed after test.
//		System.out.println(path + ": " + stat);
		
		// To be removed after test.
//		System.out.println(path + ": " + cache.get(path));
		
		// Compute the server id based on file path.
		int server_id = didx.GetServer(path);
		int obj_idx = 0;
		OBJ_LOCK(obj_id);
		// file in operation parameters and put in writeback cache
		op.op_type = 0;
		op.key.parent_id_ = obj_id.dir_id;
		op.key.partition_id_ = (short) obj_idx;
		op.key.file_name_ = obj_id.obj_name;
		// TASK-I: link the new file
		queue.write_counter(server_id, port, op);
		reset_op();

		/**
		 *  TASK-II: increase directory size.
		 *  Don't support it in serverless IndexFS for now.
		 */
		/*
		  DLOG_ASSERT(dir_guard.HasPartitionData(obj_idx));
		  dir_guard.InceaseAndGetPartitionSize(obj_idx, 1);
	
		  TriggerDirSplitting(obj_id.dir_id, obj_idx, dir_guard);
		*/	
	}

	/** Creates a new directory under a given parent directory.
	 * The requesting client should suggest 2 servers
	 * so that one of the two may become the home server for the new directory.
	 * REQUIRES: hint servers must cover the entire virtual server space.
	 * REQUIRES: the specified directory name must not collide with existing names.
	 * @param path
	 * @param obj_id
	 * @param perm
	 * @param hint_server1
	 * @param hint_server2
	 * @param port
	 */
	public void Mkdir(String path, OID obj_id,
		int perm, int hint_server1, int hint_server2, int port) {
		
		// Get Next available inode number.
		long ino = NextInode(hint_server1, 10086);
		
		// Put directory metadata into LRU_cache.	
		fillInStat(false, ino, 0);
		cache.put(path, stat);
		reset_op();
		
		int obj_idx = 0;
		OBJ_LOCK(obj_id);
  
		// TASK-II: link the new directory
		op.op_type = 1;
		op.key.parent_id_ = obj_id.dir_id;

		op.key.partition_id_ = (short) obj_idx;
		op.key.file_name_ = obj_id.obj_name;
		queue.write_counter(hint_server1, port, op);
		reset_op();

		/**
		 *  TASK-III: install the zeroth partition.
		 *  In serverless IndexFS we temporarily don't support directory partitioning.
		 */
		/* 
		int home_srv = (U16INT(hint_server1)) % ctx_->GetNumServers();
		if (home_srv == ctx_->GetMyRank()) {
			CreateZeroth(new_inode, hint_server1);
		} else {
			RPC_CreateZeroth(rpc_, home_srv, new_inode, hint_server1);
		}
		 */
		
		/**
		 *  TASK-IV: increase the directory size. 
		 *  In serverless IndexFS we don't increase directory size.
		 */
		/*
		DLOG_ASSERT(dir_guard.HasPartitionData(obj_idx));
		dir_guard.InceaseAndGetPartitionSize(obj_idx, 1);

		TriggerDirSplitting(obj_id.dir_id, obj_idx, dir_guard);
		*/
	}

	/** 
	 * Retrieves the attributes for a given file system object (file).
	 * REQUIRES: the specified path must map to an existing object.
	 * @param path
	 * @param obj_id
	 * @param port
	 * @return StatInfo file/directory metadata.
	 */
	public StatInfo Getattr(String path, OID obj_id, int port) {
		// Look into LRU cache first. 
		StatInfo stat = cache.get(path);
		
		if (stat == null) {
		    // Object not in cache. Send RPC request and put metadata in LRU cache.
			int obj_idx = 0;
			op.op_type = 5;
			op.key.parent_id_ = obj_id.dir_id;
			op.key.partition_id_ = (short) obj_idx;
			op.key.file_name_ = obj_id.obj_name;
			
			int server_id = didx.GetServer(path);
			TTransport socket = new TSocket(server_map.get((int)server_id),port);
			TProtocol protocol = new TBinaryProtocol(socket);
	    	MetaDBService.Client mdb_client = new MetaDBService.Client(protocol);
	    	try {
				socket.open();
				stat = ctx.GetEntry(mdb_client, op.key);
		    	socket.close(); 
			} catch (TTransportException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 			
			reset_op();
			
			// Object is found, put it in LRU cache.
			if (stat != null) {
				cache.put(path, stat);
			}
			// Object is not found in MetaDB.
			else {
				System.out.println(path + " doesn't exist!");
			}
		}
		return stat;
	}
		
	/**
	 * Changes the ownership of a given file system object.
	 * REQUIRES: the specified name must map to an existing object.
	 */
	public void Chown(String path, OID obj_id, short uid, short gid, int port) {
		/*
//		  MonitorHelper helper(oChown, monitor_);
		  int obj_idx = 0;
		  OBJ_LOCK(obj_id);

		  // TASK-I: obtain object's current attributes
		  StatInfo stat = Getattr(path, obj_id, port);

		  // TASK-II: reset ownership
		  if (stat.mode == 1) {
		    SetDirAttr(obj_id, obj_idx, dir_guard, stat);
		  } else {
		    MaybeThrowException(ctx_->Setattr_Unlocked(obj_id, obj_idx, stat));
		  } */
		}

	
	/** 
	 * Changes the access permission of a given file system object.
	 * REQUIRES: the specified name must map to an existing object.
	 */
	public void Chmod(String path, OID obj_id, int perm, int port) {
	/*
	  MonitorHelper helper(oChmod, monitor_);
	  int obj_idx = 0;
	  OBJ_LOCK(obj_id);

	  // TASK-I: obtain object's current attributes
	  StatInfo stat;
	  MaybeThrowException(ctx_->Getattr_Unlocked(obj_id, obj_idx, &stat));

	  // TASK-II: reset access permission
	  if (S_ISDIR(stat.mode)) {
	    SetDirAttr(obj_id, obj_idx, dir_guard, stat);
	    return true;
	  } else {
	    MaybeThrowException(ctx_->Setattr_Unlocked(obj_id, obj_idx, stat));
	    return false;
	  }
	  */
	}
}