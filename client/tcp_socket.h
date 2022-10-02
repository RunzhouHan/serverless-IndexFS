#ifndef _INDEXFS_C_CLI_TCP_SOCKET_
#define _INDEXFS_C_CLI_TCP_SOCKET_

#include <map>
#include <string>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <sys/time.h>
#include "common/common.h"
#include "c/cli_types.h"



using namespace std;

namespace indexfs {

#define MAX_CLIENTS 10

/* tcp_socket class begin */
class tcp_socket {
	public:
		tcp_socket(int num_of_deployments, int port, int my_rank);
		~tcp_socket();

		// Create a TCP connection
		Status connect(const char* msg);
		Status disconnect(int deployment);

		Status send_payload(char* path_, int deployment);
		char* receive(int deployment);
		int CheckConnection(int deployment);

		Status Mknod(int deployment, const std::string& path, const OID& oid, i16 perm);
		Status Mkdir(int deployment, const std::string& path, const OID& oid, i16 perm);
		Status Chmod(int deployment, const std::string& path, const OID& oid, i16 perm, bool* is_dir);
		Status Chown(int deployment, const std::string& path, const OID& oid, i16 uid, i16 gid, bool* is_dir);
		// char* Getattr(int deployment, const OID& oid, std::string& info);
		Status Getattr(int deployment, const std::string& path, const OID& oid, std::string& info);
		Status Flush(int deployment);

	private:
		int num_of_deployments_;
		int client_socket[30];
		//set of socket descriptors 
    	fd_set master_set;
		int master_sockfd;      // master server socket fd 
		int max_sd, sd, socket_count, new_socket, valread; 
		int active_clients;
		struct sockaddr_in server_addr;     // server info struct
		socklen_t length;
		struct sockaddr_in client_addr;
		int client; // TCP connection
		int my_rank_;
		const int MAX_BUF_LENGTH = 4096;
    	int opt;
};
/* tcp_socket class end */
}

#endif