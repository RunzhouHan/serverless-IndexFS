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
#include <unordered_map>

namespace indexfs {



/* tcp_socket class begin */
tcp_socket::tcp_socket(int num_of_deployments, int port, int my_rank):
    my_rank_(my_rank) {
    server_addr.sin_family=AF_INET;             // TCP/IP
    server_addr.sin_addr.s_addr=INADDR_ANY;     // server addr--permit all connection
    // server_addr.sin_addr.s_addr=inet_addr("10.128.0.10"); 
    server_addr.sin_port=htons(port);           // server port
    length = sizeof(client_addr);
    num_of_deployments_ = 0;
    active_clients = 0;
    opt = true;
    //initialise all client_socket[] to 0 so not checked 
    for (int i = 0; i < MAX_CLIENTS; i++)  
    {  
        client_socket[i] = 0;  
    }
}


/* send out kill signal */
Status tcp_socket::disconnect(int deployment) {
    Status s = send_payload("\n", deployment);
    return s;
}

/* send out serialized payload */
Status tcp_socket::send_payload(char* path_, int deployment) {
    string path(path_);

    send(client_socket[deployment], path.c_str(), path.size()+1, MSG_CONFIRM); // Send the std::string data 

    // send(client, path.c_str(), path.size()+1, MSG_CONFIRM); // Send the std::string data 


    return Status::OK();
}


char* tcp_socket::receive(int deployment) {
    char recv_buf[MAX_BUF_LENGTH];
    while(recv(client_socket[deployment], recv_buf, sizeof(recv_buf), 0) > 0 ){
    // while(recv(client, recv_buf, sizeof(recv_buf), 0) > 0 ){
        break;
    }
    cout << "Read file metadata: " << recv_buf << endl; 
    return recv_buf;
}


int tcp_socket::CheckConnection(int deployment) {
  int error = 0;
  socklen_t len = sizeof (error);

  // int retval = 0; 
  
  // int retval = getsockopt(master_sockfd, SOL_SOCKET, SO_ERROR, &error, &len);

  int retval = getsockopt(client_socket[deployment], SOL_SOCKET, SO_ERROR, &error, &len);

  // int retval = getsockopt(client, SOL_SOCKET, SO_ERROR, &error, &len);


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
    if((master_sockfd=socket(PF_INET,SOCK_STREAM,0))<0) {  
                    perror("socket error");
                    return Status::IOError("socket error");
    }

    //set master_set socket to allow multiple connections 
    if( setsockopt(master_sockfd, SOL_SOCKET, SO_REUSEADDR, (char *)&opt, 
          sizeof(opt)) < 0 )  
    {  
        perror("setsockopt");  
        exit(EXIT_FAILURE);  
    }  

    /* bind socket with server addr */
    if(bind(master_sockfd,(struct sockaddr *)&server_addr,sizeof(struct sockaddr))<0) {
                    perror("bind error");
                    return Status::IOError("bind error");
    }

    /* listen connection request with a queue length of 20 */
    if(listen(master_sockfd,20)<0) {
                    perror("listen error");
                    return Status::IOError("listen error");
    }
    printf("listen success.\n");

    
    //clear the socket set 
    FD_ZERO(&master_set);  

    //add master_set socket to set 
    FD_SET(master_sockfd, &master_set);  
    // max_sd = master_sockfd;

    max_sd = master_sockfd;  

    client_socket[0] = master_sockfd;

    // //add child sockets to set 
    for (int i = 1 ; i < MAX_CLIENTS ; i++)  
    {  
        //socket descriptor 
        sd = client_socket[i];  
             
        //if valid socket descriptor then add to read list 
        if(sd > 0)  
            FD_SET( sd , &master_set);  
             
        //highest file descriptor number, need it for the select function 
        if(sd > max_sd)  
            max_sd = sd;  
    }


    while (true) {

        fd_set copy = master_set;

        // printf("waiting for select\n");

        socket_count = select(max_sd + 1, &copy , NULL , NULL , NULL);  

        // printf("select finished\n");

        // printf("socket_count: %d\n", socket_count);


        // for (int i = 0; i < socket_count; i++) {  
            // sd = client_socket[i];  

            // if (sd == master_sockfd) {  

                if ((client = accept(master_sockfd, 
                        (struct sockaddr *)&client_addr, (socklen_t*)&length))<0)  
                {  
                    perror("accept");  
                    exit(EXIT_FAILURE);  
                }  

                FD_SET(client, &master_set);

                client_socket[active_clients] = client;


                active_clients++;


                // printf("active clients: %d\n", active_clients);

                printf("New connection, socket fd is %d, ip is : %s, port: %d\n", 
                    client , inet_ntoa(client_addr.sin_addr), ntohs(client_addr.sin_port));  
            // }

            // else {
            // socket_count = select(max_sd + 1, &copy , NULL , NULL , NULL);  

                // printf("Incoming I/O from serverless function\n");

                char recv_buf[2048];
                memset(recv_buf, '\0', sizeof(recv_buf));
                char client_ip[INET_ADDRSTRLEN] = "";
                inet_ntop(AF_INET, &client_addr.sin_addr, client_ip, INET_ADDRSTRLEN);

                if (num_of_deployments_ == 0) {
                    while(recv(client, recv_buf, sizeof(recv_buf), 0) > 0 ){
                        printf("recv: %s from serverless IndexFS (%s:%d). \n", recv_buf, client_ip, ntohs(client_addr.sin_port));
                        break;
                    }
                    num_of_deployments_ = atoi(recv_buf);
                    // printf("num_of_deployments: %d\n", num_of_deployments_);
                    memset(recv_buf, '\0', strlen(recv_buf));
                }
            // }
        // }
        // }
        


        if (active_clients == num_of_deployments_) {

            // send_payload("Connected to all 1 serverless function deployments", 0);

            printf("Connected to all %d serverless function deployments\n", num_of_deployments_);
            break;
        }
    }

    // /* create socket fd with IPv4 and TCP protocal*/
    // if((master_sockfd=socket(PF_INET,SOCK_STREAM,0))<0) {  
    //                 perror("socket error");
    //                 return Status::IOError("socket error");
    // }

    // /* bind socket with server addr */
    // if(bind(master_sockfd,(struct sockaddr *)&server_addr,sizeof(struct sockaddr))<0) {
    //                 perror("bind error");
    //                 return Status::IOError("bind error");
    // }

    // /* listen connection request with a queue length of 20 */
    // if(listen(master_sockfd,20)<0) {
    //                 perror("listen error");
    //                 return Status::IOError("listen error");
    // }

    // printf("listen success.\n");

    // char recv_buf[65536];
    // memset(recv_buf, '\0', sizeof(recv_buf));

    // // block on accept until positive fd or error
    // client = accept(master_sockfd, (struct sockaddr*)&client_addr,&length);

    // if(client<0) {
    //     perror("connect");
    //     return Status::IOError("connect error");
    // }

    // printf("new client accepted.\n");

    // char client_ip[INET_ADDRSTRLEN] = "";
    // inet_ntop(AF_INET, &client_addr.sin_addr, client_ip, INET_ADDRSTRLEN);

    // while(recv(client, recv_buf, sizeof(recv_buf), 0) > 0 ){
    //     printf("recv: %s from client(%s:%d). \n", recv_buf, client_ip, ntohs(client_addr.sin_port));
    //     memset(recv_buf, '\0', strlen(recv_buf));
    //     break;
    // }



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

    hash<string> hasher;
    size_t hash = hasher(path);
    int deployment_ = hash%num_of_deployments_;

    // int deployment_ = 0;


  if (!CheckConnection(deployment_)) {
    send_payload(PARAMS_, deployment_);
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

    hash<string> hasher;
    size_t hash = hasher(path);
    int deployment_ = hash%num_of_deployments_;

    // int deployment_ = 0;


  if (!CheckConnection(deployment_)) {
    send_payload(PARAMS_, deployment_);
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

    hash<string> hasher;
    size_t hash = hasher(path);
    int deployment_ = hash%num_of_deployments_;

        // int deployment_ = 0;


  if (!CheckConnection(deployment_)) {
    send_payload(PARAMS_, deployment_);
    // Temporarily make it dummy
    receive(deployment_); 
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

  if (!CheckConnection(deployment)) {
    send_payload(PARAMS_, deployment);
  }
    return Status::OK();
}


/* tcp_socket class end */

}
