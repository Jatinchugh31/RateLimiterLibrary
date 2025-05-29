package com.ratelimiter.service;


import com.ratelimiter.algo.fixedwindow.FixedWindowRateLimiter;
import com.ratelimiter.algo.tokenbucket.TokenBucketRateLimiter;
import com.ratelimiter.algo.tokenbucket.TokenBucketState;
import com.ratelimiter.exception.RateLimitExceedException;
import com.ratelimiter.algo.RateLimiter;
import com.ratelimiter.model.KeyScope;
import com.ratelimiter.store.InMemoryFixedWindowLimiterStore;
import com.ratelimiter.store.LimiterStore;
import com.ratelimiter.store.StoreFactory;

import static com.ratelimiter.algo.RateLimiterType.FIXED_WINDOW;
import static com.ratelimiter.algo.RateLimiterType.TOKEN_BUCKET;

public class RateLimitTestService {

    RateLimiter rateLimiter = null;

    public boolean testFixedWindow() throws RateLimitExceedException {
        InMemoryFixedWindowLimiterStore store = new InMemoryFixedWindowLimiterStore();
        RateLimiter fixedWindow = new FixedWindowRateLimiter(store, 60_000, 5);

        RateLimiterManger manager = new RateLimiterManger();
        manager.registerLimiter(FIXED_WINDOW, fixedWindow);

        for (int i = 0; i < 10; i++) {
            String userId = "user1" + i;
            boolean allowed = manager.isAllowed(userId, "api1", KeyScope.API, FIXED_WINDOW, 1);
            System.out.println("Request allowed for userId: " + allowed);

        }
        return true;

    }


    public boolean testTokenBucket() throws RateLimitExceedException {
        LimiterStore<TokenBucketState> store = StoreFactory.getTokenLimiter("tokenBucket");
        RateLimiter fixedWindow = new TokenBucketRateLimiter( 5,60_000, store);

        RateLimiterManger manager = new RateLimiterManger();
        manager.registerLimiter(TOKEN_BUCKET, fixedWindow);

        for (int i = 0; i < 10; i++) {
            String userId = "user1" + i;
            boolean allowed = manager.isAllowed(userId, "api1", KeyScope.API, TOKEN_BUCKET, 1);
            System.out.println("Request allowed for userId: " + allowed);

        }
        return true;

    }
}
