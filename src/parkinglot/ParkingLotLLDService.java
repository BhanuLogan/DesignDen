package parkinglot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import interfaces.LLDService;
import parkinglot.model.ParkingFloor;
import parkinglot.model.ParkingLot;
import parkinglot.model.ParkingSlot;
import parkinglot.model.Ticket;
import parkinglot.model.Vehicle;
import parkinglot.services.ParkingLotService;
import parkinglot.util.ParkingLotConstants.PaymentLocation;
import parkinglot.util.ParkingLotConstants.PaymentMethod;
import parkinglot.util.ParkingLotConstants.PricingStrategyType;
import parkinglot.util.ParkingLotConstants.SlotType;
import parkinglot.util.ParkingLotConstants.SpotSelectionStrategyType;
import parkinglot.util.ParkingLotConstants.VehicleType;

public class ParkingLotLLDService implements LLDService {

    private ParkingLotService parkingLotService = new ParkingLotService();

    @Override
    public void runExamples() throws Exception {
        runParkingLotExample();
    }

    private void runParkingLotExample() {

        Vehicle vehicle = null;
        ParkingLot lot = createParkingLotWithSingleFloor();
        Scanner scanner = new Scanner(System.in);
        Ticket ticket = null;
        while(true) {
            showMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    if(vehicle == null) vehicle = createVehicle();
                    System.out.println("Checking availability for vehicle type " + vehicle.getType() + ":");
                    if(parkingLotService.getAvailableSlot(lot, vehicle.getType()) == null) {
                        vehicle = null;
                    }
                    
                    break;
                case 2:
                    if(vehicle == null) vehicle = createVehicle();
                    System.out.println("Available slots for vehicle type " + vehicle.getType() + ":");
                    parkingLotService.showAvailableParkingSlots(lot, vehicle.getType());
                    break;
                case 3:
                    if(vehicle == null) vehicle = createVehicle();
                    ticket = parkingLotService.parkVehicle(lot, vehicle);
                    break;
                case 4:
                    parkingLotService.exitVehicle(lot, ticket.getTicketId(), selectPaymentMethod(scanner), selectPaymentLocation(scanner));
                    vehicle = null; // Reset vehicle after exit
                    break;
                case 5:
                    if(vehicle == null) vehicle = createVehicle();
                    parkingLotService.bookInAdvance(lot, vehicle, new Date(System.currentTimeMillis() + 2L * 24 * 60 * 60 * 1000));
                    break;
                case 6:
                    parkingLotService.showActiveBookings();
                    break;
                case 7:
                    System.out.println("Stopping simulation...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private PaymentMethod selectPaymentMethod(Scanner scanner) {
        showPaymentMethodMenu();
        while(true) {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    return PaymentMethod.CASH;
                case 2:
                    return PaymentMethod.CREDIT_CARD;
                case 3:
                    return PaymentMethod.DEBIT_CARD;
                case 4:
                    return PaymentMethod.UPI;
                default:
                    System.out.println("Invalid payment method selected. Please try again.");
            }
        }
    }

    private PaymentLocation selectPaymentLocation(Scanner scanner) {
        showPaymentLocalationMenu();
        while(true) {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    return PaymentLocation.SELF_PAYMENT_MACHINE;
                case 2:
                    return PaymentLocation.EXIT_GATE;
                default:
                    System.out.println("Invalid payment location selected. Please try again.");
            }
        }
    }
    private void showMenu() {
        System.out.println("\n\n1. Show available slots");
        System.out.println("2. Check Availability");
        System.out.println("3. Park Vehicle");
        System.out.println("4. Book in Advance");
        System.out.println("5. Exit Vehicle");
        System.out.println("6. Show Active Bookings");
        System.out.println("7. Stop Simulation");
        System.out.print("Select an option: ");
    }

    private void showPaymentLocalationMenu() {
        System.out.println("\n\n1. Self Payment");
        System.out.println("2. Exit Gate");
        System.out.print("Select payment location: ");
    }

    private void showPaymentMethodMenu() {
        System.out.println("\n\n1. Cash");
        System.out.println("2. Credit Card");
        System.out.println("3. Debit Card");
        System.out.println("4. UPI");
        System.out.print("Select payment method: ");
    }

