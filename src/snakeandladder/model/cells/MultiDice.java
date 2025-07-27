package snakeandladder.model.cells;

import snakeandladder.interfaces.Cell;
import snakeandladder.util.SnakeAndLadderConstants.CellType;

public class MultiDice implements Cell {

    private int position;
    private int extraRolls;

    public MultiDice(int position, int extraRolls) {
        this.position = position;
        this.extraRolls = extraRolls;
    }

    @Override
    public CellType getCellType() {
        return CellType.MULTI_DICE;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public int extraRolls() {
        return extraRolls;
    }

}
