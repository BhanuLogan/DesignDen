package atm.model;

import java.util.Map;

import atm.impl.state.IdleState;
import atm.interfaces.ATMState;

public class Atm {
    private String id;
    private String location;
    private CashDispenser cashDispenser;
    private ATMState currentState;
    private Card currentCard;

    public Atm(String id, String location) {
        this.id = id;
        this.location = location;
        this.cashDispenser = new CashDispenser();
        this.currentState = new IdleState();
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public double getCashAvailable() {
        return cashDispenser.getCashAvailable();
    }

    public void dispenseCash(double amount) {
        cashDispenser.dispenseCash((int) amount);
    }

    public void refillCash(double amount, int count) {
        cashDispenser.addCash((int) amount, count);
    }

    public void refillCash(Map<Integer, Integer> cash) {
        cashDispenser.addCash(cash);
    }

    public void setCurrentState(ATMState state) {
        this.currentState = state;
    }

    public ATMState getCurrentState() {
        return currentState;
    }

    public void setCurrentCard(Card card) {
        this.currentCard = card;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public void reset() {
        this.currentCard = null;
        this.currentState = new IdleState();
    }
}
