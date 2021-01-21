// Copyright (c) 2014 The IndexFS Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file. See the AUTHORS file for names of contributors.

#ifndef _INDEXFS_METADB_DRIVER_H_
#define _INDEXFS_METADB_DRIVER_H_

// Need to add a MetadataDBIndexService.h for thrift rpc
#include "thrift/MetadataDBService.h"

#include "common/options.h"
#include "common/common.h"
#include "common/config.h"
#include "common/logging.h"


namespace indexfs {

class MetaDBDriver {
 public:
  static MetaDBDriver* NewMetaDBDriver(Env* env, Config* config);
  virtual ~MetaDBDriver() { }
  virtual void Start() = 0;
  virtual void Shutdown() = 0;
  virtual void OpenMetaDB() = 0;
  virtual void PrepareDir() = 0;

 protected:
  Env* env_;
  Config* config_;
  MetaDBDriver(Env* env, Config* config);
};

enum MetadataDBOps {
  oRepair,
  oOpen,
  oFlush,
  oGetCurrentInodeNo,
  oReserveNextInodeNo,
  oEntryExists,
  oDeleteEntry,
  oGetEntry,
  oUpdateEntry,
  oInsertEntry,
  oPutEntry,
  oPutEntryWithMode,
  oNewFile,
  oNewDirectory,
  oSetFileMode,
  oGetMapping,
  oUpdateMapping,
  oInsertMapping,
  oFetchData,
  oWriteData,
  oListEntries,
  oCreateDirScanner,
  oBulkInsert,
  oCreateLocalBulkExtractor,
  oCreateBulkExtractor,
  kNumSrvOps
};

} // namespace indexfs

#endif /* _INDEXFS_SERVER_FSDRIVER_H_ */
