/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
#ifndef MetaDBService_H
#define MetaDBService_H

#include <thrift/TDispatchProcessor.h>
#include <thrift/async/TConcurrentClientSyncInfo.h>
#include "indexfs_types.h"

namespace indexfs {

#ifdef _WIN32
  #pragma warning( push )
  #pragma warning (disable : 4250 ) //inheriting methods via dominance 
#endif

class MetaDBServiceIf {
 public:
  virtual ~MetaDBServiceIf() {}
  virtual void Flush() = 0;
  virtual void NewFile(const KeyInfo_THRIFT& key) = 0;
  virtual void NewDirectory(const KeyInfo_THRIFT& key, const int32_t zeroth_server, const int64_t inode_no) = 0;
  virtual void GetEntry(const KeyInfo_THRIFT& key, const StatInfo& info) = 0;
  virtual void GetServerList(std::vector<std::string> & _return) = 0;
};

class MetaDBServiceIfFactory {
 public:
  typedef MetaDBServiceIf Handler;

  virtual ~MetaDBServiceIfFactory() {}

  virtual MetaDBServiceIf* getHandler(const ::apache::thrift::TConnectionInfo& connInfo) = 0;
  virtual void releaseHandler(MetaDBServiceIf* /* handler */) = 0;
};

class MetaDBServiceIfSingletonFactory : virtual public MetaDBServiceIfFactory {
 public:
  MetaDBServiceIfSingletonFactory(const boost::shared_ptr<MetaDBServiceIf>& iface) : iface_(iface) {}
  virtual ~MetaDBServiceIfSingletonFactory() {}

  virtual MetaDBServiceIf* getHandler(const ::apache::thrift::TConnectionInfo&) {
    return iface_.get();
  }
  virtual void releaseHandler(MetaDBServiceIf* /* handler */) {}

 protected:
  boost::shared_ptr<MetaDBServiceIf> iface_;
};

class MetaDBServiceNull : virtual public MetaDBServiceIf {
 public:
  virtual ~MetaDBServiceNull() {}
  void Flush() {
    return;
  }
  void NewFile(const KeyInfo_THRIFT& /* key */) {
    return;
  }
  void NewDirectory(const KeyInfo_THRIFT& /* key */, const int32_t /* zeroth_server */, const int64_t /* inode_no */) {
    return;
  }
  void GetEntry(const KeyInfo_THRIFT& /* key */, const StatInfo& /* info */) {
    return;
  }
  void GetServerList(std::vector<std::string> & /* _return */) {
    return;
  }
};


class MetaDBService_Flush_args {
 public:

  MetaDBService_Flush_args(const MetaDBService_Flush_args&);
  MetaDBService_Flush_args& operator=(const MetaDBService_Flush_args&);
  MetaDBService_Flush_args() {
  }

  virtual ~MetaDBService_Flush_args() throw();

  bool operator == (const MetaDBService_Flush_args & /* rhs */) const
  {
    return true;
  }
  bool operator != (const MetaDBService_Flush_args &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const MetaDBService_Flush_args & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};


class MetaDBService_Flush_pargs {
 public:


  virtual ~MetaDBService_Flush_pargs() throw();

  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

typedef struct _MetaDBService_Flush_result__isset {
  _MetaDBService_Flush_result__isset() : io_error(false), srv_error(false) {}
  bool io_error :1;
  bool srv_error :1;
} _MetaDBService_Flush_result__isset;

class MetaDBService_Flush_result {
 public:

  MetaDBService_Flush_result(const MetaDBService_Flush_result&);
  MetaDBService_Flush_result& operator=(const MetaDBService_Flush_result&);
  MetaDBService_Flush_result() {
  }

  virtual ~MetaDBService_Flush_result() throw();
  IOError io_error;
  ServerInternalError srv_error;

  _MetaDBService_Flush_result__isset __isset;

  void __set_io_error(const IOError& val);

  void __set_srv_error(const ServerInternalError& val);

