import socket
import sys
import json



#m ="{"id": 2, "name": "abc"}"
i=0
file_id = i
file_name = "file_" + str(i)
file_path = "/" + file_name
dir_id = 0
path_depth = 0
OID = {"dir_id": dir_id,"path_depth":path_depth, "obj_name": file_name};
m = {"zeroth_server":"35.223.120.109", "zeroth_port":10086, "instance_id":0, "deployment_id":0, "op_type": "Mknod", "path":file_path, "OID": OID};
data = json.dumps(m)


server_address = ("127.0.0.1", 2004)
sock.bind(server_address)


# Create a socket (SOCK_STREAM means a TCP socket)
sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

try:
    # Connect to server and send data
    sock.connect((HOST, PORT))
    sock.sendall(bytes(data,encoding="utf-8"))


    # Receive data from the server and shut down
    received = sock.recv(1024)
    received = received.decode("utf-8")

finally:
    sock.close()

print("Sent:     {}".format(data))
print("Received: {}".format(received))


