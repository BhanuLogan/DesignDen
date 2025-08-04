package atm.impl.action;

import java.sql.Date;
import java.util.Map;

import atm.interfaces.Command;
import atm.model.Card;
import atm.model.Transaction;
import atm.model.TransactionContext;
import atm.util.AtmConstants.TransactionType;
import atm.model.Atm;

public class DepositCommand implements Command {
    
    @Override
    public void execute(TransactionContext context) {
        Atm atm = context.getAtm();
        Card card = atm.getCurrentCard();
        Map<Integer, Integer> cash = context.getCash();
        if (card != null) {
            double totalDeposit = cash.entrySet().stream()
                .mapToDouble(entry -> entry.getKey() * entry.getValue())
                .sum();
            card.deposit(totalDeposit);
            atm.refillCash(cash);
            context.setAmount(totalDeposit);
            card.addTransaction(createTransaction(context));
            System.out.println("Deposit successful. Amount deposited: " + totalDeposit);
        } else {
            System.out.println("No account found for deposit.");
        }
    }

    private Transaction createTransaction(TransactionContext context) {
        return new Transaction("ATM deposit", context.getAmount(), TransactionType.CREDIT, new Date(System.currentTimeMillis()));
    }

}
