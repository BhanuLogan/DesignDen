package parkinglot.services;

import java.sql.Date;

import parkinglot.model.ParkingLot;
import parkinglot.model.Ticket;
import parkinglot.model.Vehicle;

public class AdvanceBookingService {
    private final ParkingLotService parkingLotService;

    public AdvanceBookingService() {
        this.parkingLotService = new ParkingLotService();
    }

    public Ticket bookTicketInAdvance(ParkingLot parkingLot, Vehicle vehicle, Date bookingTime) {
        return parkingLotService.bookInAdvance(parkingLot, vehicle, bookingTime);
    }
}
