package snakeandladder.model;

import java.util.List;
import java.util.Queue;

import snakeandladder.interfaces.Cell;

public class GameBoard {
    private int size;
    private List<Cell> cells;
    private Dice dice;
    private Queue<Player> players;
    
    public GameBoard(int size, Dice dice, List<Cell> cells, Queue<Player> players) {
        if (size <= 0) {
            throw new IllegalArgumentException("Board size must be greater than zero.");
        }
        this.size = size;
        this.dice = dice;
        this.cells = cells;
        this.players = players;
    }

    public int getSize() {
        return size;
    }

    public Player getCurrentPlayer() {
        return players.peek();
    }

    public Player moveToNextPlayer() {
        if (players.isEmpty()) {
            throw new IllegalStateException("No players left to play.");
        }
        players.offer(players.poll()); // Add them back to the queue
        return players.peek(); // Return the next player
    }

    public int rollDice() {
        return dice.roll();
    }

    public boolean isGameOver() {
        for (Player player : players) {
            if (player.getPosition() >= size) {
                return true; // A player has reached the end
            }
        }
        return false; // No player has won yet
    }

    public String getWinner() {
        for (Player player : players) {
            if (player.getPosition() >= size) {
                return player.getName(); // Return the name of the winning player
            }
        }
        return null; // No winner yet
    }

    public Cell getCell(int position) {
        if(position < 1 || position > size) {
            return null;
        }
        return cells.get(position - 1);
    }
}
