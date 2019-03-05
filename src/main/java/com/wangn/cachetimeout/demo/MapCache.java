package com.wangn.cachetimeout.demo;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * class functional description
 *
 * @author wang.xiongfei
 * @version 1.0.0
 * @since 2019-03-04
 */
public class MapCache implements Cache {

	private String name;

	private long timeOut;

	private MapCache(String name, long timeOut) {
		this.name = name;
		this.timeOut = timeOut;
	}

	public static Cache of(String name, long timeOut) {
		Objects.requireNonNull(name);
		assert timeOut >= 0;
		return new MapCache(name, timeOut);
	}

	private Map<Object, ValExpire> cacheMap = new ConcurrentHashMap<>(64);

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Object getNativeCache() {
		return this.cacheMap;
	}

	@Override
	public ValueWrapper get(Object key) {
		return new SimpleValueWrapper(key);
	}

	@Override
	public <T> T get(Object key, Class<T> type) {
		ValExpire valExpire = cacheMap.get(key);
		if (Objects.nonNull(valExpire) && (valExpire.expire <= System.currentTimeMillis())) {
			return (T)valExpire.key;
		}
		return null;
	}

	@Override
	public <T> T get(Object key, Callable<T> valueLoader) {
		Object val = get(key);
		try {
			return Objects.isNull(val) ? valueLoader.call() : (T) val;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void put(Object key, Object value) {
		long expireTime = timeOut > 0 ? System.currentTimeMillis() + timeOut : Long.MAX_VALUE;
		cacheMap.put(key, ValExpire.valueOf(value, expireTime));
	}

	@Override
	public ValueWrapper putIfAbsent(Object key, Object value) {
		Object val = get(key);
		if (Objects.isNull(val)) {
			put(key, value);
			val = value;
		}
		return new SimpleValueWrapper(val);
	}

	@Override
	public void evict(Object key) {
		cacheMap.remove(key);
	}

	@Override
	public void clear() {
		synchronized (cacheMap) {
			cacheMap.clear();
		}
	}

	static class ValExpire {
		public ValExpire(Object key, long expire) {
			this.key = key;
			this.expire = expire;
		}

		static ValExpire valueOf(Object key, long expire) {
			return new ValExpire(key, expire);
		}

		Object key;
		long expire;
	}
}
