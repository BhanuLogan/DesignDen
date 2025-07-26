package parkinglot.impl.pricing;

import java.util.HashMap;
import java.util.Map;

import parkinglot.interfaces.PricingStrategy;
import parkinglot.util.ParkingLotConstants.VehicleType;

public class HourlyPricingStrategy implements PricingStrategy {
    private static final Map<VehicleType, Double> rateMap = new HashMap<>() {{
        put(VehicleType.BIKE, 10.0);
        put(VehicleType.CAR, 20.0);
        put(VehicleType.TRUCK, 30.0);
    }};

    public double calculateCharge(VehicleType type, long hours) {
        return rateMap.get(type) * hours;
    }
}