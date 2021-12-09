# Copyright (c) 2014 The IndexFS Authors. All rights reserved.
# Use of this source code is governed by a BSD-style license that can be
# found in the LICENSE file. See the AUTHORS file for names of contributors.

namespace cpp indexfs
namespace java edu.cmu.pdl.indexfs.srvless

// ---------------------------------------------------------------
// IndexFS types
// ---------------------------------------------------------------

typedef i16 TNumServer
typedef i64 TInodeID

struct OID {
  1: required i16 path_depth
  2: required i64 dir_id
  3: required string obj_name
}

struct OIDS {
  1: required i16 path_depth
  2: required i64 dir_id
  3: required list<string> obj_names
}

struct StatInfo {
  1: required i32 mode
  2: required i16 uid
  3: required i16 gid
  4: required i64 size
  5: required i64 mtime
  6: required i64 ctime
  7: required i64 id
  8: required i16 zeroth_server
  9: required bool is_embedded
}

struct LookupInfo {
  1: required i64 id
  2: required i16 zeroth_server
  3: required i16 perm
  4: required i16 uid
  5: required i16 gid
  6: required i64 lease_due
}

struct EntryList {
  1: required string dmap_data
  2: required list<string> entries
}

struct ScanResult {
  1: required list<string> entries
  2: required string end_key
  3: required i16 end_partition
  4: required i16 more_entries
  5: required string dmap_data
}

struct ScanPlusResult {
  1: required list<string> names
  2: required list<StatInfo> entries
  3: required string end_key
  4: required i16 end_partition
  5: required i16 more_entries
  6: required string dmap_data
}

struct OpenResult {
  1: required bool is_embedded
  2: required string data
}

struct ReadResult {
  1: required bool is_embedded
  2: required string data
}

struct WriteResult {
  1: required bool is_embedded
  2: required string link
  3: required string data
}

// struct LeaseInfo {
//   1: i64 timeout
//   2: TInodeID next_inode
//   3: TNumServer next_zeroth_server
//   4: i32 max_dirs
// }

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
// IndexFS exceptions
// ---------------------------------------------------------------

exception FileNotFoundException {
  // NO additional payload needed
}

exception FileAlreadyExistsException {
  // NO additional payload needed
}

exception UnrecognizedDirectoryError {
  // NO additional payload needed
}

exception WrongServerError {
  // NO additional payload needed
}

exception DirectoryExpectedError {
  // NO additional payload needed
}

exception FileExpectedError {
  // NO additional payload needed
}

exception IOError {
  1: required string message
}

exception ServerInternalError {
  1: required string message
}

exception IllegalPathException {
  // NO additional payload needed
}

exception ServerRedirectionException {
  1: required string dmap_data
}

exception ParentPathNotFoundException {
  1: required string parent_path
}

exception ParentPathNotDirectoryException {
  1: required string parent_path
}

// ---------------------------------------------------------------
// IndexFS metadb RPC interface
// ---------------------------------------------------------------

service MetaDBService { // MetaDBService starts.

void Flush()
  throws (1: IOError io_error,
          2: ServerInternalError srv_error)

void NewFile(1: KeyInfo_THRIFT key)
  throws (1: UnrecognizedDirectoryError unknown_dir,
          2: ServerRedirectionException srv_redirect,
          3: FileAlreadyExistsException file_exists,
          4: IOError io_error,
          5: ServerInternalError srv_error)

void NewDirectory(1: KeyInfo_THRIFT key, 2: i32 zeroth_server, 3: i64 inode_no)
  throws (1: UnrecognizedDirectoryError unknown_dir,
          2: ServerRedirectionException srv_redirect,
          3: FileAlreadyExistsException file_exists,
          4: IOError io_error,
          5: ServerInternalError srv_error)

StatInfo GetEntry(1: KeyInfo_THRIFT key)
  throws (1: UnrecognizedDirectoryError unknown_dir,
          2: ServerRedirectionException srv_redirect,
          3: FileNotFoundException not_found,
          4: IOError io_error,
          5: ServerInternalError srv_error)

void PutEntry(1: KeyInfo_THRIFT key, 2: StatInfo info)
  throws (1: UnrecognizedDirectoryError unknown_dir,
          2: ServerRedirectionException srv_redirect,
          3: FileAlreadyExistsException file_exists,
          4: IOError io_error,
          5: ServerInternalError srv_error)

i64 ReserveNextInodeNo() 
  throws (1: IOError io_error,
          2: ServerInternalError srv_error)

list<string> GetServerList()
  throws (1: ServerInternalError srv_error)

list<i32> GetPortList()
  throws (1: ServerInternalError srv_error)

} // MetaDBService ends.
