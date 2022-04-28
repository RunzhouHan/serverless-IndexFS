#ifndef _INDEXFS_C_CLI_DEPLOYMENT_CACHE_
#define _INDEXFS_C_CLI_DEPLOYMENT_CACHE_

#include <map>
#include <string>

using namespace std;

namespace {

class deployment_cache {
	public:
		deployment_cache();
		~deployment_cache();

	private:
		std::map<std::string, int> cache;
};

} // end namespace

#endif
