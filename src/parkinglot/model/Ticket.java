package parkinglot.model;

import java.util.Date;

public class Ticket {
    private final String ticketId;
    private final Vehicle vehicle;
    private final ParkingSlot slot;
    private final Date entryTime;
    private Date exitTime;
    private boolean isAdvanceBooking;

    public Ticket(String ticketId, Vehicle vehicle, ParkingSlot slot, boolean isAdvanceBooking) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.slot = slot;
        this.entryTime = new Date();
        this.isAdvanceBooking = isAdvanceBooking;
    }

    public void setExitTime(Date exitTime) { this.exitTime = exitTime; }
    public long getDurationInHours() {
        long duration = exitTime.getTime() - entryTime.getTime();
        return Math.max(1, duration / (1000 * 60 * 60));
    }

    public Vehicle getVehicle() { return vehicle; }
    public ParkingSlot getSlot() { return slot; }
    public String getTicketId() { return ticketId; }
    public Date getEntryTime() { return entryTime; }
}
