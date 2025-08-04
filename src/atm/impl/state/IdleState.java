package atm.impl.state;

import atm.interfaces.ATMState;
import atm.model.Atm;
import atm.model.Card;
import atm.model.TransactionContext;

public class IdleState implements ATMState {
    
    public void insertCard(Atm atm, Card card) {
        System.out.println("Card inserted.");
        atm.setCurrentCard(card);
        atm.setCurrentState(new CardInsertedState());
    }

    public void enterPin(Atm atm, String pin) {
        System.out.println("Insert card first.");
    }

    public void executeTransaction(TransactionContext context) {
        System.out.println("Insert card first.");
    }

    public void ejectCard(Atm atm) {
        System.out.println("No card to eject.");
    }
}