  bool operator == (const MetaDBService_Flush_result & rhs) const
  {
    if (!(io_error == rhs.io_error))
      return false;
    if (!(srv_error == rhs.srv_error))
      return false;
    return true;
  }
  bool operator != (const MetaDBService_Flush_result &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const MetaDBService_Flush_result & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

typedef struct _MetaDBService_Flush_presult__isset {
  _MetaDBService_Flush_presult__isset() : io_error(false), srv_error(false) {}
  bool io_error :1;
  bool srv_error :1;
} _MetaDBService_Flush_presult__isset;

class MetaDBService_Flush_presult {
 public:


  virtual ~MetaDBService_Flush_presult() throw();
  IOError io_error;
  ServerInternalError srv_error;

  _MetaDBService_Flush_presult__isset __isset;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);

};

typedef struct _MetaDBService_NewFile_args__isset {
  _MetaDBService_NewFile_args__isset() : key(false) {}
  bool key :1;
} _MetaDBService_NewFile_args__isset;

class MetaDBService_NewFile_args {
 public:

  MetaDBService_NewFile_args(const MetaDBService_NewFile_args&);
  MetaDBService_NewFile_args& operator=(const MetaDBService_NewFile_args&);
  MetaDBService_NewFile_args() {
  }

  virtual ~MetaDBService_NewFile_args() throw();
  KeyInfo_THRIFT key;

  _MetaDBService_NewFile_args__isset __isset;

  void __set_key(const KeyInfo_THRIFT& val);

  bool operator == (const MetaDBService_NewFile_args & rhs) const
  {
    if (!(key == rhs.key))
      return false;
    return true;
  }
  bool operator != (const MetaDBService_NewFile_args &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const MetaDBService_NewFile_args & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};


class MetaDBService_NewFile_pargs {
 public:


  virtual ~MetaDBService_NewFile_pargs() throw();
  const KeyInfo_THRIFT* key;

  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

typedef struct _MetaDBService_NewFile_result__isset {
  _MetaDBService_NewFile_result__isset() : unknown_dir(false), srv_redirect(false), file_exists(false), io_error(false), srv_error(false) {}
  bool unknown_dir :1;
  bool srv_redirect :1;
  bool file_exists :1;
  bool io_error :1;
  bool srv_error :1;
} _MetaDBService_NewFile_result__isset;

class MetaDBService_NewFile_result {
 public:

  MetaDBService_NewFile_result(const MetaDBService_NewFile_result&);
  MetaDBService_NewFile_result& operator=(const MetaDBService_NewFile_result&);
  MetaDBService_NewFile_result() {
  }

  virtual ~MetaDBService_NewFile_result() throw();
  UnrecognizedDirectoryError unknown_dir;
  ServerRedirectionException srv_redirect;
  FileAlreadyExistsException file_exists;
  IOError io_error;
  ServerInternalError srv_error;

  _MetaDBService_NewFile_result__isset __isset;

  void __set_unknown_dir(const UnrecognizedDirectoryError& val);

  void __set_srv_redirect(const ServerRedirectionException& val);

  void __set_file_exists(const FileAlreadyExistsException& val);

  void __set_io_error(const IOError& val);

  void __set_srv_error(const ServerInternalError& val);

  bool operator == (const MetaDBService_NewFile_result & rhs) const
  {
    if (!(unknown_dir == rhs.unknown_dir))
      return false;
    if (!(srv_redirect == rhs.srv_redirect))
      return false;
    if (!(file_exists == rhs.file_exists))
      return false;
    if (!(io_error == rhs.io_error))
      return false;
    if (!(srv_error == rhs.srv_error))
      return false;
    return true;
  }
  bool operator != (const MetaDBService_NewFile_result &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const MetaDBService_NewFile_result & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

typedef struct _MetaDBService_NewFile_presult__isset {
  _MetaDBService_NewFile_presult__isset() : unknown_dir(false), srv_redirect(false), file_exists(false), io_error(false), srv_error(false) {}
  bool unknown_dir :1;
  bool srv_redirect :1;
  bool file_exists :1;
  bool io_error :1;
  bool srv_error :1;
} _MetaDBService_NewFile_presult__isset;

class MetaDBService_NewFile_presult {
 public:


  virtual ~MetaDBService_NewFile_presult() throw();
  UnrecognizedDirectoryError unknown_dir;
  ServerRedirectionException srv_redirect;
  FileAlreadyExistsException file_exists;
  IOError io_error;
  ServerInternalError srv_error;

  _MetaDBService_NewFile_presult__isset __isset;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);

};

typedef struct _MetaDBService_NewDirectory_args__isset {
  _MetaDBService_NewDirectory_args__isset() : key(false), zeroth_server(false), inode_no(false) {}
  bool key :1;
  bool zeroth_server :1;
  bool inode_no :1;
} _MetaDBService_NewDirectory_args__isset;

class MetaDBService_NewDirectory_args {
 public:

