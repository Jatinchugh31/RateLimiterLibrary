package com.ratelimiter.service;

import com.ratelimiter.algo.RateLimiter;
import com.ratelimiter.algo.RateLimiterType;
import com.ratelimiter.model.KeyScope;
import com.ratelimiter.model.LimiterKey;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiterManger {
    private final Map<RateLimiterType, RateLimiter> limiterRegistry = new ConcurrentHashMap<>();

    public void registerLimiter(RateLimiterType algorithm, RateLimiter limiter) {
        limiterRegistry.put(algorithm, limiter);
    }

    public boolean isAllowed(String userId, String apiId, KeyScope keyScope, RateLimiterType algorithm, int weight) {
        RateLimiter limiter = limiterRegistry.get(algorithm);
        if (limiter == null) {
            throw new IllegalArgumentException("No limiter found for algorithm: " + algorithm);
        }

        LimiterKey key = new LimiterKey(userId, apiId,keyScope);

        // Advanced concurrency: Fork-Join or CompletableFuture
        return CompletableFuture.supplyAsync(() -> limiter.allowRequest(key, weight))
                .join();
    }
}
