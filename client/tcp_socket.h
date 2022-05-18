#ifndef _INDEXFS_C_CLI_TCP_SOCKET_
#define _INDEXFS_C_CLI_TCP_SOCKET_

#include "common/common.h"
#include "c/cli_types.h"
#include <netinet/in.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <string>


using namespace std;

namespace indexfs {

/* tcp_socket class begin */
class tcp_socket {
	public:
		tcp_socket(int num_of_deployments, int port);
		~tcp_socket();

		// Create a TCP connection
		Status connect(const char* msg);
		Status disconnect();

		Status send_payload(char* path_);
		char* receive();
		int CheckConnection();

		Status Mknod(int deployment, const std::string& path, const OID& oid, i16 perm);
		Status Mkdir(int deployment, const std::string& path, const OID& oid, i16 perm);
		Status Chmod(int deployment, const std::string& path, const OID& oid, i16 perm, bool* is_dir);
		Status Chown(int deployment, const std::string& path, const OID& oid, i16 uid, i16 gid, bool* is_dir);
		// char* Getattr(int deployment, const OID& oid, std::string& info);
		Status Getattr(int deployment, const std::string& path, const OID& oid, std::string& info);
		Status Flush(int deployment);

	private:
		int num_of_deployments_;
		int server_sockfd;      // server socket fd 
		struct sockaddr_in server_addr;     // server info struct
		socklen_t length;
		struct sockaddr_in client_addr;
		int conn; // TCP connection
};
/* tcp_socket class end */
}

#endif