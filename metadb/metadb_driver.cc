// Copyright (c) 2014 The IndexFS Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file. See the AUTHORS file for names of contributors.

#include <vector>
#include <iostream>

#include "metadb/metadb_driver.h"
#include "metadb/metadb.h"

using std::cout;
using std::endl;

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
    cout << "trying to start rpc server" << endl;
    DLOG_ASSERT(rpc_mdb_ != NULL);
    rpc_mdb_->RunForever();
    cout << "rpc server started" << endl;
  }

  void Shutdown() {
    DLOG_ASSERT(rpc_mdb_ != NULL);
    rpc_mdb_->Stop();
  }

// by runzhou
  void PrepareDir() {
    PrepareDirectory(env_, config_->GetFileDir());
    PrepareDirectory(env_, config_->GetDBRootDir());
    PrepareDirectory(env_, config_->GetDBHomeDir());
    PrepareDirectory(env_, config_->GetDBSplitDir());
    // cout << "done here 1" << endl;

  	DLOG_ASSERT(mdb_ == NULL);

  	// Status s;
  	if (config_->HasOldData()) {
  	MetaDB::Repair(config_, env_);
  	}
    // cout << "done here 2" << endl;

  	// if (s.ok()) {
  	MetaDB::Open(config_, &mdb_, env_);
  	// }
    // cout << "done here 3" << endl;
  	// if (!s.ok()) {
  	// return s;
    // }
  }

  void OpenMetaDB() {
    DLOG_ASSERT(rpc_ == NULL);
    rpc_ = RPC::CreateRPC(config_);
    // DLOG_ASSERT(mdb_ == NULL); // by runzhou
    DLOG_ASSERT(rpc_mdb_ == NULL);
    rpc_mdb_ = new RPC_MetaDB(config_, mdb_); // need to redesign a RPC_Server dedicated to metadb. by runzhou
    DLOG(INFO) << "IndexFS is ready for service, listening to incoming clients ... ";
    cout << "rpc server initialized" << endl;
  }

 private:
  RPC* rpc_;
  RPC_MetaDB* rpc_mdb_;
  MetaDB* mdb_;
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
