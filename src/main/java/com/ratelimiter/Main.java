package com.ratelimiter;

import com.ratelimiter.service.RateLimitTestService;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        RateLimitTestService rateLimiter = new RateLimitTestService();
        rateLimiter.testTokenBucket();
    }
}