package parkinglot.model;

import java.util.List;

public class ParkingFloor {
    private final String floorName;
    private final int floorNumber;
    private final List<ParkingSlot> slots;

    public ParkingFloor(String floorName, int floorNumber, List<ParkingSlot> slots) {
        this.floorName = floorName;
        this.floorNumber = floorNumber;
        this.slots = slots;
    }

    public List<ParkingSlot> getSlots() {
        return slots;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public String getFloorName() {
        return floorName;
    }
}