namespace {

deployment_cache::deployment_cache() {
    cache = new map<std::string, int>();
}

deployment_cache::~deployment_cache() {
    delete(cache);
}

deployment_cache::get(std::string path) {
    ;
}

deployment_cache::put(std::string path, int deployment_id) {
    ;
}

}// end namespace
