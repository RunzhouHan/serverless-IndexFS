package edu.cmu.pdl.indexfs.srvless;


// -------------------------------------------------------------
// Exception-safe Index Guard
// -------------------------------------------------------------
//public class svrless_IndexFS_DirIndexGuard {
//	private DirIndexCache* cache_;
//	private DirIndexEntry* const ie_;
//	
//	// No copying allowed
//	private DirIndexGuard(const DirIndexGuard&);
//	private DirIndexGuard& operator=(const DirIndexGuard&);	
//	
//	  explicit DirIndexGuard(DirIndexEntry* ie) : ie_(ie) {
//	    cache_ = ie_->GetCache();
//	  }
//	  ~DirIndexGuard() {
//	    cache_->Release(ie_);
//	  }
//	
//}
//
//
//class DirIndexEntry {
//	 public:
//
//	  DirIndexEntry(DirIndex* index) : index(index),
//	      cache_(NULL), handle_(NULL) {
//	  }
//
//	  ~DirIndexEntry() {
//	    delete index;
//	  }
//
//	  DirIndex* const index;
//
//	  DirIndexCache* GetCache() { return cache_; }
//
//	 private:
//	  DirIndexCache* cache_;
//	  Cache::Handle* handle_;
//	  friend class DirIndexCache;
//
//	  // No copying allowed
//	  DirIndexEntry(const DirIndexEntry&);
//	  DirIndexEntry& operator=(const DirIndexEntry&);
//	};
//
//	// -------------------------------------------------------------
//	// Directory Index Cache
//	// -------------------------------------------------------------
//
//class DirIndexCache {
//	 public:
//
//	  void Evict(int64_t dir_id);
//	  void Release(DirIndexEntry* entry);
//	  DirIndexEntry* Get(int64_t dir_id);
//	  DirIndexEntry* Insert(DirIndex* dir_idx);
//
//	  DirIndexCache(int cap = 4096) { cache_ = NewLRUCache(cap); }
//
//	  virtual ~DirIndexCache() { delete cache_; }
//
//	 private:
//	  Cache* cache_;
//
//	  // No copying allowed
//	  DirIndexCache(const DirIndexCache&);
//	  DirIndexCache& operator=(const DirIndexCache&);
//	};


