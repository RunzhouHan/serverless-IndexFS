package main.java.indexfs.serverless;

import java.lang.Math;

public class DirIndex {	
	
	private static DirIndexPolicy policy_;
	public Config config_;
	
	public static class BytesRef {
		public long[] fname;
		public int seed;
	}
	
	/* Constructor */
	public DirIndex(short srv_num) {
		policy_ = new DirIndexPolicy();
		policy_.num_servers_ = srv_num;
		policy_.max_vs_ = policy_.num_servers_;
	}
	
	// Convert char array to long
	private static long[] char2long(String path_) {
		char[] path = path_.toCharArray();
		long[] fname_l = new long[path.length];
	    for (int i = 0; i < path.length; i++) {
		fname_l[i] = path[i] | (path[i] << 8) | (path[i] << 16) | 
				(path[i] << 24) | (path[i] << 32) | (path[i] << 40) |
				(path[i] << 48) | (path[i] << 56);
	    }
		return fname_l;
	}
	
	// 8-byte (64-bit) Murmur hash
	public static int MurmurHash(BytesRef value) {
		return MurmurHash3.MurmurHash3_x64_32(value.fname, value.seed);
	}
	
	// Convert int32 to long
	public static long ToUnsignedInt(int hash) {
		long unsigned_hash = hash & 0xffffffffL;
		return unsigned_hash;
	}
	
	// Calculate the hash value for a given string.
	// Return hash value
	//
	public static long GetNameHash(String path) {
		BytesRef value = new BytesRef();
		value.fname = char2long(path);
		value.seed = 9002;
		int hash = MurmurHash(value);
		return ToUnsignedInt(hash);
	}
	
	// Calculate the server id for a given hash value	
	public static short ComputeSrvIDFromHash(long hash) {
		double p = hash / Math.pow(2, 32);
		for (short i = 0; i< policy_.num_servers_ ; i++) {
	        if (i/(float)policy_.num_servers_ <= p && (i+1)/(float)policy_.num_servers_ > p) {
	            return i;
	        }
	    }
		return policy_.num_servers_;
	}
	
	// Calculate the server id for a given file name
	public short GetServer(final String path) {
	  long hash = GetNameHash(path);
	  short srv_id = ComputeSrvIDFromHash(hash);
	  return srv_id;
	}
}


