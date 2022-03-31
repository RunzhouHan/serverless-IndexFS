import subprocess
import requests
import time
import socket
import sys
import json


def current_milli_time():
    return round(time.time()*1000)

def tcp_server(num):
        sock = socket.socket()

        print(sys.stdout,"sock ready")

        # Bind the socket to the port
        server_address = ("127.0.0.1", 2004)

        sock.bind(server_address)

        # print(sys.stdout, "bind ready")

        # Listen for incoming connections
        sock.listen(2)

        print(sys.stdout, "listening to incoming connection requests")

        # first_request();
        # print(sys.stdout, "The first HTTP request has been sent out to serverless IndexFS");

        #connection = None

        # Wait for a connection
        try:
            connection, client_address = sock.accept()
            print(sys.stdout, "Waiting for a connection at %s/%d" % (client_address[0], client_address[1]))
            data = connection.recv(1024)
            print(sys.stdout, data)
        except:
            print(sys.stdout,"connection already set up")

        try:
            print(sys.stdout, "Sending workload to serverless IndexFS")
            # Write
            m1 = current_milli_time()
            for i in range(0, num):
                file_id = i
                file_name = 'file_' + str(i)
                file_path = '/' + file_name
                dir_id = 0
                path_depth = 0
                PARAMS = "127.0.0.1 10086 0 0 Mknod %s %d %d %s \n"% (file_path, dir_id, path_depth, file_name);
                # print(PARAMS)
                connection.sendall(bytes(PARAMS, encoding = "utf8"))

            m2 = current_milli_time()
            time_elapsed = m2-m1
            print(sys.stdout, "Finished %d file create in %d miliseconds" % (num,time_elapsed))
           
            # Read
            m1 = current_milli_time()
            for i in range(0, num):
                file_id = i
                file_name = 'file_' + str(i)
                file_path = '/' + file_name
                dir_id = 0
                path_depth = 0
                PARAMS = "127.0.0.1 10086 0 0 Getattr %s %d %d %s \n"% (file_path, dir_id, path_depth, file_name);
                connection.sendall(bytes(PARAMS, encoding = "utf8"))
                data = connection.recv(1024)
                print(sys.stdout, data)
            m2 = current_milli_time()
            time_elapsed = m2-m1
            print(sys.stdout, "Finished %d file read in %d miliseconds" % (num,time_elapsed))

        finally:
            # Clean up the connection
            connection.close()
            pass
        #except KeyboardInterrupt:
            #if connection:
                #connection.close()
                #break

def main():
        num = 10
        m1 = current_milli_time()
        tcp_server(num)
        m2 = current_milli_time()
        time_elapsed = m2-m1
        print("+ %d files creations used time(s): %d\n" % (num, time_elapsed))


if __name__ == '__main__':
        main()

