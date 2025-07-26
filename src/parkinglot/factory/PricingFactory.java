package parkinglot.factory;

import parkinglot.impl.pricing.HourlyPricingStrategy;
import parkinglot.interfaces.PricingStrategy;
import parkinglot.util.ParkingLotConstants.PricingStrategyType;

public class PricingFactory {
    public static PricingStrategy getPricingStrategy(PricingStrategyType type) {
        return switch (type) {
            case HOURLY -> new HourlyPricingStrategy();
            default -> throw new IllegalArgumentException("Invalid pricing strategy");
        };
    }
}