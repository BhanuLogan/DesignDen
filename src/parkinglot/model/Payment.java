package parkinglot.model;

import parkinglot.util.ParkingLotConstants.PaymentLocation;
import parkinglot.util.ParkingLotConstants.PaymentMethod;
import parkinglot.util.ParkingLotConstants.PaymentStatus;

public class Payment {
    private final Ticket ticket;
    private final double amount;
    private final PaymentMethod method;
    private final PaymentLocation location;
    private PaymentStatus status;

    public Payment(Ticket ticket, double amount, PaymentMethod method, PaymentLocation location) {
        this.ticket = ticket;
        this.amount = amount;
        this.method = method;
        this.location = location;
        this.status = PaymentStatus.PENDING;
    }

    public void completePayment() { this.status = PaymentStatus.COMPLETED; }
    public double getAmount() { return amount; }
    public PaymentStatus getStatus() { return status; }
    public PaymentMethod getMethod() { return method; }
    public PaymentLocation getLocation() { return location; }
}