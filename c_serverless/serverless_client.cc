#include "c_serverless/tcp_socket.h"
#include "c/cli_types.h"

#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h> // for close
#include <string.h>


int idxfs_mknod(cli_t* cli, const char* path, mode_t mode, int conn) {
  file_id = i
  file_name = 'file_' + str(i)
  file_path = '/' + file_name
  dir_id = 0
  path_depth = 0
  PARAMS = "127.0.0.1 10086 0 0 Mknod %s %d %d %s \n"% (file_path, dir_id, path_depth, file_name);
  send(new_socket, hello, strlen(hello), 0);
}


int main(int argc, char *argv[]) {

    int server_sockfd;      // server socket fd 
    struct sockaddr_in server_addr;     // server info struct
    server_addr.sin_family=AF_INET;     // TCP/IP
    server_addr.sin_addr.s_addr=INADDR_ANY;     // server addr--permit all connection
    server_addr.sin_port=htons(2004);       // server port

    /*创建服务器端套接字--IPv4协议，面向连接通信，TCP协议*/
    /* create socket fd with IPv4 and TCP protocal*/
    if((server_sockfd=socket(PF_INET,SOCK_STREAM,0))<0) {  
                    perror("socket error");
                    return 1;
    }

    /*将套接字绑定到服务器的网络地址上*/
    /* bind socket with server addr */
    if(bind(server_sockfd,(struct sockaddr *)&server_addr,sizeof(struct sockaddr))<0) {
                    perror("bind error");
                    return 1;
    }


    /*监听连接请求--监听队列长度为20*/
    /* listen connection request with a queue length of 20 */
    if(listen(server_sockfd,20)<0) {
                    perror("listen error");
                    return 1;
    }
    printf("listen success.\n");

    char recv_buf[65536];
    memset(recv_buf, '\0', sizeof(recv_buf));

    while (1) {
        struct sockaddr_in client_addr;
        socklen_t length = sizeof(client_addr);
        //进程阻塞在accept上，成功返回非负描述字，出错返回-1
        // block on accept until positive fd or error
        int conn = accept(server_sockfd, (struct sockaddr*)&client_addr,&length);
        if(conn<0) {
            perror("connect");
            return -1;
        }

        printf("new client accepted.\n");

        char client_ip[INET_ADDRSTRLEN] = "";
        inet_ntop(AF_INET, &client_addr.sin_addr, client_ip, INET_ADDRSTRLEN);

        while(recv(conn, recv_buf, sizeof(recv_buf), 0) > 0 ){
            printf("recv %s from client(%s:%d). \n", recv_buf, client_ip, ntohs(client_addr.sin_port));
            memset(recv_buf, '\0', strlen(recv_buf));
            break;
        }
    }

    printf("closed. \n");
    close(server_sockfd);
    return 0;
}
// namespace {

// int main(struct sockaddr_in address; int port) {
//   int server_fd, new_socket, valread;
//   int opt = 1;
//   int addrlen = sizeof(address);
//   char buffer[1024] = { 0 };
//   char* hello = "Hello from server";

//   // Creating socket file descriptor
//   if ((server_fd = socket(AF_INET, SOCK_STREAM, 0))
//       == 0) {
//       perror("socket failed");
//       exit(EXIT_FAILURE);
//   }

//   // Forcefully attaching socket to the port 8080
//   if (setsockopt(server_fd, SOL_SOCKET,
//                  SO_REUSEADDR | SO_REUSEPORT, &opt,
//                  sizeof(opt))) {
//       perror("setsockopt");
//       exit(EXIT_FAILURE);
//   }
//   address.sin_family = AF_INET;
//   address.sin_addr.s_addr = INADDR_ANY;
//   address.sin_port = htons(PORT);

//   // Forcefully attaching socket to the port 8080
//   if (bind(server_fd, (struct sockaddr*)&address,
//            sizeof(address))
//       < 0) {
//       perror("bind failed");
//       exit(EXIT_FAILURE);
//   }
//   if (listen(server_fd, 3) < 0) {
//       perror("listen");
//       exit(EXIT_FAILURE);
//   }
//   if ((new_socket
//        = accept(server_fd, (struct sockaddr*)&address,
//                 (socklen_t*)&addrlen))
//       < 0) {
//       perror("accept");
//       exit(EXIT_FAILURE);
//   }
//   valread = read(new_socket, buffer, 1024);
//   printf("%s\n", buffer);
//   send(new_socket, hello, strlen(hello), 0);
//   printf("Hello message sent\n");

//   return 0;
// }

// }