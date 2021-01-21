Serverless-IndexFS
===============

A ported IndexFS for serverless metadata management and caching. We decouple metadata caching and LevelDB backend by split them as two different servers and wrapping library calls between them as Thrift RPC calls. In the ported version, metadata caching server acts like a client of LevelDB backend and they work collaborately to provide IndexFS metadata service.

To see source code of original IndexFS (SC'14): https://github.com/zhengqmark/indexfs-0.4
