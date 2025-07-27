package elevator.util;

public class ElevatorConstants {
    public static enum Direction {
        UP,
        DOWN,
        NONE
    }

    public static enum ElevatorState {
        MOVING,
        IDLE,
        MAINTENANCE
    }

    public static enum PriorityLevel {
        HIGH, NORMAL
    }

    public static enum DoorAction {
        OPEN,
        CLOSE
    }

    public static enum DoorState {
        OPEN,
        CLOSED
    }
}
