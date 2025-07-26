package parkinglot.services;

import java.util.ArrayList;
import java.util.List;

import parkinglot.factory.SpotSelectionStrategyFactory;
import parkinglot.interfaces.SpotSelectionStrategy;
import parkinglot.model.ParkingFloor;
import parkinglot.model.ParkingSlot;
import parkinglot.util.ParkingLotConstants.SlotType;
import parkinglot.util.ParkingLotConstants.SpotSelectionStrategyType;
import parkinglot.util.ParkingLotConstants.VehicleType;

public class ParkingFloorService {

    public ParkingSlot findAvailableSlot(ParkingFloor floor, VehicleType type, SpotSelectionStrategyType strategyType) {
        SpotSelectionStrategy strategy = SpotSelectionStrategyFactory.getStrategy(strategyType);
        List<ParkingSlot> slots = floor.getSlots();
        SlotType required = mapToSlotType(type);

        return slots.stream()
            .filter(s -> !s.isOccupied() && s.getType().equals(required))
            .sorted(strategy.getComparator())
            .findFirst()
            .orElse(null);
    }

    public List<ParkingSlot> getAvailableSlots(ParkingFloor floor, VehicleType type) {
        SlotType required = mapToSlotType(type);
        List<ParkingSlot> availableSlots = new ArrayList<>();
        for (ParkingSlot slot : floor.getSlots()) {
            if(slot.getType() == required && !slot.isOccupied()) {
                availableSlots.add(slot);
            }
        }
        return availableSlots;
    }

    private SlotType mapToSlotType(VehicleType type) {
        return switch (type) {
            case BIKE -> SlotType.SMALL;
            case CAR -> SlotType.MEDIUM;
            case TRUCK -> SlotType.LARGE;
        };
    }
}
