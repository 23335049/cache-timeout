package com.wangn.cachetimeout.timeout;

import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

/**
 * 解析缓存有效期
 *
 * @author wang.xiongfei
 * @version 1.0.0
 * @since 2019-03-04
 */
@Component
public class CacheTimeoutAnnotationBeanPostProcessor implements MergedBeanDefinitionPostProcessor {
	@Override
	public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
		MethodIntrospector.selectMethods(beanType, (MethodIntrospector.MetadataLookup<Long>) method -> {
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
	}
}
