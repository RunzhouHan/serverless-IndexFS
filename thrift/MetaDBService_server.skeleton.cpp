// This autogenerated skeleton file illustrates how to build a server.
// You should copy it to another filename to avoid overwriting it.

#include "MetaDBService.h"
#include <thrift/protocol/TBinaryProtocol.h>
#include <thrift/server/TSimpleServer.h>
#include <thrift/transport/TServerSocket.h>
#include <thrift/transport/TBufferTransports.h>

using namespace ::apache::thrift;
using namespace ::apache::thrift::protocol;
using namespace ::apache::thrift::transport;
using namespace ::apache::thrift::server;

using boost::shared_ptr;

using namespace  ::indexfs;

class MetaDBServiceHandler : virtual public MetaDBServiceIf {
 public:
  MetaDBServiceHandler() {
    // Your initialization goes here
  }

  void Flush() {
    // Your implementation goes here
    printf("Flush\n");
  }

  void NewFile(const KeyInfo_THRIFT& key) {
    // Your implementation goes here
    printf("NewFile\n");
  }

  void NewDirectory(const KeyInfo_THRIFT& key, const int32_t zeroth_server, const int64_t inode_no) {
    // Your implementation goes here
    printf("NewDirectory\n");
  }

  void GetEntry(StatInfo& _return, const KeyInfo_THRIFT& key) {
    // Your implementation goes here
    printf("GetEntry\n");
  }

  void PutEntry(const KeyInfo_THRIFT& key, const StatInfo& info) {
    // Your implementation goes here
    printf("PutEntry\n");
  }

  int64_t ReserveNextInodeNo() {
    // Your implementation goes here
    printf("ReserveNextInodeNo\n");
  }

  void GetServerList(std::vector<std::string> & _return) {
    // Your implementation goes here
    printf("GetServerList\n");
  }

  void GetPortList(std::vector<int32_t> & _return) {
    // Your implementation goes here
    printf("GetPortList\n");
  }

};

int main(int argc, char **argv) {
  int port = 9090;
  shared_ptr<MetaDBServiceHandler> handler(new MetaDBServiceHandler());
  shared_ptr<TProcessor> processor(new MetaDBServiceProcessor(handler));
  shared_ptr<TServerTransport> serverTransport(new TServerSocket(port));
  shared_ptr<TTransportFactory> transportFactory(new TBufferedTransportFactory());
  shared_ptr<TProtocolFactory> protocolFactory(new TBinaryProtocolFactory());

  TSimpleServer server(processor, serverTransport, transportFactory, protocolFactory);
  server.serve();
  return 0;
}

