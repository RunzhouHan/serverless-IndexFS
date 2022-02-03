import subprocess
import requests
import time

def current_milli_time():
    return round(time.time()*1000)


def run_test(num):
	url = 'https://34.68.164.191:444/api/v1/web/guest/default/serverless_indexfs.json'
	for i in range(0, num):
		file_id = i
		file_name = 'file_' + str(i)
		file_path = '/' + file_name
		dir_id = 0
		path_depth = 0

		OID = {'dir_id': dir_id,'path_depth':path_depth, 'obj_name': file_name};

		PARAMS = {'ip':'35.223.120.109', 'zeroth_server':'35.223.120.109', 'zeroth_port':10086, 'instance_id':0, 'deployment_id':0, 'op_type': 'Mknod', 'path':file_path, 'OID': OID};
		response = requests.post(url, json=PARAMS, verify=False)
		# print(response.text)

def main():
	num = 1
	m1 = current_milli_time()
	run_test(num)
	m2 = current_milli_time()
	time_elapsed = m2-m1
	print("+ %d files creations used time(s): %d\n" % (num, time_elapsed))

if __name__ == '__main__':
	main()
