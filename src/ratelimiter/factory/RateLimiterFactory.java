package ratelimiter.factory;

import ratelimiter.impl.SlidingWindowRateLimiter;
import ratelimiter.impl.TokenBucketRateLimiter;
import ratelimiter.interfaces.RateLimiter;

import java.util.Map;

public class RateLimiterFactory {
    private static final Map<String, RateLimiter> rateLimiters = Map.ofEntries(
            Map.entry("TOKEN_BUCKET", TokenBucketRateLimiter.getInstance()),
            Map.entry("SLIDING_WINDOW", SlidingWindowRateLimiter.getInstance())
    );

    public static RateLimiter getRateLimiter(String strategy) {
        if(!rateLimiters.containsKey(strategy)) {
            throw new IllegalArgumentException("unknown strategy - " + strategy);
        }
        return rateLimiters.get(strategy);
    }
}
