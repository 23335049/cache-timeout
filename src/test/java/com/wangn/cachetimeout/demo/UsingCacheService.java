package com.wangn.cachetimeout.demo;

import com.wangn.cachetimeout.timeout.CacheTimeout;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * class functional description
 *
 * @author wang.xiongfei
 * @version 1.0.0
 * @since 2019-03-06
 */
@Service
public class UsingCacheService {

    @CacheTimeout(1000)
    @Cacheable(value = "nameList", key = "#name")
    public String getTimeStamp(String name) {
        System.out.println("---------------using method: no cache-------------------------------");
        return String.format("%s: %d", name, System.currentTimeMillis());
    }
}
