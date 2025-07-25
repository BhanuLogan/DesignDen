package vendingmachine.impl;

import vendingmachine.interfaces.PaymentMethod;
import vendingmachine.interfaces.State;
import vendingmachine.model.Slot;
import vendingmachine.model.VendingMachine;

public class DispenseState implements State {

    @Override
    public void addPaymentMethod(VendingMachine vendingMachine, PaymentMethod paymentMethod) {
        System.out.println("Cannot add payment method in dispense state.");
    }

    @Override
    public boolean selectItem(VendingMachine vendingMachine, int slotIndex) {
        System.out.println("Cannot select item in dispense state.");
        return false;
    }

    @Override
    public void dispenseItem(VendingMachine vendingMachine) {
        Slot selectedSlot = vendingMachine.getSelectedSlot();
        if (selectedSlot == null) {
            System.out.println("No item selected for dispensing.");
            return;
        }
        
        if (!selectedSlot.isAvailable()) {
            System.out.println("Selected item is not available for dispensing.");
            return;
        }

        PaymentMethod paymentMethod = vendingMachine.getPaymentMethod();
        if (paymentMethod == null) {
            System.out.println("No payment method selected. Cannot dispense item.");
            return;
        }
        
        double price = selectedSlot.getItem().getPrice();
        if(price > paymentMethod.getBalance()) {
            System.out.println("Insufficient balance to dispense item.");
            return;
        }
        
        try {
            paymentMethod.pay(price);
            selectedSlot.reduceQuantity();
            System.out.println("Successfully Dispensed item from slot " + vendingMachine.getSelectedSlotIndex());
            System.out.println("Please collect your item: " + selectedSlot.getItem().getName());
            System.out.println("Thank you for your purchase!");
            vendingMachine.reset();
        } catch (InterruptedException e) {
            System.out.println("Payment interrupted. Please try again.");
        }
    }

}
