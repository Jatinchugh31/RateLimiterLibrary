package com.ratelimiter.store;

import com.ratelimiter.algo.RateLimiterType;
import com.ratelimiter.algo.tokenbucket.TokenBucketState;

public class StoreFactory {
    public static LimiterStore<?> getLimitStore(RateLimiterType type, String storeType) {
        return switch (type) {
            case FIXED_WINDOW -> new InMemoryFixedWindowLimiterStore();
            case TOKEN_BUCKET -> new InMemoryTokenBucketLimitStore();
            default -> throw new RuntimeException("Unsupported limit type");
        };
    }

    public static LimiterStore<TokenBucketState> getTokenLimiter(String tokenBucket) {
        return new InMemoryTokenBucketLimitStore();
    }
}
