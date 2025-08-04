package atm.impl.action;

import atm.interfaces.Command;
import atm.model.Card;
import atm.model.TransactionContext;

public class BalanceInquiryCommand implements Command {
    
    public void execute(TransactionContext context) {
        Card card = context.getAtm().getCurrentCard();
        if (card != null) {
            System.out.println("Current balance: " + card.getBalance());
        } else {
            System.out.println("No account found for balance inquiry.");
        }
    }
}
