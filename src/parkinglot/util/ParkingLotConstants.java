package parkinglot.util;

public class ParkingLotConstants {
    public enum VehicleType { BIKE, CAR, TRUCK }
    public enum SlotType { SMALL, MEDIUM, LARGE }
    public enum PaymentStatus { PENDING, COMPLETED, FAILED }
    public enum PaymentMethod { CASH, UPI, CREDIT_CARD, DEBIT_CARD }
    public enum PaymentLocation { SELF_PAYMENT_MACHINE, EXIT_GATE }
    public enum SpotSelectionStrategyType { NEAREST_TO_EXIT, NEAREST_TO_ENTRY }
    public enum PricingStrategyType { HOURLY, HOURLY_WITH_MIN_BASE_FARE }
}