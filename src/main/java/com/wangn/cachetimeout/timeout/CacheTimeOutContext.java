package com.wangn.cachetimeout.timeout;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓存有效期上下文
 *
 * @author wang.xiongfei
 * @version 1.0.0
 * @since 2019-03-04
 */
class CacheTimeOutContext {

	private CacheTimeOutContext() {}

	private static Map<String, Long> cacheTimesMapping = new ConcurrentHashMap<>(64);


	public static long getTimeOut(String key) {
		return cacheTimesMapping.getOrDefault(key, 0L);
	}

	public static void setTimeOut(String key, long timeOut) {
		cacheTimesMapping.put(key, timeOut);
	}
}
