package vendingmachine.interfaces;

import vendingmachine.model.VendingMachine;

public interface State {
    void addPaymentMethod(VendingMachine vendingMachine, PaymentMethod paymentMethod);
    boolean selectItem(VendingMachine vendingMachine, int slotIndex);
    void dispenseItem(VendingMachine vendingMachine);
}
