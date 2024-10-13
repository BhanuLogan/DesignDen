package ratelimiter.impl;

import ratelimiter.config.RateLimiterConfig;
import ratelimiter.interfaces.RateLimiter;

import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketRateLimiter implements RateLimiter {
    private final ConcurrentHashMap<String, Bucket> buckets;

    private TokenBucketRateLimiter() {
        this.buckets = new ConcurrentHashMap<>();
    }

    private static class InstanceHolder {
        public static final TokenBucketRateLimiter INSTANCE = new TokenBucketRateLimiter();
    }

    public static TokenBucketRateLimiter getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public synchronized boolean isAllowed(String key) {
        RateLimiterConfig.Properties properties = RateLimiterConfig.getRateLimiterProperties(key);
        int maxTokens = properties.maxAllowed;
        int refillIntervalMillis = properties.durationInMillis;

        Bucket bucket = buckets.computeIfAbsent(key, k -> new Bucket(maxTokens, System.currentTimeMillis()));

        long currentTime = System.currentTimeMillis();
        long tokensToAdd = (currentTime - bucket.lastRefillTime) / refillIntervalMillis;

        if (tokensToAdd > 0) {
            bucket.tokens = (int) Math.min(maxTokens, bucket.tokens + tokensToAdd);
            bucket.lastRefillTime = currentTime;
        }

        if (bucket.tokens > 0) {
            bucket.tokens--;
            return true;
        }
        return false;
    }

    private static class Bucket {
        int tokens;
        long lastRefillTime;

        Bucket(int tokens, long lastRefillTime) {
            this.tokens = tokens;
            this.lastRefillTime = lastRefillTime;
        }
    }
}
