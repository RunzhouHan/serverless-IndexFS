package main.java.indexfs.serverless;

public class ServerlessIndexFSDirIndexPolicy {
	// private variables
	public short num_servers_;
	public int max_vs_;

    // Return the current number of servers.
	public int NumServers() {
		return num_servers_;
	}

	// Return the maximum number of virtual servers allowed.
	public int MaxNumVirtualServers() {
		return max_vs_;
	}
}
