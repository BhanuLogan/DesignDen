package parkinglot.model;

import java.util.List;

import parkinglot.util.ParkingLotConstants.PricingStrategyType;
import parkinglot.util.ParkingLotConstants.SpotSelectionStrategyType;

public class ParkingLot {
    private final List<ParkingFloor> floors;
    private final String name;
    private final SpotSelectionStrategyType spotSelectionStrategyType;
    private final PricingStrategyType pricingStrategyType;
    
    public ParkingLot(String name, List<ParkingFloor> floors, 
                    SpotSelectionStrategyType spotSelectionStrategyType, 
                    PricingStrategyType pricingStrategyType) {
        this.name = name;
        this.floors = floors;
        this.spotSelectionStrategyType = spotSelectionStrategyType;
        this.pricingStrategyType = pricingStrategyType;
    }

    public List<ParkingFloor> getFloors() {
        return floors;
    }

    public String getName() {
        return name;
    }

    public SpotSelectionStrategyType getSpotSelectionStrategyType() {
        return spotSelectionStrategyType;
    }

    public PricingStrategyType getPricingStrategyType() {
        return pricingStrategyType;
    }

    public void addFloor(ParkingFloor floor) {
        floors.add(floor);
    }

    public void addSlotToFloor(int floorNumber, ParkingSlot slot) {
        for (ParkingFloor floor : floors) {
            if (floor.getFloorNumber() == floorNumber) {
                floor.getSlots().add(slot);
                System.out.println("Slot added to floor - " + floor.getFloorName());
                return;
            }
        }
        throw new IllegalArgumentException("Floor not found: " + floorNumber);
    }
}
