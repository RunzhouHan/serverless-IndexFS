// Copyright (c) 2014 The IndexFS Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file. See the AUTHORS file for names of contributors.

#include <vector>
#include <stdio.h>


#include "client/rpc_exec.h"
#include "client/client_impl.h"
// #include "client/deployment_cache.h"



namespace indexfs {

Status ClientImpl::Init() {
  // return rpc_->Init();
  Status s;
  socket_ = new tcp_socket(0, 2004+my_rank_, my_rank_);
  socket_->connect(" ");
  return s;
}

Status ClientImpl::FlushWriteBuffer() {
  Status s;
  int cli_id = config_->GetClientId();
  if (cli_id < 0) {
    s = Status::Corruption(Slice());
  } else {
    int srv_id = cli_id;
    for (; srv_id < config_->GetSrvNum(); srv_id += config_->GetNumClients()) {
      // rpc_->GetClient(srv_id)->FlushDB();
    }
  }
  return s;
}


namespace {
static
Status TCP_Flush(tcp_socket* socket, int deployment) {
  Status s;
  try {
    socket->Flush(deployment);
  } catch (ServerInternalError &ae) {
    s = Status::Corruption(Slice());
  }
  return s;
}
}

Status TCPEngine::Flush(int deployment) {
    return TCP_Flush(socket_, deployment);
}


Status ClientImpl::FlushWriteBufferTCP() {
  Status s;
  int deployment = 0;
  int cli_id = config_->GetClientId();
  printf("ClientImpl::FlushWriteBufferTCP: GetClientId: %d\n", cli_id);
  // if (cli_id < 0) {
  //   s = Status::Corruption(Slice());
  // } else {
    printf("ClientImpl::FlushWriteBufferTCP: here\n");
    // int srv_id = 0;
    // for (; srv_id < config_->GetSrvNum(); srv_id += config_->GetNumClients()) {
      // rpc_->GetClient(srv_id)->FlushDB();
      s = TCPEngine(deployment, socket_).Flush(deployment);
      printf("ClientImpl::FlushWriteBufferTCP: Flush\n");
  //   }
  // }
  return s;
}

Status ClientImpl::Dispose() {
# ifndef NDEBUG
  if (!mknod_bufmap_.empty()) {
    DLOG(WARNING) << "Client closed with un-flushed mknod_buffer";
  }
# endif
  // return rpc_->Shutdown();
  return socket_->disconnect(0);
}

// -------------------------------------------------------------
// Ping
// -------------------------------------------------------------

namespace {
static
Status RPC_Ping(RPC* rpc, int srv) {
  Status s;
  try {
    rpc->GetClient(srv)->Ping();
  } catch (ServerInternalError &ie) {
    s = Status::Corruption(ie.message);
  }
  return s;
}
}

Status ClientImpl::Noop() {
  Status s;
  for (int i = 0; s.ok() && i < config_->GetSrvNum(); ++i) {
    s = RPC_Ping(rpc_, i);
  }
  return s;
}

// -------------------------------------------------------------
// Lookup
// -------------------------------------------------------------

namespace {
static
Status RPC_Renew(RPC* rpc, int srv, const OID& oid, LookupInfo* info) {
  Status s;
  try {
    rpc->GetClient(srv)->Renew(*info, oid);
  } catch (FileNotFoundException &nf) {
    s = Status::NotFound(Slice());
  } catch (DirectoryExpectedError &de) {
    s = Status::InvalidArgument("Not a directory");
  }
  return s;
}
}

Status RPCEngine::Renew(const OID& oid, LookupInfo* info) {
  EXEC_WITH_RETRY_TRY() {
    return RPC_Renew(rpc_, srv_id_, oid, info);
  }
  EXEC_WITH_RETRY_CATCH();
}

namespace {
static
Status RPC_Access(RPC* rpc, int srv, const OID& oid, LookupInfo* info) {
  Status s;
  try {
    rpc->GetClient(srv)->Access(*info, oid);
  } catch (FileNotFoundException &nf) {
    s = Status::NotFound(Slice());
  } catch (DirectoryExpectedError &de) {
    s = Status::InvalidArgument("Not a directory");
  }
  return s;
}
}

Status RPCEngine::Access(const OID& oid, LookupInfo* info) {
  EXEC_WITH_RETRY_TRY() {
    return RPC_Access(rpc_, srv_id_, oid, info);
  }
  EXEC_WITH_RETRY_CATCH();
}

// Retrieve path lookup info from a server. In order to correctly
// route request to the right server, parent directory's index data
// may need to be retrieved first when it is not currently cached at local.
//
Status ClientImpl::Lookup(const OID& oid, int16_t zeroth_server,
        LookupInfo* info, bool is_renew) {
  Status s;
  DirIndexEntry* entry = FetchIndex(oid.dir_id, zeroth_server);
  if (entry == NULL) {
    s = Status::NotFound(Slice());
  }
  if (s.ok()) {
    DirIndexGuard idx_guard(entry);
    if (is_renew) {
      s = RPCEngine(entry->index, rpc_).Renew(oid, info);
    } else {
      s = RPCEngine(entry->index, rpc_).Access(oid, info);
    }
  }
  return s;
}

// -------------------------------------------------------------
// Mknod
// -------------------------------------------------------------

namespace {
static
Status RPC_Mknod(RPC* rpc, int srv, const OID& oid, i16 perm) {
  Status s;
  try {
    rpc->GetClient(srv)->Mknod(oid, perm);
  } catch (FileAlreadyExistsException &ae) {
    s = Status::AlreadyExists(Slice());
  }
  return s;
}
}

Status RPCEngine::Mknod(const OID& oid, i16 perm) {
  EXEC_WITH_RETRY_TRY() {
    return RPC_Mknod(rpc_, srv_id_, oid, perm);
  }
  EXEC_WITH_RETRY_CATCH();
}


namespace {
static
Status TCP_Mknod(tcp_socket* socket, int deployment, const std::string& path, 
                                                const OID& oid, i16 perm) {
  Status s;
  try {
    // rpc->GetClient(srv)->Mknod(oid, perm);
    socket->Mknod(deployment, path, oid, perm);
  } catch (FileAlreadyExistsException &ae) {
    s = Status::AlreadyExists(Slice());
  }
  return s;
}
}

Status TCPEngine::Mknod(const std::string& path, const OID& oid, i16 perm) {
    return TCP_Mknod(socket_, deployment_id_, path, oid, perm);
}

Status ClientImpl::Mknod(const std::string& path, i16 perm) {
  Status s;
  OID oid;
  int16_t zeroth_server;
  s = ResolvePath_serverless(path, &oid, &zeroth_server);
  if (!s.ok()) {
    return s;
  }
  // DirIndexEntry* entry = FetchIndex(oid.dir_id, zeroth_server);
  int deployment = 0;  // to be implemented later
  // int deployment = GetDeployment();  // to be implemented later
  // if (deployment_ == NULL) {
  //   s = Status::Corruption("Missing deployment");
  // }
  if (s.ok()) {
    // DirIndexGuard idx_guard(entry);
    // s = RPCEngine(entry->index, rpc_).Mknod(oid, perm);
    s = TCPEngine(deployment, socket_).Mknod(path, oid, perm);

  }
  return s;
}

// -------------------------------------------------------------
// Mkdir
// -------------------------------------------------------------

namespace {
static
Status RPC_Mkdir(RPC* rpc, int srv, const OID& oid, i16 perm, i16 hint_srv) {
  Status s;
  try {
    rpc->GetClient(srv)->Mkdir(oid, perm, hint_srv, hint_srv);
  } catch (FileAlreadyExistsException &ae) {
    s = Status::AlreadyExists(Slice());
  }
  return s;
}
}

Status RPCEngine::Mkdir(const OID& oid, i16 perm, i16 hint_srv) {
  EXEC_WITH_RETRY_TRY() {
    return RPC_Mkdir(rpc_, srv_id_, oid, perm, hint_srv);
  }
  EXEC_WITH_RETRY_CATCH();
}


namespace {
static
Status TCP_Mkdir(tcp_socket* socket, int deployment, const std::string& path,  
                                                  const OID& oid, i16 perm) {
  Status s;
  try {
    // rpc->GetClient(srv)->Mknod(oid, perm);
    socket->Mkdir(deployment, path, oid, perm);
  } catch (FileAlreadyExistsException &ae) {
    s = Status::AlreadyExists(Slice());
  }
  return s;
}
}

Status TCPEngine::Mkdir(const std::string& path, const OID& oid, i16 perm) {
    return TCP_Mkdir(socket_, deployment_id_, path, oid, perm);
}

Status ClientImpl::Mkdir(const std::string& path, i16 perm) {
  Status s;
  OID oid;
  int16_t zeroth_server;
  s = ResolvePath_serverless(path, &oid, &zeroth_server);
  if (!s.ok()) {
    return s;
  }
  // DirIndexEntry* entry = FetchIndex(oid.dir_id, zeroth_server);
  int deployment = 0;  // to be implemented later
  // int deployment = GetDeployment();  // to be implemented later  
  // if (deployment_ == NULL) {
  //   s = Status::Corruption("Missing deployment");
  // }
  if (s.ok()) {
    // DirIndexGuard idx_guard(entry);
    s = TCPEngine(deployment, socket_).Mkdir(path, oid, perm);
  }
  return s;
}

// -------------------------------------------------------------
// Chmod
// -------------------------------------------------------------

namespace {
static
Status RPC_Chmod(RPC* rpc, int srv,
        const OID& oid, i16 perm, bool* is_dir) {
  Status s;
  try {
    bool r = rpc->GetClient(srv)->Chmod(oid, perm);
    if (is_dir != NULL) {
      *is_dir = r;
    }
  } catch (FileNotFoundException &nf) {
    s = Status::NotFound(Slice());
  }
  return s;
}
}

Status RPCEngine::Chmod(const OID& oid, i16 perm, bool* is_dir) {
  EXEC_WITH_RETRY_TRY() {
    return RPC_Chmod(rpc_, srv_id_, oid, perm, is_dir);
  }
  EXEC_WITH_RETRY_CATCH();
}

Status ClientImpl::Chmod(const std::string& path,
        i16 perm, bool* is_dir) {
  Status s;
  OID oid;
  int16_t zeroth_server;
  s = ResolvePath(path, &oid, &zeroth_server);
  if (!s.ok()) {
    return s;
  }
  DirIndexEntry* entry = FetchIndex(oid.dir_id, zeroth_server);
  if (entry == NULL) {
    s = Status::Corruption("Missing index");
  }
  if (s.ok()) {
    DirIndexGuard idx_guard(entry);
    s = RPCEngine(entry->index, rpc_).Chmod(oid, perm, is_dir);
  }
  return s;
}

// -------------------------------------------------------------
// Chown
// -------------------------------------------------------------

namespace {
static
Status RPC_Chown(RPC* rpc, int srv,
        const OID& oid, i16 uid, i16 gid, bool* is_dir) {
  Status s;
  try {
    bool r = rpc->GetClient(srv)->Chown(oid, uid, gid);
    if (is_dir != NULL) {
      *is_dir = r;
    }
  } catch (FileNotFoundException &nf) {
    s = Status::NotFound(Slice());
  }
  return s;
}
}

Status RPCEngine::Chown(const OID& oid, i16 uid, i16 gid, bool* is_dir) {
  EXEC_WITH_RETRY_TRY() {
    return RPC_Chown(rpc_, srv_id_, oid, uid, gid, is_dir);
  }
  EXEC_WITH_RETRY_CATCH();
}

Status ClientImpl::Chown(const std::string& path,
        i16 uid, i16 gid, bool* is_dir) {
  Status s;
  OID oid;
  int16_t zeroth_server;
  s = ResolvePath(path, &oid, &zeroth_server);
  if (!s.ok()) {
    return s;
  }
  DirIndexEntry* entry = FetchIndex(oid.dir_id, zeroth_server);
  if (entry == NULL) {
    s = Status::Corruption("Missing index");
  }
  if (s.ok()) {
    DirIndexGuard idx_guard(entry);
    s = RPCEngine(entry->index, rpc_).Chown(oid, uid, gid, is_dir);
  }
  return s;
}

// -------------------------------------------------------------
// Getattr
// -------------------------------------------------------------

namespace {
static
Status RPC_Getattr(RPC* rpc, int srv, const OID& oid, StatInfo* info) {
  Status s;
  try {
    rpc->GetClient(srv)->Getattr(*info, oid);
  } catch (FileNotFoundException &nf) {
    s = Status::NotFound(Slice());
  }
  return s;
}
}

Status RPCEngine::Getattr(const OID& oid, StatInfo* info) {
  EXEC_WITH_RETRY_TRY() {
    return RPC_Getattr(rpc_, srv_id_, oid, info);
  }
  EXEC_WITH_RETRY_CATCH();
}


namespace {
static
Status TCP_Getattr(tcp_socket* socket, int deployment, 
        const std::string& path, const OID& oid, std::string& info) {
  Status s;
  try {
    socket->Getattr(deployment, path, oid, info);
  } catch (FileNotFoundException &nf) {
    s = Status::NotFound(Slice());
  }
  return s;
}
}

Status TCPEngine::Getattr(const std::string& path, const OID& oid, 
                                                std::string& info) {
    return TCP_Getattr(socket_, deployment_id_, path, oid, info);
  }


Status ClientImpl::Getattr(const std::string& path, std::string& info) {
  Status s;
  OID oid;
  int16_t zeroth_server;
  s = ResolvePath_serverless(path, &oid, &zeroth_server);
  if (!s.ok()) {
    return s;
  }
  // DirIndexEntry* entry = FetchIndex(oid.dir_id, zeroth_server);
  int deployment = 0;
  // int deployment = GetDeployment();  // to be implemented later
  // if (deployment == NULL) {
  //   s = Status::Corruption("Missing deployment");
  // }
  if (s.ok()) {
    // DirIndexGuard idx_guard(entry);
    s = TCPEngine(deployment, socket_).Getattr(path, oid, info);
  }
  return s;
}

} // namespace indexfs
