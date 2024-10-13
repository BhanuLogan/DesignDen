package ratelimiter.impl;

import ratelimiter.config.RateLimiterConfig;
import ratelimiter.interfaces.RateLimiter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowRateLimiter implements RateLimiter {
    private final Map<String, Deque<Long>> requestMap;

    private SlidingWindowRateLimiter() {
        this.requestMap = new ConcurrentHashMap<>();
    }

    private static class InstanceHolder {
        public static final SlidingWindowRateLimiter INSTANCE = new SlidingWindowRateLimiter();
    }

    public static SlidingWindowRateLimiter getInstance() {
        return SlidingWindowRateLimiter.InstanceHolder.INSTANCE;
    }

    @Override
    public synchronized boolean isAllowed(String key) {
        RateLimiterConfig.Properties properties = RateLimiterConfig.getRateLimiterProperties(key);
        int maxRequests = properties.maxAllowed;
        int windowSizeInMillis = properties.durationInMillis;

        long currentTime = System.currentTimeMillis();
        requestMap.putIfAbsent(key, new ArrayDeque<>());

        Deque<Long> requestTimestamps = requestMap.get(key);

        // Remove outdated timestamps (outside the window)
        while (!requestTimestamps.isEmpty() && currentTime - requestTimestamps.peekFirst() > windowSizeInMillis) {
            requestTimestamps.pollFirst();
        }

        if (requestTimestamps.size() < maxRequests) {
            requestTimestamps.addLast(currentTime);
            return true;
        }
        return false;
    }
}
