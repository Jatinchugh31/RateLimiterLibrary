package com.ratelimiter.algo;

import com.ratelimiter.exception.RateLimitExceedException;
import com.ratelimiter.model.LimiterKey;

public interface RateLimiter {
      // default weight of every request is 1  and some case we can increase the weight like each request consume 2 token

     boolean allowRequest(LimiterKey key, int weight) throws RateLimitExceedException;

}
