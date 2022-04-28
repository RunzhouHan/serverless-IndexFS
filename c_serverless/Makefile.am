## -------------------------------------------------------------------------
## C/CXX Flags
## -------------------------------------------------------------------------

COMM_FLAGS =
COMM_FLAGS += $(BACKEND_FLAGS) $(SNAPPY_FLAGS)
COMM_FLAGS += $(PLATFORM) -DLEVELDB_PLATFORM_POSIX

AM_CFLAGS = $(EXTRA_INCLUDES) $(COMM_FLAGS) $(EXTRA_CFLAGS)
AM_CXXFLAGS = $(EXTRA_INCLUDES) $(COMM_FLAGS) $(EXTRA_CFLAGS)

## -------------------------------------------------------------------------
## Headers
## -------------------------------------------------------------------------

noinst_HEADERS =
noinst_HEADERS += cli_svrless.h
noinst_HEADERS += tcp_socket.h


## -------------------------------------------------------------------------
## C Interface
## -------------------------------------------------------------------------
# nobase_lib_LTLIBRARIES = 
# nobase_lib_LTLIBRARIES += libclient_svrless.la

# libclient_svrless_la_SOURCES = cli_svrless.cc

# libclient_svrless_la_LIBADD =
# libclient_svrless_la_LIBADD += $(top_builddir)/common/libcommon_idxfs.la
# libclient_svrless_la_LIBADD += $(top_builddir)/lib/leveldb/libleveldb.la

# libclient_svrless_la_LDFLAGS = -release $(INDEXFS_VERSION)


## -------------------------------------------------------------------------
## Test Programs
## -------------------------------------------------------------------------

nobase_bin_PROGRAMS =
nobase_bin_PROGRAMS += invoker
#nobase_bin_PROGRAMS += tcp_socket


invoker_SOURCES =
invoker_SOURCES += cli_svrless.cc
invoker_SOURCES += tcp_socket.cc
invoker_SOURCES += invoker.cc


invoker_LDADD =
invoker_LDADD += $(top_builddir)/common/libcommon_idxfs.la
invoker_LDADD += $(top_builddir)/lib/leveldb/libleveldb.la

# c_serverless_test_LDADD =
# c_serverless_test_LDADD += libclient_svrless.la

#tcp_socket_SOURCES = 
#tcp_socket_SOURCES += tcp_socket.C



