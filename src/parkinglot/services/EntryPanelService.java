package parkinglot.services;

import parkinglot.model.EntryPanel;
import parkinglot.model.Ticket;
import parkinglot.model.Vehicle;

class EntryPanelService {
    private final ParkingLotService lot;
    public EntryPanelService(ParkingLotService lot) { this.lot = lot; }

    public Ticket issueTicket(EntryPanel entryPanel, Vehicle vehicle) {
        return lot.parkVehicle(entryPanel.getLot(), vehicle);
    }
}