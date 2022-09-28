import subprocess
import requests
import time
import socket
import sys
import json


def current_milli_time():
    return round(time.time()*1000)

def first_request(serverless_function_url):
        url = serverless_function_url
        i = 1
        file_id = i
        file_name = 'file_' + str(i)
        file_path = '/' + file_name
        dir_id = 0
        path_depth = 0

        OID = {'dir_id': dir_id,'path_depth':path_depth, 'obj_name': file_name};
        # send serverless function the metadb address (zeroth_server) and IndexFS client address (client_ip)
        PARAMS = {'zeroth_server':'146.148.61.223', 'zeroth_port':10086, 'instance_id':0, 'deployment_id':0, 'op_type': 'Mknod', 'path':file_path, 'OID': OID, 'client_ip': '146.148.61.223', 'client_port':2004, 'client_num':1};
        response = requests.post(url, json=PARAMS, verify=False)


def tcp_server(serverless_function_url):

        first_request(serverless_function_url)

        print(sys.stdout, "The first HTTP request has been sent out to serverless IndexFS")
        print(sys.stdout, "Listening to incoming connection requests")
        print(sys.stdout, "Waiting for starting running I/O test")

def main():
        m1 = current_milli_time()
        for serverless_function_url in sys.argv[1:]:
            tcp_server(serverless_function_url)
        m2 = current_milli_time()
        time_elapsed = m2-m1
        print("+ observed time elapsed (s): %d\n" % (time_elapsed))


if __name__ == '__main__':
        main()
