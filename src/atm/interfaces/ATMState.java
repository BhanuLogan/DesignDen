package atm.interfaces;

import atm.model.Atm;
import atm.model.Card;
import atm.model.TransactionContext;

public interface ATMState {
    void insertCard(Atm atm, Card card);
    void enterPin(Atm atm, String pin);
    void executeTransaction(TransactionContext context);
    void ejectCard(Atm atm);
}