package com.wangn.cachetimeout.timeout;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Collection;

/**
 * 提供缓存超时的cacheManager
 * {@link CacheTimeoutAnnotationBeanPostProcessor}
 * {@link CacheTimeout}
 *
 * @author wang.xiongfei
 * @version 1.0.0
 * @since 2019-03-04
 */
public interface TimeoutCacheManager extends CacheManager {

	@Override
	Cache getCache(String name);

	@Override
	Collection<String> getCacheNames();

	/**
	 * 获取缓存的超时时间
	 * {@link CacheTimeout}
	 * {@link CacheTimeoutAnnotationBeanPostProcessor}
	 * @param name
	 * @return
	 */
	default long getCacheTimeout(String name) {
		return CacheTimeOutContext.getTimeOut(name);
	}
}
