package snakeandladder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import interfaces.LLDService;
import snakeandladder.interfaces.Cell;
import snakeandladder.model.Dice;
import snakeandladder.model.GameBoard;
import snakeandladder.model.Player;
import snakeandladder.model.cells.Ladder;
import snakeandladder.model.cells.MultiDice;
import snakeandladder.model.cells.Normal;
import snakeandladder.model.cells.Penalty;
import snakeandladder.model.cells.PowerUp;
import snakeandladder.model.cells.Snake;
import snakeandladder.services.GameService;

public class SnakeAndLadderLLDService implements LLDService {

    @Override
    public void runExamples() throws Exception {
        simualateGames();
    }

    private void simualateGames() {
        System.out.println("----------------- SNAKE AND LADDER -----------------");
        System.out.println("------------ SIMULATING GAMES -----------------------");

        GameService gameService = GameService.getInstance();

        GameBoard gameBoard1 = createGameBoard(100, 3);
        gameService.playGame(gameBoard1);
        System.out.println("Game 1 completed.");
        System.out.println("\n\nStarting Game 2...");

        GameBoard gameBoard2 = createGameBoard(100, 6);
        gameService.playGame(gameBoard2);
        System.out.println("---------------- EXIT ------------------------------\n");
    }

    private GameBoard createGameBoard(int size, int numberOfPlayers) {
        Dice dice = createDice();
        List<Cell> cells = createCells(size);
        Queue<Player> players = createPlayers(numberOfPlayers);
        GameBoard gameBoard = new GameBoard(size, dice, cells, players);
        return gameBoard;
    }

    private List<Cell> createCells(int size) {

        List<Cell> cells = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            cells.add(new Normal(i));
        }

        Random random = new Random();
        Set<Integer> used = new HashSet<>();
        int powerUps = 3;
        int penalties = 5;
        int multiDice = 2;
        int snakes = 5;
        int ladders = 5;
        while (powerUps > 0 || penalties > 0 || multiDice > 0 || snakes > 0 || ladders > 0) {
            int position = (int) (Math.random() * size) + 1;
            if (used.contains(position)) continue;
            used.add(position);

            if (powerUps > 0) {
                cells.set(position - 1, new PowerUp(position, random.nextInt(1, 6)));
                powerUps--;
            } else if (penalties > 0) {
                cells.set(position - 1, new Penalty(position, random.nextInt(1, 6)));
                penalties--;
            } else if (multiDice > 0) {
                cells.set(position - 1, new MultiDice(position, 1));
                multiDice--;
            } else if (snakes > 0) {
                cells.set(position - 1, new Snake(position, random.nextInt(2, position)));
                snakes--;
            } else if (ladders > 0) {
                cells.set(position - 1, new Ladder(position, random.nextInt(position + 1, size)));
                ladders--;
            }
        }

        return cells;
    }

    private Queue<Player> createPlayers(int numberOfPlayers) {
        List<String> playerNames = List.of("Bhanu", "Sai", "Divya", "Prathima", "Pavani", "Vice");
        numberOfPlayers = Math.min(numberOfPlayers, playerNames.size());
        numberOfPlayers = Math.max(numberOfPlayers, 2); // Ensure at least 2 players

        Queue<Player> players = new LinkedList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new Player(playerNames.get(i)));
        }
        return players;
    }

    private Dice createDice() {
        return new Dice(6); // Standard 6-sided dice
    }
}
