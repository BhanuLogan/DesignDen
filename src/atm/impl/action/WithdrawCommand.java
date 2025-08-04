package atm.impl.action;

import java.sql.Date;

import atm.interfaces.Command;
import atm.model.Card;
import atm.model.Transaction;
import atm.model.TransactionContext;
import atm.util.AtmConstants.TransactionType;
import atm.model.Atm;

public class WithdrawCommand implements Command {
    
    @Override
    public void execute(TransactionContext context) {
        Atm atm = context.getAtm();
        double amount = context.getAmount();
        if(amount > atm.getCashAvailable()) {
            System.out.println("Cash not available");
            return ;
        }

        Card card = atm.getCurrentCard();
        if (card != null) {
            if (card.getBalance() >= amount) {
                atm.dispenseCash(amount);
                card.withdraw(amount);
                card.addTransaction(createTransaction(context));
                System.out.println("Withdrawal successful. Amount withdrawn: " + amount);
            } else {
                System.out.println("Insufficient balance for withdrawal.");
            }
        } else {
            System.out.println("No account found for withdrawal.");
        }
    }

    private Transaction createTransaction(TransactionContext context) {
        return new Transaction("ATM withdrawal", context.getAmount(), TransactionType.DEBIT, new Date(System.currentTimeMillis()));
    }
}
