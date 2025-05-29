package com.ratelimiter.algo.tokenbucket;

import com.ratelimiter.algo.RateLimiter;
import com.ratelimiter.model.LimiterKey;
import com.ratelimiter.store.LimiterStore;
import lombok.AllArgsConstructor;

/*
 * Token Bucket to handle burst traffic.
 * Default number of tokens are refilled at a specific interval.
 */
@AllArgsConstructor
public class TokenBucketRateLimiter implements RateLimiter {

    private final int maxTokens;
    private final long refillTimeInMilliSeconds; // e.g., 60_000 ms for 1 min
    private final LimiterStore<TokenBucketState> store;

    @Override
    public boolean allowRequest(LimiterKey key, int weight) {
        String bucketKey = key.getKey();
        TokenBucketState tokenBucketState = store.getOrCreateCounter(bucketKey, new TokenBucketState(maxTokens, System.currentTimeMillis()));

        synchronized (tokenBucketState) {
            long now = System.currentTimeMillis();
            long elapsed = now - tokenBucketState.lastRefillTimestamp;

            // Calculate refill rate
            double refillRatePerMillis = (double) maxTokens / refillTimeInMilliSeconds;

            // Calculate tokens to refill
            double refillTokens = elapsed * refillRatePerMillis;

            // Refill the bucket
            tokenBucketState.tokens = Math.min(maxTokens, tokenBucketState.tokens + refillTokens);
            tokenBucketState.lastRefillTimestamp = now;

            // Check if enough tokens are available
            if (tokenBucketState.tokens >= weight) {
                tokenBucketState.tokens -= weight;
                return true;
            } else {
                return false;
            }
        }
    }
}
