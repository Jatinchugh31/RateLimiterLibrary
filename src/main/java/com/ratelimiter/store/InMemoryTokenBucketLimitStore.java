package com.ratelimiter.store;

import com.ratelimiter.algo.tokenbucket.TokenBucketState;

import java.util.HashMap;
import java.util.Map;

public class InMemoryTokenBucketLimitStore implements LimiterStore<TokenBucketState> {
    Map<String, TokenBucketState> counters = new HashMap<>();

    @Override
    public TokenBucketState getOrCreateCounter(String key,TokenBucketState state) {
        return counters.computeIfAbsent(key,k-> state);
    }
}
