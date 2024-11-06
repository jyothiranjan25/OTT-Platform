package com.example.springboot.core.Cache;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
@EnableCaching
public class CacheConfig {

    @Autowired
    private CacheManager cacheManager;

    @PostConstruct
    public void init() {
        // get Cache Data
        cacheManager.getCacheNames().forEach(cacheName -> {
            System.out.println("Cache Name: " + cacheName);
        });

        // clear Cache
        cacheManager.getCacheNames().forEach(cacheName -> Objects.requireNonNull(cacheManager.getCache(cacheName)).clear());
    }
}
