package com.ratelimiter.algo.leakybucket;

public class LeakyBucketState {
    public double waterLevel; // Current "water" in the bucket
    public long lastLeakTimestamp; // Last time we applied leaking

    public LeakyBucketState(double waterLevel, long lastLeakTimestamp) {
        this.waterLevel = waterLevel;
        this.lastLeakTimestamp = lastLeakTimestamp;
    }

}
