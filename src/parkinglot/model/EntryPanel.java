package parkinglot.model;

public class EntryPanel {
    private final ParkingLot lot;
    public EntryPanel(ParkingLot lot) { this.lot = lot; }

    public ParkingLot getLot() {
        return lot;
    }
}