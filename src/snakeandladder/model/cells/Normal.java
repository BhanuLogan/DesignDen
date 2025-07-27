package snakeandladder.model.cells;

import snakeandladder.interfaces.Cell;
import snakeandladder.util.SnakeAndLadderConstants.CellType;

public class Normal implements Cell {
    private int position;

    public Normal(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public int nextPosition() {
        return position + 1;
    }

    @Override
    public CellType getCellType() {
        return CellType.NORMAL;
    }

    public int extraRolls() {
        return 0;
    }
}
