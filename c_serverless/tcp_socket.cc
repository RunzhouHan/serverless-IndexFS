#include "c_serverless/tcp_socket.h"

#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <unistd.h> // for close
#include <string.h>


// using namespace netinet::in;

// namespace {

tcp_socket::tcp_socket(int num_of_deployments, int port):
    num_of_deployments_(num_of_deployments)  {
    server_addr.sin_family=AF_INET;     // TCP/IP
    server_addr.sin_addr.s_addr=INADDR_ANY;     // server addr--permit all connection
    server_addr.sin_port=htons(port);       // server port
    length = sizeof(client_addr);
}

int tcp_socket::connect(const std::string& path) {

    /* create socket fd with IPv4 and TCP protocal*/
    if((server_sockfd=socket(PF_INET,SOCK_STREAM,0))<0) {  
                    perror("socket error");
                    return 1;
    }

    /* bind socket with server addr */
    if(bind(server_sockfd,(struct sockaddr *)&server_addr,sizeof(struct sockaddr))<0) {
                    perror("bind error");
                    return 1;
    }

    /* listen connection request with a queue length of 20 */
    if(listen(server_sockfd,20)<0) {
                    perror("listen error");
                    return 1;
    }
    printf("listen success.\n");

    char recv_buf[65536];
    memset(recv_buf, '\0', sizeof(recv_buf));

    // while (1) {
        printf("while! .\n");
        // block on accept until positive fd or error
        conn = accept(server_sockfd, (struct sockaddr*)&client_addr,&length);
                printf("conn = accept(server_sockfd, (struct sockaddr*)&client_addr,&length);\n");

        if(conn<0) {
            perror("connect");
            return -1;
        }

        printf("new client accepted.\n");

        char client_ip[INET_ADDRSTRLEN] = "";
        inet_ntop(AF_INET, &client_addr.sin_addr, client_ip, INET_ADDRSTRLEN);

        while(recv(conn, recv_buf, sizeof(recv_buf), 0) > 0 ){
            printf("recv: %s from client(%s:%d). \n", recv_buf, client_ip, ntohs(client_addr.sin_port));
            memset(recv_buf, '\0', strlen(recv_buf));
            break;
        }

        printf("here! .\n");

        // send_payload(path);
    // }

    return 0;
}


int tcp_socket::disconnect() {
    printf("connection closed. \n");
    close(server_sockfd);
    return 0;
}


int tcp_socket::send_payload(const std::string& path) {
  // uint32_t dataLength = htonl(path.size()); // Ensure network byte order 
  //                                                 // when sending the data length

  // send(server_sockfd,&dataLength ,sizeof(uint32_t) ,MSG_CONFIRM); // Send the data length
  send(conn,path.c_str(),path.size()+1,MSG_CONFIRM); // Send the string data 
        printf("send_payload!\n");

}



// }// end namespace
