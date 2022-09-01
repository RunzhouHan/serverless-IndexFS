// Copyright (c) 2014 The IndexFS Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file. See the AUTHORS file for names of contributors.

#ifndef _INDEXFS_IPC_RPCIFACE_H_
#define _INDEXFS_IPC_RPCIFACE_H_

#include "common/common.h"
#include "common/config.h"
#include "common/logging.h"

#include "ipc/membset.h"
#include "thrift/MetadataIndexService.h"
#include "thrift/MetaDBService.h"


namespace indexfs {

// Internal RPC implementations
//
class MetaDBRep; // by runzhou
class CliRep_MetaDB;
class FTCliRepWrapper_MetaDB;
class CliRep;
class FTCliRepWrapper;

class RPC_MDB {
 public:

  Status Init();
  Status Shutdown();

  static RPC_MDB* CreateRPC(Config* conf) {
    return new RPC_MDB(conf);
  }

  int TotolServers() { return member_set_->TotalServers(); }

  virtual ~RPC_MDB();
  Mutex* GetMutex(int metadb_id);
  MetaDBServiceIf* GetClient(int metadb_id);
  Status GetMetaDBService(int metadb_id, MetaDBServiceIf** _return);

 private:

  explicit RPC_MDB(Config* conf, MetaDBServiceIf* self = NULL)
    : conf_(conf)
    , self_(self) {
    member_set_ = CreateStaticMemberSet(conf_);
    int total_server = member_set_->TotalServers();
    mtxes_ = new Mutex[total_server];
    clients_ = new FTCliRepWrapper_MetaDB*[total_server];
    for (int i = 0; i < total_server; i++) {
      clients_[i] = CreateClientIfNotLocal(i);
    }
  }

  Config* conf_;
  MemberSet* member_set_;
  MetaDBServiceIf* self_;

  // Advisory lock for RPC_MDB client sharing in MT contexts
  Mutex* mtxes_;

  FTCliRepWrapper_MetaDB** clients_;
  bool IsServerLocal(int metadb_id);
  FTCliRepWrapper_MetaDB* CreateClientFor(int metadb_id);       // Temporarily commented. by Runzhou
  FTCliRepWrapper_MetaDB* CreateClientIfNotLocal(int metadb_id);  // Temporarily commented. by Runzhou

  // No copy allowed
  RPC_MDB(const RPC_MDB&);
  RPC_MDB& operator=(const RPC_MDB&);
};

class RPC_Client_MetaDB {
 public:

  Status Init();
  Status Shutdown();

  RPC_Client_MetaDB(Config* conf, int metadb_id)
    : conf_(conf), metadb_id_(metadb_id), member_set_(NULL), client_(NULL) {
    member_set_ = CreateStaticMemberSet(conf_);
    client_ = CreateInternalClient();
  }

  virtual ~RPC_Client_MetaDB();

  MetaDBServiceIf* operator->();
  Status GetMetaDBService(MetaDBServiceIf** _return);

 private:

  Config* conf_;
  int metadb_id_;
  MemberSet* member_set_;

  FTCliRepWrapper_MetaDB* client_;
  FTCliRepWrapper_MetaDB* CreateInternalClient(); // Temporarily commented. by Runzhou

  // No copying allowed
  RPC_Client_MetaDB(const RPC_Client_MetaDB&);
  RPC_Client_MetaDB& operator=(const RPC_Client_MetaDB&);
};

// by Runzhou 
class RPC_MetaDB {
 public:

  void Stop();
  void RunForever();

  RPC_MetaDB(Config* conf, MetaDBServiceIf* handler)
    : conf_(conf), handler_(handler) {
    metadb_ = CreateInteralMetaDB();
  }

  virtual ~RPC_MetaDB();

 private:

  Config* conf_;
  MetaDBServiceIf* handler_; // pay attention to this. by runzhou

  // MetaDB implementation
  MetaDBRep* metadb_;
  MetaDBRep* CreateInteralMetaDB();

  // No copy allowed
  RPC_MetaDB(const RPC_MetaDB&);
  RPC_MetaDB& operator=(const RPC_MetaDB&);
};


class RPC {
 public:

  Status Init();
  Status Shutdown();

  static RPC* CreateRPC(Config* conf) {
    return new RPC(conf);
  }

  int TotolServers() { return member_set_->TotalServers(); }

  virtual ~RPC();
  Mutex* GetMutex(int srv_id);
  MetadataIndexServiceIf* GetClient(int srv_id);
  Status GetMetadataService(int srv_id, MetadataIndexServiceIf** _return);

 private:

  explicit RPC(Config* conf, MetadataIndexServiceIf* self = NULL)
    : conf_(conf)
    , self_(self) {
    member_set_ = CreateStaticMemberSet(conf_);
    int total_server = member_set_->TotalServers();
    mtxes_ = new Mutex[total_server];
    clients_ = new FTCliRepWrapper*[total_server];
    for (int i = 0; i < total_server; i++) {
      clients_[i] = CreateClientIfNotLocal(i);
    }
  }

  Config* conf_;
  MemberSet* member_set_;
  MetadataIndexServiceIf* self_;

  // Advisory lock for RPC client sharing in MT contexts
  Mutex* mtxes_;

  FTCliRepWrapper** clients_;
  bool IsServerLocal(int srv_id);
  FTCliRepWrapper* CreateClientFor(int srv_id);
  FTCliRepWrapper* CreateClientIfNotLocal(int srv_id);

  // No copy allowed
  RPC(const RPC&);
  RPC& operator=(const RPC&);
};

class RPC_Client {
 public:

  Status Init();
  Status Shutdown();

  RPC_Client(Config* conf, int srv_id)
    : conf_(conf), srv_id_(srv_id), member_set_(NULL), client_(NULL) {
    member_set_ = CreateStaticMemberSet(conf_);
    client_ = CreateInternalClient();
  }

  virtual ~RPC_Client();

  MetadataIndexServiceIf* operator->();
  Status GetMetadataService(MetadataIndexServiceIf** _return);

 private:

  Config* conf_;
  int srv_id_;
  MemberSet* member_set_;

  FTCliRepWrapper* client_;
  FTCliRepWrapper* CreateInternalClient();

  // No copying allowed
  RPC_Client(const RPC_Client&);
  RPC_Client& operator=(const RPC_Client&);
};


} /* namespace indexfs */

#endif /* _INDEXFS_IPC_RPCIFACE_H_ */
