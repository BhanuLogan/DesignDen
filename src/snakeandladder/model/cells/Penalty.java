package snakeandladder.model.cells;

import snakeandladder.interfaces.Cell;
import snakeandladder.util.SnakeAndLadderConstants.CellType;

public class Penalty implements Cell {

    private int position;
    private int penalty;

    public Penalty(int position, int penalty) {
        this.position = position;
        this.penalty = penalty;
    }

    @Override
    public CellType getCellType() {
        return CellType.PENALTY;
    }

    @Override
    public int getPosition() {
        return Math.max(1, position - penalty);
    }

    @Override
    public int extraRolls() {
        return 0;
    }

}
