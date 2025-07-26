package parkinglot.impl.spotselection;

import java.util.Comparator;

import parkinglot.interfaces.SpotSelectionStrategy;
import parkinglot.model.ParkingSlot;

public class NearestToEntryStrategy implements SpotSelectionStrategy {
    public Comparator<ParkingSlot> getComparator() {
        return Comparator.comparingInt(ParkingSlot::getDistanceFromEntry);
    }
}