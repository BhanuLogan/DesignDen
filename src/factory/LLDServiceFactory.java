package factory;

import interfaces.LLDService;
import jsonparser.JsonParserLLDService;
import ratelimiter.RateLimiterLLDService;
import util.Constants;
import vendingmachine.VendingMachineLLDService;

import java.util.Map;

import atm.AtmLLDService;

public class LLDServiceFactory {

    public static Map<Constants.Design, LLDService> services = Map.ofEntries(
            Map.entry(Constants.Design.JSON_PARSER, new JsonParserLLDService()),
            Map.entry(Constants.Design.RATE_LIMITER, new RateLimiterLLDService()),
            Map.entry(Constants.Design.VENDING_MACHINE, new VendingMachineLLDService()),
            Map.entry(Constants.Design.PARKING_LOT, new parkinglot.ParkingLotLLDService()),
            Map.entry(Constants.Design.SNAKE_AND_LADDER, new snakeandladder.SnakeAndLadderLLDService()),
            Map.entry(Constants.Design.ATM, new AtmLLDService())
    );

    public static LLDService getService(Constants.Design design) {
        if(!services.containsKey(design)) {
            throw new UnsupportedOperationException("invalid design");
        }
        return services.get(design);
    }
}
