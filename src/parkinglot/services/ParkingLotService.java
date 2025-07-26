package parkinglot.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import parkinglot.factory.PaymentProcessorFactory;
import parkinglot.factory.PricingFactory;
import parkinglot.interfaces.PaymentProcessor;
import parkinglot.interfaces.PricingStrategy;
import parkinglot.model.ParkingFloor;
import parkinglot.model.ParkingLot;
import parkinglot.model.ParkingSlot;
import parkinglot.model.Payment;
import parkinglot.model.Ticket;
import parkinglot.model.Vehicle;
import parkinglot.util.ParkingLotConstants.PaymentLocation;
import parkinglot.util.ParkingLotConstants.PaymentMethod;
import parkinglot.util.ParkingLotConstants.SpotSelectionStrategyType;
import parkinglot.util.ParkingLotConstants.VehicleType;

public class ParkingLotService {
    private final Map<String, Ticket> activeTickets;
    private ParkingFloorService parkingFloorService;

    public ParkingLotService() {
        this.activeTickets = new HashMap<>();
        this.parkingFloorService = new ParkingFloorService();
    }

    public Ticket parkVehicle(ParkingLot lot, Vehicle vehicle) {
        SpotSelectionStrategyType strategy = lot.getSpotSelectionStrategyType();
        List<ParkingFloor> floors = lot.getFloors();
        for (ParkingFloor floor : floors) {
            ParkingSlot slot = parkingFloorService.findAvailableSlot(floor, vehicle.getType(), strategy);
            if (slot != null) {
                slot.occupy();
                Ticket ticket = new Ticket(UUID.randomUUID().toString(), vehicle, slot, false);
                activeTickets.put(ticket.getTicketId(), ticket);
                System.out.println("Vehicle parked at floor: " + floor.getFloorName() + " slot: " + slot.getId());
                return ticket;
            }
        }
        throw new RuntimeException("No slot available for vehicle type: " + vehicle.getType());
    }

    public Payment exitVehicle(ParkingLot lot, String ticketId, PaymentMethod method, PaymentLocation location) {
        Ticket ticket = activeTickets.get(ticketId);
        if (ticket == null) throw new RuntimeException("Invalid ticket ID");
        ticket.setExitTime(new Date());
        long hours = ticket.getDurationInHours();

        PricingStrategy pricingStrategy = PricingFactory.getPricingStrategy(lot.getPricingStrategyType());

        double amount = pricingStrategy.calculateCharge(ticket.getVehicle().getType(), hours);
        ticket.getSlot().release();
        activeTickets.remove(ticketId);

        Payment payment = new Payment(ticket, amount, method, location);
        PaymentProcessor processor = PaymentProcessorFactory.getProcessor(method);
        processor.process(payment);
        return payment;
    }

    public Ticket bookInAdvance(ParkingLot lot, Vehicle vehicle, Date bookingTime) {
        SpotSelectionStrategyType strategy = lot.getSpotSelectionStrategyType();
        if (bookingTime.after(new Date(System.currentTimeMillis() + 3L * 24 * 60 * 60 * 1000))) {
            throw new IllegalArgumentException("Advance booking allowed up to 3 days in advance only.");
        }

        List<ParkingFloor> floors = lot.getFloors();
        for (ParkingFloor floor : floors) {
            ParkingSlot slot = parkingFloorService.findAvailableSlot(floor, vehicle.getType(), strategy);
            if (slot != null) {
                slot.occupy(); // Mark the slot as occupied for advance booking(can be made occupied after the booking time)
                Ticket ticket = new Ticket(UUID.randomUUID().toString(), vehicle, slot, true);
                activeTickets.put(ticket.getTicketId(), ticket);
                return ticket;
            }
        }
        System.out.println("No slot available for advance booking.");
        return null; // No slot available for advance booking
    }

    public void showAvailableParkingSlots(ParkingLot lot, VehicleType type) {
        int availableSlots = 0;
        for (ParkingFloor floor : lot.getFloors()) {
            for (ParkingSlot slot : parkingFloorService.getAvailableSlots(floor, type)) {
                availableSlots++;
                System.out.println("Floor: " + floor.getFloorName() + " and Slot ID: " + slot.getId() + " is available.");
            }
        }

        if(availableSlots == 0) {
            System.out.println("No slots available in the parking lot.");
        } else {
            System.out.println("Total available slots: " + availableSlots);
        }
    }

    public ParkingSlot getAvailableSlot(ParkingLot lot, VehicleType type) {
        SpotSelectionStrategyType strategy = lot.getSpotSelectionStrategyType();
        for (ParkingFloor floor : lot.getFloors()) {
            ParkingSlot slot = parkingFloorService.findAvailableSlot(floor, type, strategy);
            if (slot != null) {
                return slot;
            }
        }
        System.out.println("No available slot found for type: " + type);
        return null; // No available slot found
    }

    public void showActiveBookings() {
        if (activeTickets.isEmpty()) {
            System.out.println("No active bookings.");
            return;
        }
        System.out.println("Active Bookings:");
        for (Ticket ticket : activeTickets.values()) {
            System.out.println("Ticket ID: " + ticket.getTicketId() + ", Vehicle: " + ticket.getVehicle().getType() +
                    ", Slot: " + ticket.getSlot().getId() + ", Booking Time: " + ticket.getEntryTime());
        }
    }
}