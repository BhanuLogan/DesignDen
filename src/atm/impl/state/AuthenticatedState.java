package atm.impl.state;

import atm.factory.ATMCommandFactory;
import atm.interfaces.ATMState;
import atm.interfaces.Command;
import atm.model.Atm;
import atm.model.Card;
import atm.model.TransactionContext;
import atm.util.AtmConstants.ActionType;

public class AuthenticatedState implements ATMState {
    
    public void insertCard(Atm atm, Card card) {
        System.out.println("Card already inserted.");
    }
    
    public void enterPin(Atm atm, String pin) {
        System.out.println("Already authenticated.");
    }
    
    public void executeTransaction(TransactionContext context) {
        ActionType action = context.getActionType();
        Command cmd = ATMCommandFactory.getCommand(action);
        cmd.execute(context);
    }

    public void ejectCard(Atm atm) {
        System.out.println("Card ejected.");
        atm.reset();
    }
}