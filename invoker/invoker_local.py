import subprocess
import requests
import time
import socket
import sys
import json

def current_milli_time():
    return round(time.time()*1000)

def tcp_server(num):
	sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

	# Bind the socket to the port
	server_address = ('localhost', 10000)
	# print >> sys.stderr, 'starting up on %s port %s' % server_address
	sock.bind(server_address)

	# Listen for incoming connections
	sock.listen(1)

	while True:
	    # Wait for a connection
	    # print >>sys.stderr, 'waiting for a connection'
	    connection, client_address = sock.accept()
	    try:
	        # print >>sys.stderr, 'connection from', client_address

	        # Receive the data in small chunks and retransmit it
	        while True:
	            data = connection.recv(512)
	            print (sys.stderr, 'received "%s"' % data)
	            if data:
	            	print (sys.stderr, 'sending data back to the client')
	            	for i in range(0, num):
		            	file_id = i
		            	file_name = 'file_' + str(i)
		            	file_path = '/' + file_name
		            	dir_id = 0
		            	path_depth = 0
		            	OID = {'dir_id': dir_id,'path_depth':path_depth, 'obj_name': file_name};
		            	PARAMS = {'zeroth_server':'35.223.120.109', 'zeroth_port':10086, 'instance_id':0, 'deployment_id':0, 'op_type': 'Mknod', 'path':file_path, 'OID': OID};
		            	data = json.dumps(PARAMS)
		            	connection.sendall(bytes(data,encoding="utf-8"))
	            else:
	                print (sys.stderr, 'no more data from', client_address)
	                break
	            
	    finally:
	        # Clean up the connection
	        connection.close()

def run_test(num):
	url = 'https://34.68.164.191:444/api/v1/web/guest/default/serverless_indexfs.json'
	for i in range(0, num):
		file_id = i
		file_name = 'file_' + str(i)
		file_path = '/' + file_name
		dir_id = 0
		path_depth = 0

		OID = {'dir_id': dir_id,'path_depth':path_depth, 'obj_name': file_name};

		PARAMS = {'zeroth_server':'35.223.120.109', 'zeroth_port':10086, 'instance_id':0, 'deployment_id':0, 'op_type': 'Mknod', 'path':file_path, 'OID': OID};
		response = requests.post(url, json=PARAMS, verify=False)
		# print(response.text)

def main():
	num = 100
	tcp_server(num)
	m1 = current_milli_time()
	run_test(num)
	m2 = current_milli_time()
	time_elapsed = m2-m1
	print("+ %d files creations used time(s): %d\n" % (num, time_elapsed))


if __name__ == '__main__':
	main()
