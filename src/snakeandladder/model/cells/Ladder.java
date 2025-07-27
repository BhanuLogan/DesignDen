package snakeandladder.model.cells;

import snakeandladder.interfaces.Cell;
import snakeandladder.util.SnakeAndLadderConstants.CellType;

public class Ladder implements Cell {
    private int start;
    private int end;

    public Ladder(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public CellType getCellType() {
        return CellType.LADDER;
    }

    @Override
    public int getPosition() {
        return end;
    }

    public int extraRolls() {
        return 0;
    }
}
