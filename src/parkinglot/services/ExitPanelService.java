package parkinglot.services;

import parkinglot.model.ExitPanel;
import parkinglot.model.Payment;
import parkinglot.util.ParkingLotConstants.PaymentLocation;
import parkinglot.util.ParkingLotConstants.PaymentMethod;

class ExitPanelService {
    private final ParkingLotService lot;
    public ExitPanelService(ParkingLotService lot) { this.lot = lot; }

    public Payment processExit(ExitPanel exitPanel, String ticketId, PaymentMethod method, PaymentLocation location) {
        return lot.exitVehicle(exitPanel.getLot(), ticketId, method, location);
    }
}