  MetaDBService_NewDirectory_args(const MetaDBService_NewDirectory_args&);
  MetaDBService_NewDirectory_args& operator=(const MetaDBService_NewDirectory_args&);
  MetaDBService_NewDirectory_args() : zeroth_server(0), inode_no(0) {
  }

  virtual ~MetaDBService_NewDirectory_args() throw();
  KeyInfo_THRIFT key;
  int32_t zeroth_server;
  int64_t inode_no;

  _MetaDBService_NewDirectory_args__isset __isset;

  void __set_key(const KeyInfo_THRIFT& val);

  void __set_zeroth_server(const int32_t val);

  void __set_inode_no(const int64_t val);

  bool operator == (const MetaDBService_NewDirectory_args & rhs) const
  {
    if (!(key == rhs.key))
      return false;
    if (!(zeroth_server == rhs.zeroth_server))
      return false;
    if (!(inode_no == rhs.inode_no))
      return false;
    return true;
  }
  bool operator != (const MetaDBService_NewDirectory_args &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const MetaDBService_NewDirectory_args & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};


class MetaDBService_NewDirectory_pargs {
 public:


  virtual ~MetaDBService_NewDirectory_pargs() throw();
  const KeyInfo_THRIFT* key;
  const int32_t* zeroth_server;
  const int64_t* inode_no;

  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

typedef struct _MetaDBService_NewDirectory_result__isset {
  _MetaDBService_NewDirectory_result__isset() : unknown_dir(false), srv_redirect(false), file_exists(false), io_error(false), srv_error(false) {}
  bool unknown_dir :1;
  bool srv_redirect :1;
  bool file_exists :1;
  bool io_error :1;
  bool srv_error :1;
} _MetaDBService_NewDirectory_result__isset;

class MetaDBService_NewDirectory_result {
 public:

  MetaDBService_NewDirectory_result(const MetaDBService_NewDirectory_result&);
  MetaDBService_NewDirectory_result& operator=(const MetaDBService_NewDirectory_result&);
  MetaDBService_NewDirectory_result() {
  }

  virtual ~MetaDBService_NewDirectory_result() throw();
  UnrecognizedDirectoryError unknown_dir;
  ServerRedirectionException srv_redirect;
  FileAlreadyExistsException file_exists;
  IOError io_error;
  ServerInternalError srv_error;

  _MetaDBService_NewDirectory_result__isset __isset;

  void __set_unknown_dir(const UnrecognizedDirectoryError& val);

  void __set_srv_redirect(const ServerRedirectionException& val);

  void __set_file_exists(const FileAlreadyExistsException& val);

  void __set_io_error(const IOError& val);

  void __set_srv_error(const ServerInternalError& val);

  bool operator == (const MetaDBService_NewDirectory_result & rhs) const
  {
    if (!(unknown_dir == rhs.unknown_dir))
      return false;
    if (!(srv_redirect == rhs.srv_redirect))
      return false;
    if (!(file_exists == rhs.file_exists))
      return false;
    if (!(io_error == rhs.io_error))
      return false;
    if (!(srv_error == rhs.srv_error))
      return false;
    return true;
  }
  bool operator != (const MetaDBService_NewDirectory_result &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const MetaDBService_NewDirectory_result & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

typedef struct _MetaDBService_NewDirectory_presult__isset {
  _MetaDBService_NewDirectory_presult__isset() : unknown_dir(false), srv_redirect(false), file_exists(false), io_error(false), srv_error(false) {}
  bool unknown_dir :1;
  bool srv_redirect :1;
  bool file_exists :1;
  bool io_error :1;
  bool srv_error :1;
} _MetaDBService_NewDirectory_presult__isset;

class MetaDBService_NewDirectory_presult {
 public:


  virtual ~MetaDBService_NewDirectory_presult() throw();
  UnrecognizedDirectoryError unknown_dir;
  ServerRedirectionException srv_redirect;
  FileAlreadyExistsException file_exists;
  IOError io_error;
  ServerInternalError srv_error;

  _MetaDBService_NewDirectory_presult__isset __isset;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);

};

typedef struct _MetaDBService_GetEntry_args__isset {
  _MetaDBService_GetEntry_args__isset() : key(false), info(false) {}
  bool key :1;
  bool info :1;
} _MetaDBService_GetEntry_args__isset;

class MetaDBService_GetEntry_args {
 public:

