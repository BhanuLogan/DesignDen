package atm.factory;

import atm.impl.action.BalanceInquiryCommand;
import atm.impl.action.DepositCommand;
import atm.impl.action.MiniStatementCommand;
import atm.impl.action.WithdrawCommand;
import atm.interfaces.Command;
import atm.util.AtmConstants.ActionType;

public class ATMCommandFactory {
    public static Command getCommand(ActionType actionType) {
        return switch (actionType) {
            case DEPOSIT -> new DepositCommand();
            case WITHDRAW -> new WithdrawCommand();
            case BALANCE_INQUIRY -> new BalanceInquiryCommand();
            case MINI_STATEMENT -> new MiniStatementCommand();
            default -> throw new IllegalArgumentException("Invalid action");
        };
    }
}