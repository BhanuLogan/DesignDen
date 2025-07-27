package snakeandladder.model.cells;

import snakeandladder.interfaces.Cell;
import snakeandladder.util.SnakeAndLadderConstants.CellType;

public class PowerUp implements Cell{

    private int position;
    private int boost;

    public PowerUp(int position, int boost) {
        this.position = position;
        this.boost = boost;
    }

    @Override
    public CellType getCellType() {
        return CellType.POWER_UP;
    }

    @Override
    public int getPosition() {
        return position + boost;
    }

    @Override
    public int extraRolls() {
        return 0;
    }

}