  MetaDBService_GetEntry_args(const MetaDBService_GetEntry_args&);
  MetaDBService_GetEntry_args& operator=(const MetaDBService_GetEntry_args&);
  MetaDBService_GetEntry_args() {
  }

  virtual ~MetaDBService_GetEntry_args() throw();
  KeyInfo_THRIFT key;
  StatInfo info;

  _MetaDBService_GetEntry_args__isset __isset;

  void __set_key(const KeyInfo_THRIFT& val);

  void __set_info(const StatInfo& val);

  bool operator == (const MetaDBService_GetEntry_args & rhs) const
  {
    if (!(key == rhs.key))
      return false;
    if (!(info == rhs.info))
      return false;
    return true;
  }
  bool operator != (const MetaDBService_GetEntry_args &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const MetaDBService_GetEntry_args & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};


class MetaDBService_GetEntry_pargs {
 public:


  virtual ~MetaDBService_GetEntry_pargs() throw();
  const KeyInfo_THRIFT* key;
  const StatInfo* info;

  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

typedef struct _MetaDBService_GetEntry_result__isset {
  _MetaDBService_GetEntry_result__isset() : unknown_dir(false), srv_redirect(false), not_found(false), io_error(false), srv_error(false) {}
  bool unknown_dir :1;
  bool srv_redirect :1;
  bool not_found :1;
  bool io_error :1;
  bool srv_error :1;
} _MetaDBService_GetEntry_result__isset;

class MetaDBService_GetEntry_result {
 public:

  MetaDBService_GetEntry_result(const MetaDBService_GetEntry_result&);
  MetaDBService_GetEntry_result& operator=(const MetaDBService_GetEntry_result&);
  MetaDBService_GetEntry_result() {
  }

  virtual ~MetaDBService_GetEntry_result() throw();
  UnrecognizedDirectoryError unknown_dir;
  ServerRedirectionException srv_redirect;
  FileNotFoundException not_found;
  IOError io_error;
  ServerInternalError srv_error;

  _MetaDBService_GetEntry_result__isset __isset;

  void __set_unknown_dir(const UnrecognizedDirectoryError& val);

  void __set_srv_redirect(const ServerRedirectionException& val);

  void __set_not_found(const FileNotFoundException& val);

  void __set_io_error(const IOError& val);

  void __set_srv_error(const ServerInternalError& val);

  bool operator == (const MetaDBService_GetEntry_result & rhs) const
  {
    if (!(unknown_dir == rhs.unknown_dir))
      return false;
    if (!(srv_redirect == rhs.srv_redirect))
      return false;
    if (!(not_found == rhs.not_found))
      return false;
    if (!(io_error == rhs.io_error))
      return false;
    if (!(srv_error == rhs.srv_error))
      return false;
    return true;
  }
  bool operator != (const MetaDBService_GetEntry_result &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const MetaDBService_GetEntry_result & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

typedef struct _MetaDBService_GetEntry_presult__isset {
  _MetaDBService_GetEntry_presult__isset() : unknown_dir(false), srv_redirect(false), not_found(false), io_error(false), srv_error(false) {}
  bool unknown_dir :1;
  bool srv_redirect :1;
  bool not_found :1;
  bool io_error :1;
  bool srv_error :1;
} _MetaDBService_GetEntry_presult__isset;

class MetaDBService_GetEntry_presult {
 public:


  virtual ~MetaDBService_GetEntry_presult() throw();
  UnrecognizedDirectoryError unknown_dir;
  ServerRedirectionException srv_redirect;
  FileNotFoundException not_found;
  IOError io_error;
  ServerInternalError srv_error;

  _MetaDBService_GetEntry_presult__isset __isset;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);

};


class MetaDBService_GetServerList_args {
 public:

