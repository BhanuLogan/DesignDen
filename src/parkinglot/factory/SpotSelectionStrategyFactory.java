package parkinglot.factory;

import parkinglot.impl.spotselection.NearestToEntryStrategy;
import parkinglot.impl.spotselection.NearestToExitStrategy;
import parkinglot.interfaces.SpotSelectionStrategy;
import parkinglot.util.ParkingLotConstants.SpotSelectionStrategyType;

public class SpotSelectionStrategyFactory {
    public static SpotSelectionStrategy getStrategy(SpotSelectionStrategyType type) {
        return switch (type) {
            case NEAREST_TO_ENTRY -> new NearestToEntryStrategy();
            case NEAREST_TO_EXIT -> new NearestToExitStrategy();
            default -> throw new IllegalArgumentException("Invalid spot selection type");
        };
    }
}