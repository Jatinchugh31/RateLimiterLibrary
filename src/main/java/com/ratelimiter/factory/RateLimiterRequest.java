package com.ratelimiter.factory;

import com.ratelimiter.algo.RateLimiterType;
import lombok.Data;

@Data
public class RateLimiterRequest {


    RateLimiterType rateLimiterType = RateLimiterType.TOKEN_BUCKET;
    int capacity=10;
    long refillRate= 1000; //milisecond
    long windowSize=1000; //1 min //milli
    int leakeageRate=1000; //1min

}
