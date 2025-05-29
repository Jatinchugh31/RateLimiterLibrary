package com.ratelimiter.store;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryFixedWindowLimiterStore implements LimiterStore<AtomicLong> {
    Map<String,AtomicLong> counters = new ConcurrentHashMap<>();

    @Override
    public AtomicLong getOrCreateCounter(String key,AtomicLong defaultValue) {
        return counters.computeIfAbsent(key,k-> defaultValue);
    }
}
