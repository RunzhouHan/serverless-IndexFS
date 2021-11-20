package edu.cmu.pdl.indexfs.srvless;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Iterator;

public class Cache {
	
    public class Object {
    	public String obj_name;
    	public int path_depth;
    	public long dir_id;
    	public long inode_no;
    	public int perm;
    	public int uid;
    	public int gid;
//    	public int zeroth_server;
    	public long lease_due;
    }
	
    // store keys of cache
    private Deque<Object> doublyQueue;
 
    // store references of key in cache
    private HashSet<String> hashSet;
 
    // maximum capacity of cache
    private final int CACHE_SIZE;
    
    
	/* Constructor */
	Cache(int capacity) {
        doublyQueue = new LinkedList<>();
        hashSet = new HashSet<>();
        CACHE_SIZE = capacity;
    }	
	
    /* Refer the page within the LRU cache */
    public int refer(Object obj) {
    	int s = 0;
        if (!hashSet.contains(obj.obj_name)) {
            if (doublyQueue.size() == CACHE_SIZE) {
            	Object last = doublyQueue.removeLast();
                hashSet.remove(last.obj_name);
            }
        }
        else {/* The found page may not be always the last element, even if it's an
               intermediate element that needs to be removed and added to the start
               of the Queue */
            doublyQueue.remove(obj);
        }
        doublyQueue.push(obj);
        hashSet.add(obj.obj_name);
        return s;
    }
    
}
