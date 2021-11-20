package edu.cmu.pdl.indexfs.srvless;

import java.lang.Math;

public class DirIndex {	
	
	private static DirIndexPolicy policy_;
	public GetConfig config_;
	
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
	private static long[] char2long(char[] fname) {
		long[] fname_l = new long[fname.length];
	    for (int i = 0; i < fname.length; i++) {
		fname_l[i] = fname[i] | (fname[i] << 8) | (fname[i] << 16) | 
				(fname[i] << 24) | (fname[i] << 32) | (fname[i] << 40) |
				(fname[i] << 48) | (fname[i] << 56);
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
	public static long GetNameHash(char[] fname) {
		BytesRef value = new BytesRef();
		value.fname = char2long(fname);
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
	public short GetServer(final char[] fname) {
	  long hash = GetNameHash(fname);
	  short srv_id = ComputeSrvIDFromHash(hash);
	  return srv_id;
	}
}


