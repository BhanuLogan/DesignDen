package parkinglot.interfaces;

import parkinglot.util.ParkingLotConstants.VehicleType;

public interface PricingStrategy {
    double calculateCharge(VehicleType type, long hours);
}