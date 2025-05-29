package com.ratelimiter.algo.leakybucket;

import com.ratelimiter.algo.RateLimiter;
import com.ratelimiter.exception.RateLimitExceedException;
import com.ratelimiter.model.LimiterKey;
import com.ratelimiter.store.LimiterStore;
import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.Queue;
/*
* leaky bucket recived the request store in the and leak them as 1 request per second
* */
@AllArgsConstructor
public class LeakyBucketRateLimiter implements RateLimiter {

    private final LimiterStore<LeakyBucketState> store;
    private final double capacity;
    private final double leakRatePerMillis; // requests/ms

    @Override
    public boolean allowRequest(LimiterKey key, int weight) {
        String bucketKey = key.getKey();
        LeakyBucketState state = store.getOrCreateCounter(bucketKey, new LeakyBucketState(0,System.currentTimeMillis()));

        synchronized (state) {
            long now = System.currentTimeMillis();
            long elapsed = now - state.lastLeakTimestamp;

            // Leak the bucket
            double leaked = elapsed * leakRatePerMillis;
            state.waterLevel = Math.max(0, state.waterLevel - leaked);
            state.lastLeakTimestamp = now;

            // Check if there's room for this request
            if (state.waterLevel + 1 <= capacity) {
                state.waterLevel += 1; // Add this request
                return true;
            } else {
                return false;
            }
        }
    }
}

