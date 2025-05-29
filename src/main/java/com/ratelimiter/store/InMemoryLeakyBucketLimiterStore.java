package com.ratelimiter.store;

import com.ratelimiter.algo.leakybucket.LeakyBucketState;

import java.util.concurrent.ConcurrentHashMap;

public class InMemoryLeakyBucketLimiterStore implements LimiterStore<LeakyBucketState>{

    private final ConcurrentHashMap<String, LeakyBucketState> buckets = new ConcurrentHashMap<>();


    @Override
    public LeakyBucketState getOrCreateCounter(String key, LeakyBucketState defaultValue) {
        return buckets.computeIfAbsent(key, k -> defaultValue);
    }
}
