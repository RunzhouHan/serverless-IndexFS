#include "c_serverless/cli_svrless.h"
#include "c_serverless/tcp_socket.h"

#include <string> 
#include<iostream>
#include<ctime>
// #include <format>

// using namespace;
using std::cout;
using std::endl;
using std::string;

int main() {
    int port_base = 2004;
    int num_of_file = 10;
    tcp_socket* socket = new tcp_socket(1, port_base);

    std::cout << "tcp_socket* socket = new tcp_socket(1, port_base);" <<  std::endl;
    // Write
    std::clock_t    start;
    start = std::clock();

    socket->connect("");

    for(int i=0; i<num_of_file; i++) {
        int file_id = i;
        const std::string file_name = "file_" + std::to_string(i);
        const std::string file_path = "/" + file_name;
        int dir_id = 0;
        int path_depth = 0;
        
        std::cout << " int path_depth = 0;" <<  std::endl;

        std::string payload = idxfs_mknod(file_path, file_name, 0); 
        // socket->connect(payload);
        socket->send_payload(payload);
        //connection.sendall(bytes(PARAMS, encoding = "utf8"));
    }

    std::cout << "Time: " << (std::clock() - start) / (double)(CLOCKS_PER_SEC / 1000) << " ms" << std::endl;

    socket->disconnect();

    // m2 = current_milli_time();
    // time_elapsed = m2-m1;
    // print(sys.stdout, "Finished %d file create in %d miliseconds" % (num,time_elapsed));
   
    // Read
    // m1 = current_milli_time()
    // for i in range(0, num):
    //     file_id = i
    //     file_name = 'file_' + str(i)
    //     file_path = '/' + file_name
    //     dir_id = 0
    //     path_depth = 0
    //     PARAMS = "127.0.0.1 10086 0 0 Getattr %s %d %d %s \n"% (file_path, dir_id, path_depth, file_name);
    //     connection.sendall(bytes(PARAMS, encoding = "utf8"))
    //     data = connection.recv(1024)
    //     # print(sys.stdout, data)
    // m2 = current_milli_time()
    // time_elapsed = m2-m1
    // print(sys.stdout, "Finished %d file read in %d miliseconds" % (num,time_elapsed))
}

