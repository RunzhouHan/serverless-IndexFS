#!/bin/bash
#
# Copyright (c) 2014-2016 Carnegie Mellon University.
#
# All rights reserved.
#
# Use of this source code is governed by a BSD-style license that can be
# found in the LICENSE file. See the AUTHORS file for names of contributors.
#
# Use this script to start or restart an metadb server instance.
#
# Root privilege is neither required nor recommended to run this script. 
#

me=$0
cmd=${2-"start"}
METADB_HOME=$(cd -P -- `dirname $me`/.. && pwd -P)
# METADB_BUILD=${METADB_BUILD:-"$METADB_HOME/build"}
METADB_BUILD=${METADB_BUILD:-"$METADB_HOME/metadb"}
METADB_CONF_DIR=${METADB_CONF_DIR:-"$METADB_HOME/etc/metadb"}
METADB_BACKEND=`$METADB_HOME/sbin/idxfs.sh backend`

# check metadb backend
if test -z "$METADB_BACKEND"
then
  echo "Cannot determine metadb backend -- oops"
  exit 1
fi

# check if we have the required server list
if test ! -e "$METADB_CONF_DIR/server_list"
then
  echo "Cannot find our server list file -- oops"
  echo "It is supposed to be found at $METADB_CONF_DIR/server_list"
  exit 1
else
  # ensure legacy metadb clients can work correctly
  rm -f /home/runzhou/Desktop/giga_conf
  ln -fs $METADB_CONF_DIR/server_list /home/runzhou/Desktop/giga_conf
fi

# check if we have the required configuration files
if test ! -e "$METADB_CONF_DIR/metadb_conf"
then
  echo "Cannot find our metadb config file -- oops"
  echo "It is supposed to be found at $METADB_CONF_DIR/metadb_conf"
  exit 1
else
  # ensure legacy metadb clients can work correctly
  rm -f /home/runzhou/Desktop/idxfs_conf
  ln -fs $METADB_CONF_DIR/metadb_conf /home/runzhou/Desktop/idxfs_conf
fi

METADB_ID=${METADB_ID:-"0"}
METADB_ROOT=${METADB_ROOT:-"/home/runzhou/Desktop/metadb"}
METADB_RUN=${METADB_RUN:-"$METADB_ROOT/run/s$METADB_ID"}
METADB_LOGS=$METADB_RUN/logs
METADB_OLD_LOGS=$METADB_RUN/old_logs
METADB_PID_FILE=$METADB_RUN/metadb.pid.$METADB_ID

# check running instances
case "$cmd" in
  start)
    if test -e "$METADB_PID_FILE"
    then
      echo "Killing existing metadb server ..."
      pid=$(cat $METADB_PID_FILE)
      kill -9 $pid && sleep 1; rm -f $METADB_PID_FILE
    fi
    ;;
  restart)
    if test -e "$METADB_PID_FILE"
    then
      echo "Stopping metadb server ..."
      pid=$(cat $METADB_PID_FILE)
      kill $pid && sleep 2; rm -f $METADB_PID_FILE
    fi
    ;;
  *)
    exit 1
    ;;
esac

# prepare java runtime env if necessary
if test x"$METADB_BACKEND" = x"__HDFS__"
then
  LD_PATH=`$METADB_HOME/sbin/hdfs.sh ldpath`
  if test -n "$LD_LIBRARY_PATH"
  then
    LD_PATH="$LD_PATH:$LD_LIBRARY_PATH"
  fi
  export LD_LIBRARY_PATH=$LD_PATH
  export LIBHDFS_OPTS="-Djava.library.path=$LD_PATH"
  export CLASSPATH=`$METADB_HOME/sbin/hdfs.sh classpath`
fi

# switch log directories, retaining old logs
if test -d "$METADB_LOGS"
then
  rm -rf $METADB_OLD_LOGS
  mkdir -p $METADB_RUN && mv \
    $METADB_LOGS $METADB_OLD_LOGS || exit 1
fi
mkdir -p $METADB_RUN $METADB_LOGS || exit 1

metadb_addr=${1-"`hostname -s`"}
echo "Starting metadb server $METADB_ID at $metadb_addr, logging to $METADB_LOGS ..."

# start metadb server
# nohup $METADB_BUILD/metadb \
$METADB_BUILD/metadb \
    --metadbid="$METADB_ID" \
    --log_dir="$METADB_LOGS" \
    --file_dir="$METADB_ROOT/_DATA_" \
    --db_root="$METADB_ROOT/_META_" \
    --configfn="$METADB_CONF_DIR/metadb_conf" \
    --metadblstfn="$METADB_CONF_DIR/server_list" \
  # 1>$METADB_LOGS/metadb.STDOUT 2>$METADB_LOGS/metadb.STDERR </dev/null &

echo "$!" | tee $METADB_PID_FILE &>/dev/null

exit 0
