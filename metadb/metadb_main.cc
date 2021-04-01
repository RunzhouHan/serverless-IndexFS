// Copyright (c) 2014 The IndexFS Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file. See the AUTHORS file for names of contributors.
#include <string.h>
#include <signal.h>

// #include <iostream>

#include "common/logging.h"
#include "metadb/metadb_driver.h"

DEFINE_int32(metadbid, -1, "set indexfs server rank");


using indexfs::MetaDBDriver;
using leveldb::NewMemLinkEnv;
using indexfs::Config;
using indexfs::Env;
using indexfs::GetSystemEnv;
using indexfs::Logger;
using indexfs::LoadMetaDBConfig;
using indexfs::GetLogFileName;
using indexfs::GetDefaultLogDir;
using indexfs::SetVersionString;
using indexfs::SetUsageMessage;
using indexfs::ParseCommandLineFlags;

// using std::cout;
// using std::endl;

// Global state
// -----------------------------------------------
static Env* env = NULL;
static Config* config = NULL;
static MetaDBDriver* driver = NULL;
// -----------------------------------------------


namespace {
static
void SignalHandler(int sig) {
  if (driver != NULL) {
    driver->Shutdown();
  }
  LOG(INFO) << "Receive external signal to stop MetaDB ...";
}
}

int main(int argc, char* argv[]) {
//-----------------------------------------------------------------
  // cout << "starts here" << endl; 
  FLAGS_logfn = "indexfs_metadb";
  FLAGS_metadbid = -1;
  FLAGS_logbufsecs = 5;
  FLAGS_log_dir = GetDefaultLogDir();
  SetVersionString(PACKAGE_VERSION);
  SetUsageMessage("indexfs metadb");
  ParseCommandLineFlags(&argc, &argv, true);
  srand (FLAGS_metadbid);
  Logger::Initialize(GetLogFileName());
//-----------------------------------------------------------------
// LevelDB
  config = LoadMetaDBConfig(FLAGS_metadbid); // not implemented yet
  env = GetSystemEnv(config);
  if (config->HasOldData()) {
    // We will have to link old SSTables into
    // our current DB home
    env = NewMemLinkEnv(env);
  }
//-----------------------------------------------------------------
// fd_driver 
  driver = MetaDBDriver::NewMetaDBDriver(env, config);
  // cout << "driver Initialized" << endl;
  driver->PrepareDir();  
  // cout << "directory prepared" << endl;
  driver->OpenMetaDB();
  // cout << "metadb Initialized" << endl;
  signal(SIGINT, &SignalHandler);
  signal(SIGTERM, &SignalHandler);
  driver->Start(); // Run forever until interrupted 
  // cout << "rpc server started" << endl;
//-----------------------------------------------------------------
  MetaDBDriver* _driver_ = driver;
  driver = NULL;
  delete _driver_;
//-----------------------------------------------------------------
  Logger::Shutdown();
  DLOG(INFO) << "Everything disposed, metadb will now shutdown";
  return 0;
}