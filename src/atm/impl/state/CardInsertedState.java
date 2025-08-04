package atm.impl.state;

import atm.interfaces.ATMState;
import atm.model.Atm;
import atm.model.Card;
import atm.model.TransactionContext;

public class CardInsertedState implements ATMState {
    
    public void insertCard(Atm atm, Card card) {
        System.out.println("Card already inserted.");
    }
    
    public void enterPin(Atm atm, String pin) {
        if (atm.getCurrentCard().validatePin(pin)) {
            System.out.println("PIN correct.");
            atm.setCurrentState(new AuthenticatedState());
        } else {
            System.out.println("Invalid PIN.");
        }
    }

    public void executeTransaction(TransactionContext context) {
        System.out.println("Enter PIN first.");
    }

    public void ejectCard(Atm atm) {
        System.out.println("Card ejected.");
        atm.reset();
    }
}