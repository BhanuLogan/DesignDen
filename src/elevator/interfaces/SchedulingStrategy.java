package elevator.interfaces;

import java.util.List;

import elevator.model.Elevator;
import elevator.model.Request;

public interface SchedulingStrategy {
    Elevator assignElevator(List<Elevator> elevators, Request request);
}