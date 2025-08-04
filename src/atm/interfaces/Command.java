package atm.interfaces;

import atm.model.TransactionContext;

public interface Command {
    void execute(TransactionContext context);
}