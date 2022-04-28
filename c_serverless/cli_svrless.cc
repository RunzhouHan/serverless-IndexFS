#include "c_serverless/cli_svrless.h"
#include "c_serverless/deployment_cache.h"

#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <time.h>
#include <stdio.h>
#include <fcntl.h>

#include<iostream>

// #include "client/client.h"
#include "common/config.h"
#include "common/logging.h"

using std::string;
using std::cout;
using std::endl;
using ::indexfs::Slice;
using ::indexfs::Config;
using ::indexfs::Logger;
using ::indexfs::StatInfo;
using ::indexfs::Status;
// using ::indexfs::Client;
// using ::indexfs::ClientFactory;
using ::indexfs::LoadClientConfig;
using ::indexfs::ParseCommandLineFlags;

extern "C" {

namespace {

static
int CheckErrors(const char* func,
                const Status& s) {
  if (!s.ok()) {
    LOG(ERROR) << "(" << func << ") - " << s.ToString();
    // LOG(ERROR) << "(" << func << ") - " << "0";

    return -1;
  }
  return 0;
}
}

void idxfs_parse_arguments(int *argc, char*** argv) {
  ParseCommandLineFlags(argc, argv, true);
}

void idxfs_log_init() {
  Logger::Initialize(NULL);
}

void idxfs_log_close() {
  Logger::Shutdown();
}

std::string idxfs_mknod(const std::string path, const std::string file_name, mode_t mode) {
    Status s;
    std::string p = path;
    std::string file_path = path;
    int dir_id = 0;
    int path_depth = 0;
    std::string PARAMS;
    std::ostringstream oss;
    oss << "127.0.0.1 10086 0 0 Mknod " << file_path << " " 
    << dir_id << " " << path_depth << " "  << file_name << "\n" ;
    PARAMS = oss.str();
    return PARAMS;
}

std::string idxfs_mkdir(const std::string path, const std::string file_name, mode_t mode) {
    Status s;
    std::string file_path = path;
    int dir_id = 0;
    int path_depth = 0;
    std::string PARAMS;
    std::ostringstream oss;
    oss << "127.0.0.1 10086 0 0 Mkdir " << file_path << " " 
    << dir_id << " " << path_depth << " "  << file_name << "\n" ;
    PARAMS = oss.str();
    return PARAMS;
}

// int idxfs_rmdir(cli_t* cli, const char* path) {
//   return CheckErrors(__func__, Status::Corruption(Slice()));
// }

// int idxfs_delete(cli_t* cli, const char* path) {
//   return CheckErrors(__func__, Status::Corruption(Slice()));
// }

// int idxfs_exists(cli_t* cli, const char* path) {
//   Status s;
//   std::string p = path;
//   // Client* client = (Client*) cli->rep;
//   StatInfo info;
//   // s = client->Getattr(p, &info);
//   return CheckErrors(__func__, s);
//   return 0;
// }

// int idxfs_getinfo(cli_t* cli, const char* path, info_t* _return) {
//   Status s;
//   std::string p = path;
//   // Client* client = (Client*) cli->rep;
//   StatInfo info;
//   // s = client->Getattr(p, &info);
//   if (s.ok()) {
//     _return->mode = info.mode;
//     _return->size = info.size;
//     _return->uid = info.uid;
//     _return->gid = info.gid;
//     _return->mtime = info.mtime;
//     _return->ctime = info.ctime;
//     _return->is_dir = S_ISDIR(info.mode);
//   }
//   return CheckErrors(__func__, s);
//   return 0;
// }

// /* To be implemented with lower priority */
// int idxfs_chmod(cli_t* cli, const char* path,
//                 mode_t mode, bool* is_dir) {
//   Status s;
//   std::string p = path;
//   // Client* client = (Client*) cli->rep;
//   // s = client->Chmod(p, mode, is_dir);
//   return CheckErrors(__func__, s);
//   return 0;
// }

// /* To be implemented with lower priority */
// int idxfs_chown(cli_t* cli, const char* path,
//                 uid_t owner, gid_t group, bool* is_dir) {
//   Status s;
//   std::string p = path;
//   // Client* client = (Client*) cli->rep;
//   // s = client->Chown(p, owner, group, is_dir);
//   return CheckErrors(__func__, s);
//   return 0;
// }

namespace {
static
Config* CreateConfig(conf_t* conf) {
  if (conf == NULL) {
    return LoadClientConfig(-1, -1);
  }
  std::vector<std::string> servers;
  return LoadClientConfig(-1, -1, servers,
          conf->config_file == NULL ? "" : conf->config_file,
          conf->server_list == NULL ? "" : conf->server_list);
}
}

/* keep it */
int idxfs_destroy_client(cli_t* cli) {
  Status s;
  if (cli != NULL) {
    // Client* client = (Client*) cli->rep;
    // s = client->Dispose();
    // delete client;
    delete cli;
  }
  return CheckErrors(__func__, s);
  return 0;
}

/* keep it */ 
int idxfs_create_client(conf_t* conf, cli_t** _return) {
  Status s;
  *_return = NULL;
  Config* config = CreateConfig(conf);
  // Client* client = ClientFactory::GetClient(config);
  // s = client->Init();
  if (s.ok()) {
    cli_t* cli = new cli_t;
    // cli->rep = client;
    *_return = cli;
  } else {
    // delete client;
  }
  return CheckErrors(__func__, s);
  return 0;
}

} /* end extern "C" */
