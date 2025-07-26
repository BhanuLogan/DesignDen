package parkinglot.model;

import parkinglot.util.ParkingLotConstants.SlotType;

public class ParkingSlot {
    private final String id;
    private final SlotType type;
    private boolean occupied;
    private final int distanceFromEntry;
    private final int distanceFromExit;
    
    public ParkingSlot(String id, SlotType type, int distanceFromEntry, int distanceFromExit) {
        this.id = id;
        this.type = type;
        this.occupied = false;
        this.distanceFromEntry = distanceFromEntry;
        this.distanceFromExit = distanceFromExit;
    }

    public boolean isOccupied() { return occupied; }
    public void occupy() { occupied = true; }
    public void release() { occupied = false; }
    public SlotType getType() { return type; }
    public String getId() { return id; }
    public int getDistanceFromEntry() { return distanceFromEntry; }
    public int getDistanceFromExit() { return distanceFromExit; }
}