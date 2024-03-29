import subprocess
import requests
import time
import socket
import sys
import json


def current_milli_time():
    return round(time.time()*1000)

def first_request():
        url = 'https://34.133.92.85:444/api/v1/web/guest/default/serverless_indexfs'
        i = 1
        file_id = i
        file_name = 'file_' + str(i)
        file_path = '/' + file_name
        dir_id = 0
        path_depth = 0

        OID = {'dir_id': dir_id,'path_depth':path_depth, 'obj_name': file_name};
        # send serverless function the metadb address (zeroth_server) and IndexFS client address (client_ip)
        PARAMS = {'zeroth_server':'146.148.61.223', 'zeroth_port':10086, 'instance_id':0, 'deployment_id':0, 'op_type': 'Mknod', 'path':file_path, 'OID': OID, 'client_ip': '146.148.61.223', 'client_port':2004};
        response = requests.post(url, json=PARAMS, verify=False)


def tcp_server(num):
        sock = socket.socket()

        print(sys.stdout,"sock ready")

        # Bind the socket to the port
        server_address = ("10.128.0.10", 2004)

        sock.bind(server_address)

        print(sys.stdout, "bind ready")

        # Listen for incoming connections
        sock.listen(2)

        print(sys.stdout, "listening to incoming connection requests")

        first_request();
        
        print(sys.stdout, "The first HTTP request has been sent out to serverless IndexFS");

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
            m1 = current_milli_time()
            for i in range(0, num):
                file_id = i
                file_name = 'file_' + str(i)
                file_path = '/' + file_name
                dir_id = 0
                path_depth = 0
                PARAMS = "35.223.120.109 10086 0 0 Mknod %s %d %d %s, \n"% (file_path, dir_id, path_depth, file_name);
                connection.sendall(bytes(PARAMS).encode("utf-8"))
            m2 = current_milli_time()
            time_elapsed = m2-m1
            print(sys.stdout, "Finished %d I/O requests in %d miliseconds" % (num,time_elapsed))

        except KeyboardInterrupt:
            if connection:
                connection.close()

        finally:
            # Clean up the connection
            connection.close()



def main():
        num = 1000
        m1 = current_milli_time()
        tcp_server(num)
        m2 = current_milli_time()
        time_elapsed = m2-m1
        print("+ %d files creations used time(s): %d\n" % (num, time_elapsed))


if __name__ == '__main__':
        main()

