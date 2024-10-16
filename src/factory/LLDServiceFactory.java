package factory;

import interfaces.LLDService;
import jsonparser.JsonParserLLDService;
import ratelimiter.RateLimiterLLDService;
import util.Constants;
import java.util.Map;

public class LLDServiceFactory {

    public static Map<Constants.Design, LLDService> services = Map.ofEntries(
            Map.entry(Constants.Design.JSON_PARSER, new JsonParserLLDService()),
            Map.entry(Constants.Design.RATE_LIMITER, new RateLimiterLLDService())
    );

    public static LLDService getService(Constants.Design design) {
        if(!services.containsKey(design)) {
            throw new UnsupportedOperationException("invalid design");
        }
        return services.get(design);
    }
}