  MetaDBService_GetServerList_args(const MetaDBService_GetServerList_args&);
  MetaDBService_GetServerList_args& operator=(const MetaDBService_GetServerList_args&);
  MetaDBService_GetServerList_args() {
  }

  virtual ~MetaDBService_GetServerList_args() throw();

  bool operator == (const MetaDBService_GetServerList_args & /* rhs */) const
  {
    return true;
  }
  bool operator != (const MetaDBService_GetServerList_args &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const MetaDBService_GetServerList_args & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};


class MetaDBService_GetServerList_pargs {
 public:


  virtual ~MetaDBService_GetServerList_pargs() throw();

  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

typedef struct _MetaDBService_GetServerList_result__isset {
  _MetaDBService_GetServerList_result__isset() : success(false), srv_error(false) {}
  bool success :1;
  bool srv_error :1;
} _MetaDBService_GetServerList_result__isset;

class MetaDBService_GetServerList_result {
 public:

  MetaDBService_GetServerList_result(const MetaDBService_GetServerList_result&);
  MetaDBService_GetServerList_result& operator=(const MetaDBService_GetServerList_result&);
  MetaDBService_GetServerList_result() {
  }

  virtual ~MetaDBService_GetServerList_result() throw();
  std::vector<std::string>  success;
  ServerInternalError srv_error;

  _MetaDBService_GetServerList_result__isset __isset;

  void __set_success(const std::vector<std::string> & val);

  void __set_srv_error(const ServerInternalError& val);

