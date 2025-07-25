package vendingmachine;

import interfaces.LLDService;
import vendingmachine.impl.CashPayment;
import vendingmachine.model.Item;
import vendingmachine.model.Slot;
import vendingmachine.model.VendingMachine;

public class VendingMachineLLDService implements LLDService {

    @Override
    public void runExamples() throws Exception {
        runUseCase1();
    }

    /*
        Use case 1: Add items to the vending machine and check availability
    */
    private void runUseCase1() {
        System.out.println("----------------- VENDING MACHINE -----------------");
        System.out.println("------------ USE CASE 1 - ADD ITEMS ---------------");
        // Logic to add items to the vending machine
        // Example: Adding chips, soda, and candy

        VendingMachine vendingMachine = new VendingMachine();
        System.out.println("Dispensing items from vending machine...");
        vendingMachine.dispenseItem(); // Attempt to dispense an item before adding any

        System.out.println("\nAdding payment methods...");
        vendingMachine.addPaymentMethod(new CashPayment(100)); // No payment method selected
        System.out.println("Payment method added successfully.");

        System.out.println("\nDispensing items from vending machine...");
        vendingMachine.dispenseItem(); // Attempt to dispense without selecting an item

        System.out.println("\nSelecting an item from slot 1 ...");
        vendingMachine.selectSlot(1);

        Slot[] slots = new Slot[3];
        slots[0] = new Slot(new Item("Chips", 1.50), 2);
        slots[1] = new Slot(new Item("Soda", 1.00), 10);
        slots[2] = new Slot(new Item("Candy", 0.75), 20);

        System.out.println("\nAdding items to vending machine...");
        vendingMachine.updateInventory(slots);
        System.out.println("Items added successfully.");

        System.out.println("\nSelecting an item from slot 1 ...");
        vendingMachine.selectSlot(1);

        System.out.println("\nDispensing items from vending machine...");
        vendingMachine.dispenseItem();

        System.out.println("---------------- EXIT ------------------------------\n");
    }

}
