package snakeandladder.model;

public class Dice {
    private int sides;

    public Dice(int sides) {
        if (sides < 1) {
            throw new IllegalArgumentException("Dice must have at least one side.");
        }
        this.sides = sides;
    }

    public int roll() {
        return (int) (Math.random() * sides) + 1;
    }
}
