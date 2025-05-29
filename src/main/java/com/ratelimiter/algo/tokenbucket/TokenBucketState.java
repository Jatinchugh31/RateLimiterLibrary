package com.ratelimiter.algo.tokenbucket;

import lombok.AllArgsConstructor;
import lombok.Data;
/*
* we iniate the bucket with maximum capicity
* then refil the token base on refill windwow
* elpased = now - lastRefillTimestamp
* we will calculte the refill rateper min=  maxtoken/refiltime
* refillTokens= elapsed * refilrate per min
  tokenBucketState.tokens = Math.min(maxTokens, tokenBucketState.tokens + refillTokens);

* */
@Data
@AllArgsConstructor
public class TokenBucketState {
    public double tokens;
    public long lastRefillTimestamp;
}
