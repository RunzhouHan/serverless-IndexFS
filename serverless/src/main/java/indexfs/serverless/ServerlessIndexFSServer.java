package main.java.indexfs.serverless;

import java.io.IOException;
//import org.apache.hadoop.hdfs.serverless.zookeeper.SyncZKClient;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class ServerlessIndexFSServer {
	/**
	 * MetaDB APIs interface.
	 */
	private ServerlessIndexFSCtx ctx;
		
	/**
	 * Directory index used to decide which server should a metadata operation should 
	 * be forwarded to.
	 */
	private static ServerlessIndexFSDirIndex didx;
	
	/**
	 * An buffer used as a write-back cache which groups a number of write operations
	 * before starts a RPC socket to a specific server.
	 */
	private ServerlessIndexFSRPCWritebackQueue queue;
			
	/**
	 * Server id to ip map. This is set to be public because it will be used in 
	 * srvless_IndexFS_server.NextInode().
	 */
	public Map<Integer, String> server_map;	
	
	InMemoryStatInfoCache cache;
	
//	/**
//	 * Cache hit count 
//	 */
//	public long cache_hit = 0;
//	
//	/**
//	 * Cache miss count 
//	 */
//	public long cache_miss = 0;
	
	private ServerlessIndexFSRPCClient[] rpc_connections;

	public int metadb_id = 0;
	
	private int obj_idx = 0;
	
	private int mdb_num;
	
	/**
	 * The concurrency lock to protect write operations.
	 * In our experiments we only have a single root folder, 
	 * so we simply lock the Mknode method when a client is using it. 
	 */
    ReadWriteLock lock = new ReentrantReadWriteLock();    
	

	/** 
	 * Constructor
	 */	
	public ServerlessIndexFSServer(ServerlessIndexFSConfig config) {
		
		this.metadb_id = config.GetSvrID();
		this.ctx = new ServerlessIndexFSCtx();
		this.server_map = config.GetMetaDBMap();
		this.mdb_num = config.GetMetaDBNum();
		this.didx = new ServerlessIndexFSDirIndex((short) this.mdb_num);
		
		this.rpc_connections = new ServerlessIndexFSRPCClient[config.GetMetaDBNum()];
		for (int i = 0; i < config.GetMetaDBNum(); i++) {
			this.rpc_connections[i] = new ServerlessIndexFSRPCClient(config, i);
			try {
				StartRPC(this.rpc_connections[i]);
				System.out.println("Connected to MetaDB server at: " + 
			    		this.rpc_connections[i].getServerAddr() + ":" + this.rpc_connections[i].getPort());
			} catch (Exception e) {
				// e.printStackTrace();
			    System.out.println("Failed to connected to MetaDB server at: " + 
			    		this.rpc_connections[i].getServerAddr() + ":" + this.rpc_connections[i].getPort());
			    System.out.println("Hint: check your MetaDB server list.");
			}
		}		
		this.queue = new ServerlessIndexFSRPCWritebackQueue(config, this.rpc_connections); // need a lock
	}
	
    
	/**
	 * Start serverless IndexFS RPC connections.
	 * @throws IOException 
	 */
	public void StartRPC(ServerlessIndexFSRPCClient rpc_connection) {
			rpc_connection.open();
	}
	
	/**
	 * Stop serverless IndexFS RPC connections.
	 * @throws IOException 
	 */
	public void StopRPC() throws IOException {
		for (int i = 0; i < (this.mdb_num-1); i++) {
			this.rpc_connections[i].close();
			System.out.println("Disconnected RPC connection with MetadDB server at: " + 
		    		this.rpc_connections[i].getServerAddr() + ":" + this.rpc_connections[i].getPort());
		}
	}
	
	
	/**
	 * Fill in StatInfo (object metadata) before put into LRU cache.
	 */
	private void fillInStat(StatInfo stat, boolean embeded, long ino, int mode) {
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
	
	
	/**
	 * Flush changes to MetaDB.
	 * @param port
	 */
	public void FlushDB(int port) {
		ServerlessIndexFSOperationParameters op = new ServerlessIndexFSOperationParameters();
		op.op_type = 5;
		queue.write_counter(-1, op);
	}
	
	/**
	 *  Send RPC to grab the next available inode number.
	 * @return inode number.
	 */
	private long NextInode(int metadb_id) {
//		return 0;
		System.out.println("NextInode(): server id: "+ metadb_id);
		System.out.println("NextInode(): server: "+ rpc_connections[metadb_id].getServerAddr());
    	return ctx.NextInode(rpc_connections[metadb_id].mdb_client);
	}
	
	/**
	 *  Creates a new file under a given directory.
	 *  REQUIRES: the specified file name must not collide with existing names.
	 * @param path
	 * @param obj_id
	 * @param perm
	 * @param port
	 */
	public void Mknod(InMemoryStatInfoCache cache, String path, OID obj_id, int perm, int port) {
				
		ServerlessIndexFSOperationParameters op = new ServerlessIndexFSOperationParameters();
		
		StatInfo stat_ = new StatInfo();
		
		// Compute the server id based on file path.
		metadb_id = didx.GetServer(path);
						
		// Put file metadata into LRU_cache.	
		fillInStat(stat_, true, NextInode(metadb_id), 0);
		
		cache.put(path, stat_.id, stat_);
					
		// file in operation parameters and put in writeback cache
		op.op_type = 0;
		op.key.parent_id_ = obj_id.dir_id;
		op.key.partition_id_ = (short) obj_idx;
		op.key.file_name_ = obj_id.obj_name;
		
//		queue.write_counter(metadb_id, op);
		ctx.newFile(rpc_connections[metadb_id].mdb_client, op.key);
		
		/**
		 *  Increase directory size.
		 *  Don't support it in serverless IndexFS for now.
		 */
		/*
		  DLOG_ASSERT(dir_guard.HasPartitionData(obj_idx));
		  dir_guard.InceaseAndGetPartitionSize(obj_idx, 1);
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
	public void Mkdir(InMemoryStatInfoCache cache, String path, OID obj_id,
		int perm, int port) {
				
		ServerlessIndexFSOperationParameters op = new ServerlessIndexFSOperationParameters();
		
		StatInfo stat_ = new StatInfo();
		
		metadb_id = didx.GetServer(path);
		
		// Get Next available inode number and fill in directory metadata
		fillInStat(stat_, false, NextInode(metadb_id), 0);
		
		// Put directory metadata into LRU_cache.	
		cache.put(path, stat_.id, stat_);
		
  
		// TASK-II: link the new directory
		op.op_type = 1;
		op.key.parent_id_ = obj_id.dir_id;

		op.key.partition_id_ = (short) obj_idx;
		op.key.file_name_ = obj_id.obj_name;
		
//		queue.write_counter(metadb_id, op);
		ctx.newDirectory(rpc_connections[metadb_id].mdb_client, op.key, (short) metadb_id, stat_.id);
				
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
	public StatInfo Getattr(InMemoryStatInfoCache cache, String path, OID obj_id, int port) {
		
		
		ServerlessIndexFSOperationParameters op = new ServerlessIndexFSOperationParameters();

		// Look into LRU cache first. 
		StatInfo stat = new StatInfo();
	
		stat = cache.getByPath(path);	
		
		
		if (stat == null) {
		    // Object not in cache. Send RPC request and put metadata in LRU cache.
			System.out.println("ServerlessIndexFSServer:Getattr: " + path + ": " 
							 + " Cache miss");
			
			op.key.parent_id_ = obj_id.dir_id;
			op.key.partition_id_ = (short) obj_idx;
			op.key.file_name_ = obj_id.obj_name;
			
			int metadb_id = didx.GetServer(path);
			
			stat = ctx.GetEntry(rpc_connections[metadb_id].mdb_client, op.key);
			
			// Object is found, put it in LRU cache.
			if (stat != null) {
				cache.put(path, stat.id, stat);
			}
			// Object is not found in MetaDB.
			else {
				System.out.println(path + " doesn't exist!");
			}
		}
		else {
			// System.out.println("ServerlessIndexFSServer:Getattr: " + path + ": " + stat.id);
		}
		//	System.out.println("ServerlessIndexFSServer:Getattr: " + path + ": " 
		//				+ stat.id + " Cache hit: " + this.cache_hit);
		//	System.out.println("ServerlessIndexFSServer:Getattr: " + path + ": " 
		//				+ stat.id + " Cache miss: " + this.cache_miss);
		//	if ((this.cache_hit+this.cache_miss)%1000 == 0)
		//		System.out.println("Cache hit rate = " + this.cache_hit + "/" + (this.cache_hit+this.cache_miss) 
		//				+ "=" + this.cache_hit/(this.cache_miss+this.cache_hit));

		return stat;
	}
	
	
	/**
	 * Helper function for Chown and Chmod.
	 */
	/*
	private void SetDirAttr(OID obj_id, short obj_idx,
	        DirGuard& dir_guard, StatInfo info) {

	}
	*/
	
		
	/**
	 * Changes the ownership of a given file system object.
	 * REQUIRES: the specified name must map to an existing object.
	 */
	public void Chown(String path, OID obj_id, short uid, short gid, int port) {
		/*
		  MonitorHelper helper(oChown, monitor_);
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
