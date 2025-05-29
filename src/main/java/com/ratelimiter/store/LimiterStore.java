package com.ratelimiter.store;

import java.util.concurrent.atomic.AtomicLong;

public interface LimiterStore<T> {

    T getOrCreateCounter(String key,T defaultValue);

}
