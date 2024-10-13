package ratelimiter;

import interfaces.LLDService;
import ratelimiter.impl.ProxyRateLimiter;
import ratelimiter.interfaces.RateLimiter;

public class RateLimiterLLDService implements LLDService {

    /*
        // client level rate limiting
        config.put("c1", Properties.of("c1", "TOKEN_BUCKET", 3, 5000));
        config.put("c2", Properties.of("c2", "SLIDING_WIDOW", 10, 5000));

        // api level rate limiting
        config.put("api1", Properties.of("api1", "TOKEN_BUCKET", 3, 10000));
        config.put("api2", Properties.of("api2", "SLIDING_WIDOW",10, 60000));

     */
    @Override
    public void runExamples() throws Exception {
        runUseCase1();
        runUseCase2();
    }

    /*
        Make 10 requests as client c1, first 3 should be successful and remaining should be rejected
    */
    private void runUseCase1() {
        System.out.println("----------------- RATE LIMITER -----------------");
        System.out.println("------------ USE CASE 1 - CLIENT ---------------");
        RateLimiter rateLimiter = ProxyRateLimiter.getInstance();
        for(int i = 1; i <= 10; i++) {
            rateLimiter.isAllowed("c1");
        }
        System.out.println("---------------- EXIT --------------------------\n");
    }

    /*
       Make 15 requests for api2, first 10 should be successful and remaining should be rejected
    */
    private void runUseCase2() {
        System.out.println("----------------- RATE LIMITER -----------------");
        System.out.println("--------------- USE CASE 1 - API ---------------");
        RateLimiter rateLimiter = ProxyRateLimiter.getInstance();
        for(int i = 1; i <= 15; i++) {
            rateLimiter.isAllowed("api2");
        }
        System.out.println("---------------- EXIT --------------------------\n");
    }
}
