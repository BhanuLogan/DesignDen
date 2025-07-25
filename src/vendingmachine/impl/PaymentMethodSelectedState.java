package vendingmachine.impl;

import vendingmachine.interfaces.PaymentMethod;
import vendingmachine.interfaces.State;
import vendingmachine.model.Slot;
import vendingmachine.model.VendingMachine;

public class PaymentMethodSelectedState implements State {

    @Override
    public void addPaymentMethod(VendingMachine vendingMachine, PaymentMethod paymentMethod) {
        System.out.println("Payment method changed to " + paymentMethod.getClass().getSimpleName());
    }

    @Override
    public boolean selectItem(VendingMachine vendingMachine, int index) {
        Slot[] inventory = vendingMachine.getInventory();
        if (index >= 0 && index < inventory.length && inventory[index] != null && inventory[index].isAvailable()) {
            vendingMachine.setSelectedSlotIndex(index);
            System.out.println("Slot " + index + " selected.");
            vendingMachine.setState(new DispenseState());
            return true; // selection successful
        } else {
            System.out.println("Invalid slot selection or item not available.");
            return false; // selection failed
        }
    }

    @Override
    public void dispenseItem(VendingMachine vendingMachine) {
        System.out.println("Please select an item.");
    }

}