    private void runAdvanceBookingExample() {
        // Example code for running the advance booking feature
        // This can include booking a slot in advance, checking availability, etc.
        
        System.out.println("Running Advance Booking Example...");
        ParkingLot lot = createParkingLotWithSingleFloor();
        print(lot);

        Vehicle vehicle = createVehicle();
        parkingLotService.bookInAdvance(lot, vehicle, new Date(System.currentTimeMillis() + 2L * 24 * 60 * 60 * 1000));

        print(lot);
    }

    private void print(ParkingLot lot) {
        System.out.println("\n\n--------------------------------------------------");
        System.out.println("Welcome to " + lot.getName().toUpperCase() + "!!");
        System.out.println("Pricing Strategy: " + lot.getPricingStrategyType());
        System.out.println("Spot Selection Strategy: " + lot.getSpotSelectionStrategyType());
        System.out.println("Number of Floors: " + lot.getFloors().size());

        for (ParkingFloor floor : lot.getFloors()) {
            System.out.println("\nFloor Name: " + floor.getFloorName() + ", Floor Number: " + floor.getFloorNumber());
            for (ParkingSlot slot : floor.getSlots()) {
                System.out.println("Slot ID: " + slot.getId() + ", Type: " + slot.getType() +
                        ", Distance from Entry: " + slot.getDistanceFromEntry() +
                        ", Distance from Exit: " + slot.getDistanceFromExit() +
                        ", Occupied: " + slot.isOccupied());
            }
        }
        System.out.println("Total Slots: " + lot.getFloors().stream()
                .flatMap(floor -> floor.getSlots().stream())
                .count());
        System.out.println("Total Available Slots: " + lot.getFloors().stream()
                .flatMap(floor -> floor.getSlots().stream())
                .filter(slot -> !slot.isOccupied())
                .count());
        System.out.println("Total Occupied Slots: " + lot.getFloors().stream()
                .flatMap(floor -> floor.getSlots().stream())
                .filter(ParkingSlot::isOccupied)
                .count());
        System.out.println("--------------------------------------------------\n\n");
    }
    

    private ParkingLot createParkingLotWithSingleFloor() {
        ParkingFloor groundFloor = createParkingFloor(0, "Ground floor", 2);
        List<ParkingFloor> floors = new ArrayList<>();
        floors.add(groundFloor);
        return new ParkingLot("Design Den Parking Lot", floors, SpotSelectionStrategyType.NEAREST_TO_EXIT, PricingStrategyType.HOURLY); 
    }

    private ParkingLot createParkingLotWithMultipleFloors() {
        ParkingFloor groundFloor = createParkingFloor(0, "Ground floor", 2);
        ParkingFloor firstFloor = createParkingFloor(0, "First floor", 3);
        ParkingFloor secondFloor = createParkingFloor(0, "Second floor", 3);
        List<ParkingFloor> floors = new ArrayList<>();
        floors.add(groundFloor);
        floors.add(firstFloor);
        floors.add(secondFloor);
        return new ParkingLot("Design Den Parking Lot", floors, SpotSelectionStrategyType.NEAREST_TO_EXIT, PricingStrategyType.HOURLY); 
    }

    private ParkingFloor createParkingFloor(int floorNumber, String name, int capacity) {
        List<ParkingSlot> slots = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            ParkingSlot slot = createParkingSlot(i);
            slots.add(slot);
        }
        return new ParkingFloor(name, floorNumber, slots);
    }

    private ParkingSlot createParkingSlot(int seq) {
        Random random = new Random();
        int distanceFromEntry = random.nextInt(1, 10);
        int distanceFromExit = random.nextInt(1, 10);

        int randomSlot = random.nextInt(0, SlotType.values().length); 
        SlotType slotType = SlotType.values()[randomSlot];
        String id = slotType.name() + "-" + seq;
        return new ParkingSlot(id, slotType, distanceFromEntry, distanceFromExit);
    }

    private Vehicle createVehicle() {
        Random random = new Random();
        int randomType = random.nextInt(0, VehicleType.values().length); 
        VehicleType vehicleType = VehicleType.values()[randomType];
        String vehicleNumber = vehicleType.name() + "-" + random.nextInt(1000, 9999);
        return new Vehicle(vehicleNumber, vehicleType);
    }
}
