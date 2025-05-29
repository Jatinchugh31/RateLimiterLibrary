package com.ratelimiter.algo.fixedwindow;

import com.ratelimiter.algo.RateLimiter;
import com.ratelimiter.model.LimiterKey;
import com.ratelimiter.store.LimiterStore;
import lombok.AllArgsConstructor;

import java.util.concurrent.atomic.AtomicLong;

/*
*✅ **Yes, this is a classic Fixed Window rate limiter example!**

Here’s why:

---

### 🟢 **What makes it a Fixed Window?**

✅ **Window-based counters**

* Requests are grouped into **fixed time windows** (like 1 minute, 5 minutes, etc.).
* Each window has a **fixed start and end time** (e.g., 0–60,000 ms, 60,000–120,000 ms).

✅ **No overlap or sliding**

* A request at 170,000 ms and 171,000 ms **both belong to window 2** (fixed time slot).
* Once you cross into the next window, old counters **do not** count anymore.

✅ **Counter resets for each window**

* At `180,000 ms`, new window starts and counter resets to 0.

---

### 🔴 **Why it’s NOT sliding window**

A sliding window:

* Uses **more fine-grained timestamps** to create a rolling window.
* Counts requests in **rolling slices**, e.g., “past 60 seconds from now,” not a rigid 0–60,000 ms window.

---

### 🚀 **Summary of the Fixed Window example you have**:

✅ Fixed window **buckets** of time.
✅ Requests in same window increment **same counter**.
✅ When window changes, new counter starts.
✅ 6th request in same window **blocked**.

---

**So yes!** Your implementation is a **Fixed Window rate limiter**. Would you like me to show a **Sliding Window example** for comparison? 🚀 Let me know!

* */
@AllArgsConstructor
public class FixedWindowRateLimiter implements RateLimiter {
    private final LimiterStore<AtomicLong> store;
    private final long windowSizeInMilliSeconds;
    private final int maxRequests;

    @Override
    public boolean allowRequest(LimiterKey key, int weight) {
        long currentWindow = System.currentTimeMillis() / windowSizeInMilliSeconds;
        System.out.println("Current window: " + System.currentTimeMillis() + " for key" + key.getKey());
        String storeKey = key.getKey() + "_" + currentWindow;
        AtomicLong count = store.getOrCreateCounter(storeKey,new AtomicLong(0));
        long updatedCount = count.addAndGet(weight);
        return updatedCount <= maxRequests;
    }
}


/***
//
// in this we maintian the time window like 1 min 2 min
 every request coming in 1 min will pass
 we are building a key based on bucket
 as when we divide currenttime / windowsize it will give us same back
 like if our request came in
*/