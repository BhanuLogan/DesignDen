package atm.model;

import java.util.Map;

import atm.util.AtmConstants.ActionType;

public class TransactionContext {
    private Atm atm;
    private double amount;
    private ActionType actionType;
    private Map<Integer, Integer> cash;

    public TransactionContext(ActionType actionType) {
        this.actionType = actionType;
    }

    public Atm getAtm() {
        return atm;
    }

    public Map<Integer, Integer> getCash() {
        return cash;
    }

    public double getAmount() {
        return amount;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setAtm(Atm atm) {
        this.atm = atm;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCash(Map<Integer, Integer> cash) {
        this.cash = cash;
    }
}