  bool operator == (const MetaDBService_GetServerList_result & rhs) const
  {
    if (!(success == rhs.success))
      return false;
    if (!(srv_error == rhs.srv_error))
      return false;
    return true;
  }
  bool operator != (const MetaDBService_GetServerList_result &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const MetaDBService_GetServerList_result & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

typedef struct _MetaDBService_GetServerList_presult__isset {
  _MetaDBService_GetServerList_presult__isset() : success(false), srv_error(false) {}
  bool success :1;
  bool srv_error :1;
} _MetaDBService_GetServerList_presult__isset;

class MetaDBService_GetServerList_presult {
 public:


  virtual ~MetaDBService_GetServerList_presult() throw();
  std::vector<std::string> * success;
  ServerInternalError srv_error;

  _MetaDBService_GetServerList_presult__isset __isset;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);

};

class MetaDBServiceClient : virtual public MetaDBServiceIf {
 public:
  MetaDBServiceClient(boost::shared_ptr< ::apache::thrift::protocol::TProtocol> prot) {
    setProtocol(prot);
  }
  MetaDBServiceClient(boost::shared_ptr< ::apache::thrift::protocol::TProtocol> iprot, boost::shared_ptr< ::apache::thrift::protocol::TProtocol> oprot) {
    setProtocol(iprot,oprot);
  }
 private:
  void setProtocol(boost::shared_ptr< ::apache::thrift::protocol::TProtocol> prot) {
  setProtocol(prot,prot);
  }
  void setProtocol(boost::shared_ptr< ::apache::thrift::protocol::TProtocol> iprot, boost::shared_ptr< ::apache::thrift::protocol::TProtocol> oprot) {
    piprot_=iprot;
    poprot_=oprot;
    iprot_ = iprot.get();
    oprot_ = oprot.get();
  }
 public:
  boost::shared_ptr< ::apache::thrift::protocol::TProtocol> getInputProtocol() {
    return piprot_;
  }
  boost::shared_ptr< ::apache::thrift::protocol::TProtocol> getOutputProtocol() {
    return poprot_;
  }
  void Flush();
  void send_Flush();
  void recv_Flush();
  void NewFile(const KeyInfo_THRIFT& key);
  void send_NewFile(const KeyInfo_THRIFT& key);
  void recv_NewFile();
  void NewDirectory(const KeyInfo_THRIFT& key, const int32_t zeroth_server, const int64_t inode_no);
  void send_NewDirectory(const KeyInfo_THRIFT& key, const int32_t zeroth_server, const int64_t inode_no);
  void recv_NewDirectory();
  void GetEntry(const KeyInfo_THRIFT& key, const StatInfo& info);
  void send_GetEntry(const KeyInfo_THRIFT& key, const StatInfo& info);
  void recv_GetEntry();
  void GetServerList(std::vector<std::string> & _return);
  void send_GetServerList();
  void recv_GetServerList(std::vector<std::string> & _return);
 protected:
  boost::shared_ptr< ::apache::thrift::protocol::TProtocol> piprot_;
  boost::shared_ptr< ::apache::thrift::protocol::TProtocol> poprot_;
  ::apache::thrift::protocol::TProtocol* iprot_;
  ::apache::thrift::protocol::TProtocol* oprot_;
};

class MetaDBServiceProcessor : public ::apache::thrift::TDispatchProcessor {
 protected:
  boost::shared_ptr<MetaDBServiceIf> iface_;
  virtual bool dispatchCall(::apache::thrift::protocol::TProtocol* iprot, ::apache::thrift::protocol::TProtocol* oprot, const std::string& fname, int32_t seqid, void* callContext);
 private:
  typedef  void (MetaDBServiceProcessor::*ProcessFunction)(int32_t, ::apache::thrift::protocol::TProtocol*, ::apache::thrift::protocol::TProtocol*, void*);
  typedef std::map<std::string, ProcessFunction> ProcessMap;
  ProcessMap processMap_;
  void process_Flush(int32_t seqid, ::apache::thrift::protocol::TProtocol* iprot, ::apache::thrift::protocol::TProtocol* oprot, void* callContext);
  void process_NewFile(int32_t seqid, ::apache::thrift::protocol::TProtocol* iprot, ::apache::thrift::protocol::TProtocol* oprot, void* callContext);
  void process_NewDirectory(int32_t seqid, ::apache::thrift::protocol::TProtocol* iprot, ::apache::thrift::protocol::TProtocol* oprot, void* callContext);
  void process_GetEntry(int32_t seqid, ::apache::thrift::protocol::TProtocol* iprot, ::apache::thrift::protocol::TProtocol* oprot, void* callContext);
  void process_GetServerList(int32_t seqid, ::apache::thrift::protocol::TProtocol* iprot, ::apache::thrift::protocol::TProtocol* oprot, void* callContext);
 public:
  MetaDBServiceProcessor(boost::shared_ptr<MetaDBServiceIf> iface) :
    iface_(iface) {
    processMap_["Flush"] = &MetaDBServiceProcessor::process_Flush;
    processMap_["NewFile"] = &MetaDBServiceProcessor::process_NewFile;
    processMap_["NewDirectory"] = &MetaDBServiceProcessor::process_NewDirectory;
    processMap_["GetEntry"] = &MetaDBServiceProcessor::process_GetEntry;
    processMap_["GetServerList"] = &MetaDBServiceProcessor::process_GetServerList;
  }

  virtual ~MetaDBServiceProcessor() {}
};

class MetaDBServiceProcessorFactory : public ::apache::thrift::TProcessorFactory {
 public:
  MetaDBServiceProcessorFactory(const ::boost::shared_ptr< MetaDBServiceIfFactory >& handlerFactory) :
      handlerFactory_(handlerFactory) {}

  ::boost::shared_ptr< ::apache::thrift::TProcessor > getProcessor(const ::apache::thrift::TConnectionInfo& connInfo);

 protected:
  ::boost::shared_ptr< MetaDBServiceIfFactory > handlerFactory_;
};

class MetaDBServiceMultiface : virtual public MetaDBServiceIf {
 public:
  MetaDBServiceMultiface(std::vector<boost::shared_ptr<MetaDBServiceIf> >& ifaces) : ifaces_(ifaces) {
  }
  virtual ~MetaDBServiceMultiface() {}
 protected:
  std::vector<boost::shared_ptr<MetaDBServiceIf> > ifaces_;
  MetaDBServiceMultiface() {}
  void add(boost::shared_ptr<MetaDBServiceIf> iface) {
    ifaces_.push_back(iface);
  }
 public:
  void Flush() {
    size_t sz = ifaces_.size();
    size_t i = 0;
    for (; i < (sz - 1); ++i) {
      ifaces_[i]->Flush();
    }
    ifaces_[i]->Flush();
  }

