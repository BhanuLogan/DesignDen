package snakeandladder.interfaces;

import snakeandladder.util.SnakeAndLadderConstants.CellType;

public interface Cell {
    public CellType getCellType();
    public int getPosition();
    public int extraRolls();
}