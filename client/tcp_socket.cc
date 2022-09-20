#include "client/tcp_socket.h"

#include <stdio.h>
#include <sys/types.h>
#include <unistd.h> 
#include <cstring>
#include <iostream>
#include <sstream>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <fcntl.h>
#include <random>


namespace indexfs {


/* tcp_socket class begin */
tcp_socket::tcp_socket(int num_of_deployments, int port, int my_rank):
    num_of_deployments_(num_of_deployments),
    my_rank_(my_rank) {
    server_addr.sin_family=AF_INET;             // TCP/IP
    server_addr.sin_addr.s_addr=INADDR_ANY;     // server addr--permit all connection
    // server_addr.sin_addr.s_addr=inet_addr("10.128.0.10"); 
    server_addr.sin_port=htons(port);           // server port
    length = sizeof(client_addr);
}


/* send out kill signal */
Status tcp_socket::disconnect() {
    Status s = send_payload("\n");
    return s;
}

/* send out serialized payload */
Status tcp_socket::send_payload(char* path_) {
    string path(path_);
    send(conn, path.c_str(), path.size()+1, MSG_CONFIRM); // Send the std::string data 
    return Status::OK();
}


char* tcp_socket::receive() {
    char recv_buf[MAX_BUF_LENGTH];

    printf("Rank %d: receive(): %s -- before \n", my_rank_, recv_buf);

    while(recv(conn, recv_buf, sizeof(recv_buf), 0) > 0 ){
        // cout << "Read file metadata: " << recv_buf << endl; 
        break;
    }

    /*Debug*/
    printf("Rank %d: receive(): %s -- after \n", my_rank_, recv_buf);

    return recv_buf;
}


int tcp_socket::CheckConnection() {
  int error = 0;
  socklen_t len = sizeof (error);
  int retval = getsockopt(server_sockfd, SOL_SOCKET, SO_ERROR, &error, &len);

  if (retval != 0) {
    /* there was a problem getting the error code */
    fprintf(stderr, "error getting socket error code: %s\n", strerror(retval));
    return 1;
  }

  if (error != 0) {
    /* socket has a non zero error status */
    fprintf(stderr, "socket error: %s\n", strerror(error));
    return 1;
  }
  return 0;
}

Status tcp_socket::connect(const char* msg) {

    /* create socket fd with IPv4 and TCP protocal*/
    if((server_sockfd=socket(PF_INET,SOCK_STREAM,0))<0) {  
                    perror("socket error");
                    return Status::IOError("socket error");
    }

    /* bind socket with server addr */
    if(bind(server_sockfd,(struct sockaddr *)&server_addr,sizeof(struct sockaddr))<0) {
                    perror("bind error");
                    return Status::IOError("bind error");
    }

    /* listen connection request with a queue length of 20 */
    if(listen(server_sockfd,20)<0) {
                    perror("listen error");
                    return Status::IOError("listen error");
    }
    // printf("listen success.\n");

    char recv_buf[65536];
    memset(recv_buf, '\0', sizeof(recv_buf));

    // block on accept until positive fd or error
    conn = accept(server_sockfd, (struct sockaddr*)&client_addr,&length);

    if(conn<0) {
        perror("connect");
        return Status::IOError("connect error");
    }

    printf("client accepted at rank %d\n", my_rank_);

    char client_ip[INET_ADDRSTRLEN] = "";
    inet_ntop(AF_INET, &client_addr.sin_addr, client_ip, INET_ADDRSTRLEN);

    while(recv(conn, recv_buf, sizeof(recv_buf), 0) > 0 ){
        printf("recv: %s from serverless IndexFS (%s:%d). \n", recv_buf, client_ip, ntohs(client_addr.sin_port));
        memset(recv_buf, '\0', strlen(recv_buf));
        break;
    }

    return Status::OK();
}




Status tcp_socket::Mknod(int deployment, const std::string& path, 
                                        const OID& oid, i16 perm) {
  Status s;

  const std::string zeroth_server = "127.0.0.1";
  int zeroth_port_ = 10086;
  int instance_id_ = 0;
  const std::string op_ = "Mknod"; 

  std::ostringstream oss;
  oss << zeroth_server << " "
  << zeroth_port_ << " "
  << instance_id_ << " "
  << deployment << " "
  << op_ << " "
  << path << " "
  << oid.dir_id << " " 
  << oid.path_depth << " "  
  << oid.obj_name << "\n";
  
  string PARAMS;  
  PARAMS = oss.str();

  char PARAMS_[1024];
  strcpy(PARAMS_, PARAMS.c_str());

  /* Debug */
  // if (my_rank_ == 1)
    // printf("Rank %d: %s", my_rank_, PARAMS_);

  if (!CheckConnection()) {
    send_payload(PARAMS_);
    // cout << PARAMS << endl;
  }
  return Status::OK();

}


Status tcp_socket::Mkdir(int deployment, const std::string& path, 
                                        const OID& oid, i16 perm) {
  Status s;

  const std::string zeroth_server = "127.0.0.1";
  int zeroth_port_ = 10086;
  int instance_id_ = 0;
  const std::string op_ = "Mkdir"; 

  std::ostringstream oss;
  oss << zeroth_server << " "
  << zeroth_port_ << " "
  << instance_id_ << " "
  << deployment << " "
  << op_ << " "
  << path << " "
  << oid.dir_id << " " 
  << oid.path_depth << " "  
  << oid.obj_name << "\n";
  
  string PARAMS;  
  PARAMS = oss.str();


  char PARAMS_[1024];
  strcpy(PARAMS_, PARAMS.c_str());

  /* Debug */ 
  // if (my_rank_ == 1)
    // printf("Rank %d: %s", my_rank_, PARAMS_);

  if (!CheckConnection()) {
    send_payload(PARAMS_);
    // cout << PARAMS << endl;
  }
    return Status::OK();
}


Status tcp_socket::Getattr(int deployment, const std::string& path,
                                    const OID& oid, std::string& info){
  Status s;

  const std::string zeroth_server = "127.0.0.1";
  int zeroth_port_ = 10086;
  int instance_id_ = 0;
  const std::string op_ = "Getattr"; 

  std::ostringstream oss;
  oss << zeroth_server << " "
  << zeroth_port_ << " "
  << instance_id_ << " "
  << deployment << " "
  << op_ << " "
  << path << " "
  << oid.dir_id << " " 
  << oid.path_depth << " "  
  << oid.obj_name << "\n";
  
  std::string PARAMS;  
  PARAMS = oss.str();

  char PARAMS_[1024];
  strcpy(PARAMS_, PARAMS.c_str());

  /* Debug */
  // if (my_rank_ == 1)
    // printf("Rank %d: %s", my_rank_, PARAMS_);

  if (!CheckConnection()) {
    send_payload(PARAMS_);
    // Temporarily make it dummy
    // receive(); 
    return Status::OK();
  }

  cout << "Failed to fetch metadata of " << "path_" << endl;
  return Status::IOError("IOError");
}


Status tcp_socket::Flush(int deployment){
  Status s;

  const std::string zeroth_server = "127.0.0.1";
  int zeroth_port_ = 10086;
  int instance_id_ = 0;
  const std::string op_ = "FlushDB"; 

  std::ostringstream oss;
  oss << zeroth_server << " "
  << zeroth_port_ << " "
  << instance_id_ << " "
  << deployment << " "
  << op_ << " "
  << "/" << " "
  << 0 << " " 
  << 0 << " "  
  << "/" << "\n";
  
  std::string PARAMS;
  PARAMS = oss.str();

  char PARAMS_[1024];
  strcpy(PARAMS_, PARAMS.c_str());

  if (!CheckConnection()) {
    send_payload(PARAMS_);
  }
    return Status::OK();
}


/* tcp_socket class end */

}
