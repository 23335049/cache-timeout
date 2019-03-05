package com.wangn.cachetimeout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CacheTimeoutApplication {

    public static void main(String[] args) {
        SpringApplication.run(CacheTimeoutApplication.class, args);
    }

}
