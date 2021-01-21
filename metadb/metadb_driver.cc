// Copyright (c) 2014 The IndexFS Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file. See the AUTHORS file for names of contributors.

#include <vector>

#include "ipc/rpc.h"
#include "metadb/metadb_driver.h"
#include "metadb/metadb.h"

namespace indexfs {


namespace {
static
void PrepareDirectory(Env* env,
                      const std::string& dir_name) {
  Status s;
  if (!env->FileExists(dir_name)) {
    s = env->CreateDir(dir_name);
  }
  bool dir_exists = env->FileExists(dir_name);
  CHECK(dir_exists) << "Fail to create dir: " << s.ToString();
}
}

class IndexMetaDBDriver: virtual public MetaDBDriver {
 public:

  explicit IndexMetaDBDriver(Env* env, Config* config)
    : MetaDBDriver(env, config),
    // by runzhou
      rpc_(NULL),rpc_mdb_(NULL),mdb_(NULL) {
  }

  virtual ~IndexMetaDBDriver() {
    delete rpc_;
    delete rpc_mdb_;
    delete mdb_;
  }

  void Start() {
    DLOG_ASSERT(rpc_mdb_ != NULL);
    rpc_mdb_->RunForever();
  }

  void Shutdown() {
    DLOG_ASSERT(rpc_srv_ != NULL);
    rpc_srv_->Stop();
  }

// by runzhou
  void PrepareDir() {
    PrepareDirectory(env_, config_->GetFileDir());
    PrepareDirectory(env_, config_->GetDBRootDir());
    PrepareDirectory(env_, config_->GetDBHomeDir());
    PrepareDirectory(env_, config_->GetDBSplitDir());
  }

  void OpenMetaDB() {
    DLOG_ASSERT(rpc_ == NULL);
    rpc_ = RPC::CreateRPC(config_);
    DLOG_ASSERT(metadb_ == NULL);
    mdb_ = new MetaDB();
    DLOG_ASSERT(rpc_srv_ == NULL);
    rpc_srv_ = new RPC_Server(config_, mdb_); // need to redesign a RPC_Server dedicated to metadb. by runzhou
    DLOG(INFO) << "IndexFS is ready for service, listening to incoming clients ... ";
  }

 private:
  RPC* rpc_;
  RPC_MetaDB* rpc_mdb_;
  // No copying allowed
  IndexMetaDBDriver(const IndexMetaDBDriver&);
  IndexMetaDBDriver& operator=(const IndexMetaDBDriver&);
};


MetaDBDriver::MetaDBDriver(Env* env, Config* config) :
  env_(env), config_(config) {
}

MetaDBDriver* MetaDBDriver::NewMetaDBDriver(Env* env, Config* config) {
  return new IndexMetaDBDriver(env, config);
}

} // namespace indexfs
