package parkinglot.model;

public class ExitPanel {
    private final ParkingLot lot;
    public ExitPanel(ParkingLot lot) { this.lot = lot; }

    public ParkingLot getLot() {
        return lot;
    }
}