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


#define MAX_CLIENTS 10

/* tcp_socket class begin */
tcp_socket::tcp_socket(int num_of_deployments, int port, int my_rank):
    my_rank_(my_rank) {
    server_addr.sin_family=AF_INET;             // TCP/IP
    server_addr.sin_addr.s_addr=INADDR_ANY;     // server addr--permit all connection
    // server_addr.sin_addr.s_addr=inet_addr("10.128.0.10"); 
    server_addr.sin_port=htons(port);           // server port
    length = sizeof(client_addr);
    num_of_deployments_ = 0;
    opt = true;
    //initialise all client_socket[] to 0 so not checked 
    for (int i = 0; i < MAX_CLIENTS; i++)  
    {  
        client_socket[i] = 0;  
    }
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
    while(recv(conn, recv_buf, sizeof(recv_buf), 0) > 0 ){
        // cout << "Read file metadata: " << recv_buf << endl; 
        break;
    }
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

    //set master socket to allow multiple connections 
    if( setsockopt(server_sockfd, SOL_SOCKET, SO_REUSEADDR, (char *)&opt, 
          sizeof(opt)) < 0 )  
    {  
        perror("setsockopt");  
        exit(EXIT_FAILURE);  
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


    //clear the socket set 
    FD_ZERO(&readfds);  

    //add master socket to set 
    FD_SET(server_sockfd, &readfds);  
    max_sd = server_sockfd;

    //add child sockets to set 
    for (int i = 0 ; i < MAX_CLIENTS ; i++)  
    {  
        //socket descriptor 
        sd = client_socket[i];  
             
        //if valid socket descriptor then add to read list 
        if(sd > 0)  
            FD_SET( sd , &readfds);  
             
        //highest file descriptor number, need it for the select function 
        if(sd > max_sd)  
            max_sd = sd;  
    } 

    //wait for an activity on one of the sockets , timeout is NULL , 
    //so wait indefinitely 
    activity = select(max_sd + 1 , &readfds , NULL , NULL , NULL);  

    if ((activity < 0) && (errno!=EINTR))  
    {  
        printf("select error");  
    }


    while (true) {

        //If something happened on the master socket , 
        //then its an incoming connection 
        if (FD_ISSET(server_sockfd, &readfds))  
        {  
            if ((conn = accept(server_sockfd, 
                    (struct sockaddr *)&client_addr, (socklen_t*)&length))<0)  
            {  
                perror("accept");  
                exit(EXIT_FAILURE);  
            }  
             
            //inform user of socket number - used in send and receive commands 
            printf("New connection, socket fd is %d, ip is : %s, port: %d\n", 
                conn , inet_ntoa(client_addr.sin_addr), ntohs(client_addr.sin_port));  
           
            //send new connection greeting message 
            // if( send(new_socket, message, strlen(message), 0) != strlen(message) )  
            // {  
            //     perror("send");  
            // }  
            // puts("Welcome message sent successfully");  
                 
            //add new socket to array of sockets 
            for (int i = 0; i < MAX_CLIENTS; i++)  
            {  
                //if position is empty 
                if(client_socket[i] == 0 )  
                {  
                    client_socket[i] = conn;  
                    printf("Client accepted at rank %d, Adding to list of sockets as %d\n",my_rank_, i);  
                    break;  
                }  
            }  
        }

        char recv_buf[65536];
        memset(recv_buf, '\0', sizeof(recv_buf));
        char client_ip[INET_ADDRSTRLEN] = "";

        //else its some IO operation on some other socket
        for (int i = 0; i < MAX_CLIENTS; i++)  
        {  
            sd = client_socket[i];  
                 
            if (FD_ISSET(sd , &readfds))  
            {   
                
                //Check if it was for closing , and also read the 
                //incoming message 
                if ((valread = read( sd , recv_buf, 1024)) == 0)  
                {  
                    //Somebody disconnected , get his details and print 
                    getpeername(sd , (struct sockaddr*)&client_addr , \
                        (socklen_t*)&length);  
                    printf("Host disconnected , ip %s , port %d \n" , 
                          inet_ntoa(client_addr.sin_addr) , ntohs(client_addr.sin_port));  
                         
                    //Close the socket and mark as 0 in list for reuse 
                    close( sd );  
                    client_socket[i] = 0;  
                }  
                     
                //Echo back the message that came in 
                else 
                {  
                    //set the string terminating NULL byte on the end 
                    //of the data read 
                    // buffer[valread] = '\0';  
                    // send(sd , buffer , strlen(buffer) , 0 );  
                    printf("recv: %s from serverless IndexFS (%s:%d). \n", recv_buf, client_ip, ntohs(client_addr.sin_port));
                    memset(recv_buf, '\0', strlen(recv_buf));
                }  
                
                // inet_ntop(AF_INET, &client_addr.sin_addr, client_ip, INET_ADDRSTRLEN);

                // while(recv(sd, recv_buf, sizeof(recv_buf), 0) > 0 ){
                //     printf("recv: %s from serverless IndexFS (%s:%d). \n", recv_buf, client_ip, ntohs(client_addr.sin_port));
                //     memset(recv_buf, '\0', strlen(recv_buf));
                //     break;
                // }

            }  
        } 
    }



    // block on accept until positive fd or error

    // conn = accept(server_sockfd, (struct sockaddr*)&client_addr,&length);

    // if(conn<0) {
    //     perror("connect");
    //     return Status::IOError("connect error");
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
    receive(); 
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
