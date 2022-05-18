#ifndef _INDEXFS_C_CLI_DEPLOYMENT_CACHE_
#define _INDEXFS_C_CLI_DEPLOYMENT_CACHE_

#include "common/config.h"

#include <map>
#include <string>

namespace indexfs {

class DeploymentCache {
	public:
		DeploymentCache(Config* config);
		~DeploymentCache();

		void deployment_cache_free(void* cache);
		void cache_put(void* map, char* path, int deployment_id); 
		int cache_get(char* path);

	private:
		std::map<std::string, int> Cache;
		Config* config_;
};

}

#endif



