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
public class CacheTimeOutContext {

	private CacheTimeOutContext() {}

	private static Map<String, Long> cacheTimesMapping = new ConcurrentHashMap<>(64);


	public static long getTimeOut(String key) {
		return cacheTimesMapping.getOrDefault(key, 0L);
	}

	public static long setTimeOut(String key, long timeOut) {
		return cacheTimesMapping.put(key, timeOut);
	}
}
