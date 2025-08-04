package atm.services;

import atm.interfaces.ATMState;
import atm.model.Atm;
import atm.model.Card;
import atm.model.TransactionContext;

public class AtmService {

    public void insertCard(Atm atm, Card card) {
        ATMState currentState = atm.getCurrentState();
        currentState.insertCard(atm, card);
    }

    public void enterPin(Atm atm, String pin) {
        ATMState currentState = atm.getCurrentState();
        currentState.enterPin(atm, pin);
    }

    public void executeTransaction(TransactionContext context) {
        Atm atm = context.getAtm();
        ATMState currentState = atm.getCurrentState();
        currentState.executeTransaction(context);
    }

    public void ejectCard(Atm atm) {
        ATMState currentState = atm.getCurrentState();
        currentState.ejectCard(atm);
    }
}
