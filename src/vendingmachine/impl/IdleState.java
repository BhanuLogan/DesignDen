package vendingmachine.impl;

import vendingmachine.interfaces.PaymentMethod;
import vendingmachine.interfaces.State;
import vendingmachine.model.VendingMachine;

public class IdleState implements State {

    @Override
    public void addPaymentMethod(VendingMachine vendingMachine, PaymentMethod paymentMethod) {
        vendingMachine.setState(new PaymentMethodSelectedState());
        System.out.println("Payment method added: " + paymentMethod.getClass().getSimpleName());
    }

    @Override
    public boolean selectItem(VendingMachine vendingMachine, int slotIndex) {
        System.out.println("Please add payment method before selecting an item.");
        return false;
    }

    @Override
    public void dispenseItem(VendingMachine vendingMachine) {
        System.out.println("Please add payment method and select an item before purchasing.");
    }

}
