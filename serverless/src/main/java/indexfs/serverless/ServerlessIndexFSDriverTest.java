package main.java.indexfs.serverless;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ServerlessIndexFSDriverTest {

	@Test
	void test() {
//	    OID oid = new OID();
//	    // Create a directory
//	    oid.dir_id = 0;
//	    oid.obj_name = "dir_0";
//	    oid.path_depth = 0;
//	    index_srv_.Mkdir("/dir_0", oid, 0, 0, 1, 10086);
//		System.out.println("Create a directory, succeed!\n");	 
//	    // Flush
//	    index_srv_.FlushDB(10086);
//		System.out.println("Flush a directory, succeed!\n");	    
//
//		try {
//			TimeUnit.SECONDS.sleep(2);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	    // Read the directory metadata
//	    StatInfo dir_stat = index_srv_.Getattr("/dir_0", oid, 10086);
//		System.out.println("Stat a directory, succeed!\n");	    
//	    
//		// Create a number of files under the directory
//	    int n = 1000000;
//	    oid.dir_id = dir_stat.id;
//	    oid.path_depth = 1;
//		long start = System.currentTimeMillis();
//
//		for (int i = 0; i < n; i++) {
//		    oid.obj_name = "file_"+i;
//		    index_srv_.Mknod("/dir_0/"+oid.obj_name, oid, 0, 10086);
//			System.out.println("Create a file, succeed!\n");
//		}
//	    // Flush
//	    index_srv_.FlushDB(10086);
//        long finish_1 = System.currentTimeMillis();
//	    long timeElapsed_1 = finish_1 - start;
//        System.out.println("Write " + n + " files took: " + timeElapsed_1); 
//	    // Read the file metadata
//		for (int i = 0; i < n; i++) {
//		    oid.obj_name = "file_"+i;
//		    index_srv_.Getattr("/dir_0/"+oid.obj_name, oid, 10086);
//			System.out.println("Stat a file, succeed!\n");	    
//		}	    
//        long finish_2 = System.currentTimeMillis();
//	    long timeElapsed_2 = finish_2 - start;
//        System.out.println("Stat "+ n + " files took: " + timeElapsed_2); 
	}

}
