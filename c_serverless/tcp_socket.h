#ifndef _INDEXFS_C_CLI_TCP_SOCKET_
#define _INDEXFS_C_CLI_TCP_SOCKET_

#include "c/cli_types.h"
#include <netinet/in.h>
#include <string> 


// namespace {

class tcp_socket {
	public:
		tcp_socket(int num_of_deployments, int port);
		~tcp_socket();

		// Create a TCP connection
		int connect(const std::string& pat);

		int disconnect();

		int send_payload(const std::string& path);

		// int idxfs_mknod(cli_t* cli, const char* path, mode_t mode);

		// int idxfs_mkdir(cli_t* cli, const char* path, mode_t mode);

		// int idxfs_rmdir(cli_t* cli, const char* path);

		// int idxfs_delete(cli_t* cli, const char* path);

		// int idxfs_exists(cli_t* cli, const char* path);

		// int idxfs_getinfo(cli_t* cli, const char* path, info_t* _return);

		// /* To be implemented with lower priority */
		// int idxfs_chmod(cli_t* cli, const char* path, mode_t mode, bool* is_dir);
		// /* To be implemented with lower priority */
		// int idxfs_chown(cli_t* cli, const char* path, uid_t owner, gid_t group, bool* is_dir);

	private:
		int num_of_deployments_;
		int server_sockfd;      // server socket fd 
		struct sockaddr_in server_addr;     // server info struct
		socklen_t length;
		struct sockaddr_in client_addr;
		int conn; // TCP connection
};

// } // end namespace

#endif