package com.ratelimiter.algo.leakybucket;

public class LeakyBucketState {
    public double token; // Current "water" in the bucket
    public long lastLeakTimestamp; // Last time we applied leaking

    public LeakyBucketState(double token, long lastLeakTimestamp) {
        this.token = token;
        this.lastLeakTimestamp = lastLeakTimestamp;
    }

}
