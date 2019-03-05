package com.wangn.cachetimeout.timeout;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 基于spring cacheManage 实现缓存超时功能
 *
 * @author wang.xiongfei
 * @version 1.0.0
 * @since 2019-03-04
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CacheTimeout {

	/**
	 * 缓存有效时间，单位毫秒
	 * @return
	 */
	long value() default 0;
}
