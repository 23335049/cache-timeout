package com.wangn.cachetimeout.timeout;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 解析缓存有效期
 *
 * @author wang.xiongfei
 * @version 1.0.0
 * @since 2019-03-04
 */
@Component
public class CacheTimeoutAnnotationBeanPostProcessor implements BeanPostProcessor {

	private final Set<Class<?>> hasResolvedClasses =
			Collections.newSetFromMap(new ConcurrentHashMap<>(64));

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		Class<?> targetClass = AopUtils.getTargetClass(bean);
		if (!hasResolvedClasses.contains(targetClass)) {
			MethodIntrospector.selectMethods(targetClass, (MethodIntrospector.MetadataLookup<Long>) method -> {
				Cacheable cacheable;
				if ((cacheable = AnnotationUtils.getAnnotation(method, Cacheable.class)) == null) {
					return null;
				}
				CacheTimeout cacheTimeout = AnnotationUtils.getAnnotation(method, CacheTimeout.class);
				long timeOut =  cacheTimeout == null ? 0 :
						cacheTimeout.value() < 0 ? 0 : cacheTimeout.value();
				for (String cacheName : cacheable.value()) {
					CacheTimeOutContext.setTimeOut(cacheName, timeOut);
				}
				return timeOut;
			});
			hasResolvedClasses.add(targetClass);
		}
		return bean;
	}
}
