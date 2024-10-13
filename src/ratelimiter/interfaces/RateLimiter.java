package ratelimiter.interfaces;

public interface RateLimiter {
    boolean isAllowed(String key);
}