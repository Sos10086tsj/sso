package com.chinesedreamer.sso.base.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.util.StringUtils;

/**
 * Description: 
 * @author Paris
 * @date Feb 6, 201511:55:30 AM
 * @version beta
 */
public abstract class BaseCacheAspect implements InitializingBean{
	protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private CacheManager ehcacheManager;
    private Cache cache;
    protected String cacheName;

    /**
     * 缓存池名称
     *
     * @param cacheName
     */
    public void setCacheName(String cacheName) {

        this.cacheName = cacheName;
    }

    /**
     * 缓存管理器
     *
     * @return
     */
    public void setCacheManager(CacheManager cacheManager) {
        this.ehcacheManager = cacheManager;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        cache = ehcacheManager.getCache(cacheName);
    }

    public void clear() {
        log.debug("cacheName:{}, cache clear", cacheName);
        this.cache.clear();
    }

    public void evict(String key) {
        log.debug("cacheName:{}, evict key:{}", cacheName, key);
        this.cache.evict(key);
    }

    @SuppressWarnings("unchecked")
	public <T> T get(Object key) {
        log.debug("cacheName:{}, get key:{}", cacheName, key);
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        Cache.ValueWrapper value = cache.get(key);
        if (value == null) {
            return null;
        }
        return (T) value.get();
    }

    public void put(String key, Object value) {
        log.debug("cacheName:{}, put key:{}", cacheName, key);
        this.cache.put(key, value);
    }

}
