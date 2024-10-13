package ratelimiter.impl;

import ratelimiter.config.RateLimiterConfig;
import ratelimiter.factory.RateLimiterFactory;
import ratelimiter.interfaces.RateLimiter;

public class ProxyRateLimiter implements RateLimiter {

    private ProxyRateLimiter() {}

    private static class InstanceHolder {
        private static final ProxyRateLimiter INSTANCE = new ProxyRateLimiter();
    }

    public static ProxyRateLimiter getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public boolean isAllowed(String key) {
        RateLimiterConfig.Properties properties = RateLimiterConfig.getRateLimiterProperties(key);
        RateLimiter rateLimiter = RateLimiterFactory.getRateLimiter(properties.strategy);
        boolean isAllowed = rateLimiter.isAllowed(key);
        if(isAllowed) {
            System.out.println(key + " ALLOWED");
        } else {
            System.out.println(key + " THROTTLED");
        }
        return isAllowed;
    }
}
