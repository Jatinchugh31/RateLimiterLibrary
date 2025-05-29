package com.ratelimiter.factory;

public class RateLimiterFactory {

//    public  static RateLimiter createRateLimiter(RateLimiterRequest rateLimiterRequest) {
//        return switch (rateLimiterRequest.getRateLimiterType()) {
//            case TOKEN_BUCKET ->
//                    new TokenBucketRateLimiter(rateLimiterRequest.getCapacity(), rateLimiterRequest.getRefillRate());
//            case FIXED_WINDOW ->
//                    new FixedWindowRateLimiter(rateLimiterRequest.getWindowSize(), rateLimiterRequest.getCapacity());
//            case LEAKEY_TOKEN_BUCKET ->
//                    new LeakyBucketRateLimiter(rateLimiterRequest.getCapacity(), rateLimiterRequest.getLeakeageRate());
//            case SLIDING_WINDOW -> null;
//            default ->
//                    throw new IllegalArgumentException("Unsupported ratelimiter type: " + rateLimiterRequest.getRateLimiterType());
//        };
//    }
}
