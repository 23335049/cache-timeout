package com.wangn.cachetimeout.demo;

import com.wangn.cachetimeout.timeout.TimeoutCacheManager;
import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractCacheManager;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

/**
 * 基于{@link java.util.concurrent.ConcurrentHashMap}简单缓存实现
 *
 * @author wang.xiongfei
 * @version 1.0.0
 * @since 2019-03-04
 */
@Component
public class SimpleTimeoutCacheManager extends AbstractCacheManager implements TimeoutCacheManager {

	@Override
	protected Collection<? extends Cache> loadCaches() {
		return Collections.emptySet();
	}

	@Override
	protected Cache getMissingCache(String name) {
		return MapCache.of(name, getCacheTimeout(name));
	}
}