  void NewFile(const KeyInfo_THRIFT& key) {
    size_t sz = ifaces_.size();
    size_t i = 0;
    for (; i < (sz - 1); ++i) {
      ifaces_[i]->NewFile(key);
    }
    ifaces_[i]->NewFile(key);
  }

  void NewDirectory(const KeyInfo_THRIFT& key, const int32_t zeroth_server, const int64_t inode_no) {
    size_t sz = ifaces_.size();
    size_t i = 0;
    for (; i < (sz - 1); ++i) {
      ifaces_[i]->NewDirectory(key, zeroth_server, inode_no);
    }
    ifaces_[i]->NewDirectory(key, zeroth_server, inode_no);
  }

  void GetEntry(const KeyInfo_THRIFT& key, const StatInfo& info) {
    size_t sz = ifaces_.size();
    size_t i = 0;
    for (; i < (sz - 1); ++i) {
      ifaces_[i]->GetEntry(key, info);
    }
    ifaces_[i]->GetEntry(key, info);
  }

  void GetServerList(std::vector<std::string> & _return) {
    size_t sz = ifaces_.size();
    size_t i = 0;
    for (; i < (sz - 1); ++i) {
      ifaces_[i]->GetServerList(_return);
    }
    ifaces_[i]->GetServerList(_return);
    return;
  }

};

// The 'concurrent' client is a thread safe client that correctly handles
// out of order responses.  It is slower than the regular client, so should
// only be used when you need to share a connection among multiple threads
class MetaDBServiceConcurrentClient : virtual public MetaDBServiceIf {
 public:
  MetaDBServiceConcurrentClient(boost::shared_ptr< ::apache::thrift::protocol::TProtocol> prot) {
    setProtocol(prot);
  }
  MetaDBServiceConcurrentClient(boost::shared_ptr< ::apache::thrift::protocol::TProtocol> iprot, boost::shared_ptr< ::apache::thrift::protocol::TProtocol> oprot) {
    setProtocol(iprot,oprot);
  }
 private:
  void setProtocol(boost::shared_ptr< ::apache::thrift::protocol::TProtocol> prot) {
  setProtocol(prot,prot);
  }
  void setProtocol(boost::shared_ptr< ::apache::thrift::protocol::TProtocol> iprot, boost::shared_ptr< ::apache::thrift::protocol::TProtocol> oprot) {
    piprot_=iprot;
    poprot_=oprot;
    iprot_ = iprot.get();
    oprot_ = oprot.get();
  }
 public:
  boost::shared_ptr< ::apache::thrift::protocol::TProtocol> getInputProtocol() {
    return piprot_;
  }
  boost::shared_ptr< ::apache::thrift::protocol::TProtocol> getOutputProtocol() {
    return poprot_;
  }
  void Flush();
  int32_t send_Flush();
  void recv_Flush(const int32_t seqid);
  void NewFile(const KeyInfo_THRIFT& key);
  int32_t send_NewFile(const KeyInfo_THRIFT& key);
  void recv_NewFile(const int32_t seqid);
  void NewDirectory(const KeyInfo_THRIFT& key, const int32_t zeroth_server, const int64_t inode_no);
  int32_t send_NewDirectory(const KeyInfo_THRIFT& key, const int32_t zeroth_server, const int64_t inode_no);
  void recv_NewDirectory(const int32_t seqid);
  void GetEntry(const KeyInfo_THRIFT& key, const StatInfo& info);
  int32_t send_GetEntry(const KeyInfo_THRIFT& key, const StatInfo& info);
  void recv_GetEntry(const int32_t seqid);
  void GetServerList(std::vector<std::string> & _return);
  int32_t send_GetServerList();
  void recv_GetServerList(std::vector<std::string> & _return, const int32_t seqid);
 protected:
  boost::shared_ptr< ::apache::thrift::protocol::TProtocol> piprot_;
  boost::shared_ptr< ::apache::thrift::protocol::TProtocol> poprot_;
  ::apache::thrift::protocol::TProtocol* iprot_;
  ::apache::thrift::protocol::TProtocol* oprot_;
  ::apache::thrift::async::TConcurrentClientSyncInfo sync_;
};

#ifdef _WIN32
  #pragma warning( pop )
#endif

} // namespace

#endif
