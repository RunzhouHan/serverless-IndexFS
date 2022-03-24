#!/bin/bash
#
# Copyright (c) 2014-2016 Carnegie Mellon University.
#
# All rights reserved.
#
# Use of this source code is governed by a BSD-style license that can be
# found in the LICENSE file. See the AUTHORS file for names of contributors.
#
# Please run this script at the metadb's home directory:
#   > sbin/start-cluster.sh
#
# This starts an metadb cluster consisting of multiple metadb server
# instances running on a set of machines. If metadb has been built with
# a 3rd-party backend such as HDFS or RADOS, then that backend
# will also be created to server as the underlying storage infrastructure.
# Root privilege is neither required nor recommended to run this script.
#
# Before using this script, please prepare required config files at
# etc/metadb/metadb_conf and etc/metadb/server_list.
# These files are distributed with default settings along with the source code.
# 
# Note that please use only IPv4 addresses in the server list file, as we
# currently do not support hostname or IPv6 addresses.
#

me=$0
METADB_HOME=$(cd -P -- `dirname $me`/.. && pwd -P)
METADB_CONF_DIR=${METADB_CONF_DIR:-"$METADB_HOME/etc/metadb"}

# check metadb backend type
METADB_BACKEND=`$METADB_HOME/sbin/idxfs.sh backend`
if test -z "$METADB_BACKEND"
then
  echo "Cannot determine metadb backend --oops"
  exit 1
fi
case "$METADB_BACKEND" in
  __NFS__)
    echo "Using native POXIS as the storage backend"
    ;;
  __HDFS__)
    echo "Using HDFS as the storage backend"
    ;;
  __RADOS__)
    echo "Using RADOS as the storage backend"
    ;;
  *)
    echo "Unexpected backend '$METADB_BACKEND'"
    exit 1
    ;;
esac

# check if we have the required server list
if test ! -e "$METADB_CONF_DIR/server_list"
then
  echo "Cannot find our server list file -- oops"
  echo "It is supposed to be found at $METADB_CONF_DIR/server_list"
  exit 1
fi

# check if we have the required configuration files
if test ! -e "$METADB_CONF_DIR/metadb_conf"
then
  echo "Cannot find our metadb config file -- oops"
  echo "It is supposed to be found at $METADB_CONF_DIR/metadb_conf"
  exit 1
fi

# check the location of the build directory
METADB_BUILD=$METADB_HOME
if test -d $METADB_HOME/build
then
  METADB_BUILD=$METADB_HOME/metadb
fi

# check the existence of our metadb server binary
if test ! -e $METADB_BUILD/metadb
then
  echo "Cannot find the metadb server binary -- oops"
  echo "It is supposed to be found at $METADB_BUILD/metadb_server"
  exit 1
fi

gen_fsid() {
  fsid=$(cat /dev/urandom \
    | tr -dc 'a-zA-Z0-9' \
    | fold -w 32 \
    | head -n 1)
  export fsid=$fsid
}

# prepare cluster root
# note: this must be a shared path in distributed settings
METADB_ROOT=${METADB_ROOT-"/mnt/beegfs/metadb"}
rm -rf $METADB_ROOT/*
mkdir -p $METADB_ROOT || exit 1
# check accesses
gen_fsid
echo $fsid > $METADB_ROOT/__fsid__ || exit 1

# boot hdfs is necessary
# node: this only works in stand-alone mode
if test x"$METADB_BACKEND" = x"__HDFS__"
then
  # check hdfs status
  echo "Cheking hdfs ..."
  $METADB_HOME/sbin/hdfs.sh check || exit 1

  # bootstrap hdfs first
  echo "Starting hdfs at `hostname -s` ..."
  $METADB_HOME/sbin/hdfs.sh kill &>/dev/null
  $METADB_HOME/sbin/hdfs.sh start || exit 1 # will remove old data
  sleep 5

  echo "Setup hdfs ..."
  $METADB_HOME/sbin/hdfs.sh mkdir $METADB_RUN || exit 1
fi

# boot rados if necessary
# note: this only works in stand-alone mode
if test x"$METADB_BACKEND" = x"__RADOS__"
then
  # check rados status
  echo "Checking rados ..."
  $METADB_HOME/sbin/rados.sh check || exit 1

  # initialize rados cluster
  echo "Starting rados at `hostname -s` ..."
  $METADB_HOME/sbin/rados.sh kill &>/dev/null
  $METADB_HOME/sbin/rados.sh start || exit 1 # will remove old data
  sleep 5

  # check rados health
  echo "Checking rados cluster status ..."
  $METADB_HOME/sbin/rados.sh status || exit 1
fi

report_error() {
  echo "Fail to start metadb server at $1"
  echo "Abort!"
  exit 1
}

# boot metadb cluster
for srv_addr in \
  $(cat $METADB_CONF_DIR/server_list)
do
  METADB_ID=$((${METADB_ID:-"-1"} + 1)) # starts from 0
  METADB_RUN=$METADB_ROOT/run/s$METADB_ID
  # ssh $(echo $srv_addr | cut -d':' -f1) 
  env \
      METADB_ID=$METADB_ID \
      METADB_CONF_DIR=$METADB_CONF_DIR \
      METADB_ROOT=$METADB_ROOT \
      METADB_RUN=$METADB_RUN \
      METADB_BUILD=$METADB_BUILD \
  $METADB_HOME/sbin/start-metadb.sh $srv_addr || report_error $srv_addr
done

exit 0
