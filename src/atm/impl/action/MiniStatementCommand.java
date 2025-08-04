package atm.impl.action;

import java.util.List;

import atm.interfaces.Command;
import atm.model.Card;
import atm.model.Atm;
import atm.model.Transaction;
import atm.model.TransactionContext;

public class MiniStatementCommand implements Command {

    @Override
    public void execute(TransactionContext context) {
        Atm atm = context.getAtm();
        Card card = atm.getCurrentCard();
        if (card != null) {
            System.out.println("Mini Statement for Account: " + card.getAccountNumber());
            
            int cnt = 10;
            List<Transaction> history = card.getTransactionHistory();
            int index = history.size() - 1;
            Transaction transaction = null;
            while(cnt-- > 0 && index >= 0) {
                transaction = history.get(index--);
                System.out.println("Date: " + transaction.getDate() + ", Type: " + transaction.getType() +
                        ", Amount: " + transaction.getAmount());
            }
        } else {
            System.out.println("No account found for mini statement.");
        }
    }

}
