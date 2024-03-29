## Copyright (c) 2014 The IndexFS Authors. All rights reserved.
## Use of this source code is governed by a BSD-style license that can be
## found in the LICENSE file. See the AUTHORS file for names of contributors.

ACLOCAL_AMFLAGS = -I m4 ${ACLOCAL_FLAGS}

## -------------------------------------------------------------------------
## IndexFS Modules
## -------------------------------------------------------------------------

SUBDIRS =

# --------------------------------
# Main Modules
# --------------------------------

SUBDIRS += lib/leveldb
SUBDIRS += thrift
SUBDIRS += common
SUBDIRS += util
SUBDIRS += ipc
SUBDIRS += metadb

SUBDIRS += client
SUBDIRS += c

# --------------------------------
# Performance Testing
# --------------------------------

if BUILD_IOTESTS
  SUBDIRS += io_test
endif

if BUILD_MDTESTS
  SUBDIRS += md_test
endif

# --------------------------------
# Client Toolkit
# --------------------------------


SUBDIRS += bin

#if BUILD_FUSE
#  SUBDIRS += fuse
#endif

## -------------------------------------------------------------------------
## Other Artifacts
## -------------------------------------------------------------------------

EXTRA_DIST =

EXTRA_DIST += bootstrap.sh
EXTRA_DIST += AUTHORS LICENSE README.md
EXTRA_DIST += sbin/idxfs.sh sbin/hdfs.sh sbin/rados.sh
EXTRA_DIST += sbin/start-cluster.sh sbin/stop-cluster.sh
EXTRA_DIST += sbin/start-idxfs.sh sbin/stop-idxfs.sh
EXTRA_DIST += sbin/tree-test.sh
EXTRA_DIST += etc/indexfs etc/hdfs-lo etc/rados-lo

## -------------------------------------------------------------------------
## Additional Makefile Actions
## -------------------------------------------------------------------------

all-local:
	ln -fs $(top_builddir)/server/indexfs_server $(top_builddir)/
	ln -fs $(top_builddir)/indexfs_server $(top_builddir)/metadata_server
	ln -fs $(top_builddir)/io_test/io_driver $(top_builddir)/
	ln -fs $(top_builddir)/fuse/fuse_main $(top_builddir)/
	ln -fs $(top_builddir)/c/.libs/libindexfs_st-$(INDEXFS_VERSION).so $(top_builddir)/
	ln -fs $(top_builddir)/c/.libs/libindexfs_mt-$(INDEXFS_VERSION).so $(top_builddir)/
	ln -fs $(top_builddir)/c_serverless/.libs/libclient_svrless-$(INDEXFS_VERSION).so $(top_builddir)/
	ln -fs $(top_builddir)/libindexfs_mt-$(INDEXFS_VERSION).so $(top_builddir)/libindexfs.so


clean-local:
	rm -f $(top_builddir)/indexfs_server
	rm -f $(top_builddir)/io_driver
	rm -f $(top_builddir)/fuse_main
	rm -f $(top_builddir)/libindexfs*.so

## -------------------------------------------------------------------------
