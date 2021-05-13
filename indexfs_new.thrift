# Copyright (c) 2014 The IndexFS Authors. All rights reserved.
# Use of this source code is governed by a BSD-style license that can be
# found in the LICENSE file. See the AUTHORS file for names of contributors.

namespace cpp indexfs
namespace java edu.cmu.pdl.indexfs.rpc

// ---------------------------------------------------------------
// MetaDB types
// ---------------------------------------------------------------

struct KeyInfo_THRIFT {
  1: required i64 parent_id_
  2: required i16 partition_id_
  3: required string file_name_
}

//struct KeyOffset {
//  1: required i64 parent_id
//  2: required i16 partition_id_
//  3: required string start_hash_
//}

// ---------------------------------------------------------------
// MetaDB exceptions
// ---------------------------------------------------------------

exception IOError {
  1: required string message
}

exception ServerInternalError {
  1: required string message
}

// ---------------------------------------------------------------
// IndexFS metadb RPC interface
// ---------------------------------------------------------------

service MetaDBService {

void NewFile(1: KeyInfo_THRIFT key)
  throws (1: IOError io_error,
          2: ServerInternalError srv_error)

void NewDirectory(1: KeyInfo_THRIFT key, 2: i16 zeroth_server, 3: i64 inode_no)
  throws (1: IOError io_error,
          2: ServerInternalError srv_error)

}
