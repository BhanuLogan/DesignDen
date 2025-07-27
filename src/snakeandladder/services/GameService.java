package snakeandladder.services;

import snakeandladder.interfaces.Cell;
import snakeandladder.model.GameBoard;
import snakeandladder.model.Player;

public class GameService {

    private GameService() {}

    private static class InstanceHolder {
        private static final GameService INSTANCE = new GameService();
    }

    public static GameService getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void playGame(GameBoard gameBoard) {
        while(!gameBoard.isGameOver()) {
            Player currentPlayer = gameBoard.getCurrentPlayer();
            int currentPosition = currentPlayer.getPosition();
            System.out.println("\nCurrent Player: " + currentPlayer.getName() + " at position: " + currentPosition);
            int diceRoll = gameBoard.rollDice();
            
            int nextPosition = currentPosition + diceRoll;
            Cell cell = gameBoard.getCell(nextPosition);
            if (cell == null) {
                System.out.println("Invalid move for " + currentPlayer.getName() + ". Staying at position " + currentPosition);
                gameBoard.moveToNextPlayer();
                continue;
            }
            System.out.println(currentPlayer.getName() + " rolled a " + diceRoll + " and moved from " + currentPosition + " to " + cell.getPosition() + " on cell type: " + cell.getCellType());
            nextPosition = cell.getPosition();
            int prevPosition = nextPosition;
            int rolls = cell.extraRolls();
            while(rolls > 0) {
                prevPosition = nextPosition;
                diceRoll = gameBoard.rollDice();
                nextPosition = prevPosition + diceRoll;
                cell = gameBoard.getCell(nextPosition);
                if (cell == null) {
                    nextPosition = prevPosition; // No valid cell found, stay in place
                    System.out.println("Invalid move for " + currentPlayer.getName() + ". Staying at position " + currentPosition);
                    break; // No valid cell found
                }
                rolls--;
                rolls += cell.extraRolls();
                nextPosition = cell.getPosition();
                System.out.println(currentPlayer.getName() + " rolled a " + diceRoll + " and moved from " + prevPosition + " to " + nextPosition + " on cell type: " + cell.getCellType() + " rolls left: " + rolls);
            }

            currentPlayer.setPosition(nextPosition);
            gameBoard.moveToNextPlayer();
            System.out.println(currentPlayer.getName() + " is now at position: " + currentPlayer.getPosition());
        }
        System.out.println("Hurrah!!! Game Over! Winner: " + gameBoard.getWinner());
    }
}