package ratelimiter.config;

import java.util.HashMap;
import java.util.Map;

public class RateLimiterConfig {
    public static Map<String, Properties> config;

    // in real word applications, config will be initialised from configuration file or other resources
    static {
        config = new HashMap<>();

        // client level rate limiting
        config.put("c1", Properties.of("c1", "TOKEN_BUCKET", 3, 5000));
        config.put("c2", Properties.of("c2", "SLIDING_WINDOW", 10, 5000));

        // api level rate limiting
        config.put("api1", Properties.of("api1", "TOKEN_BUCKET", 3, 10000));
        config.put("api2", Properties.of("api2", "SLIDING_WINDOW",10, 60000));
    }

    public static Properties getRateLimiterProperties(String key) {
        return config.getOrDefault(key, Properties.of("default", "TOKEN_BUCKET", 10, 60000));
    }

    public static class Properties {
        public String key;
        public String strategy;
        public int maxAllowed;
        public int durationInMillis;

        public static Properties of(String key, String strategy, int maxAllowed, int durationInMillis) {
            Properties properties = new Properties();
            properties.key = key;
            properties.strategy = strategy;
            properties.maxAllowed = maxAllowed;
            properties.durationInMillis = durationInMillis;
            return properties;
        }
    }
}
