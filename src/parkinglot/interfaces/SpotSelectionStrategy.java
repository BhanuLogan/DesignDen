package parkinglot.interfaces;

import java.util.Comparator;

import parkinglot.model.ParkingSlot;

public interface SpotSelectionStrategy {
    Comparator<ParkingSlot> getComparator();
}