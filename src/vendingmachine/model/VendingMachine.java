package vendingmachine.model;

import vendingmachine.impl.IdleState;
import vendingmachine.interfaces.PaymentMethod;
import vendingmachine.interfaces.State;

public class VendingMachine {

    private PaymentMethod paymentMethod;
    private Slot[] inventory;
    private int selectedSlotIndex = -1;
    private State currentState;

    public VendingMachine() {
        this.inventory = new Slot[10]; // Initialize with no slots
        this.currentState = new IdleState(); // Initial state
    }
    
    public void updateInventory(Slot[] inventory) {
        this.inventory = inventory;
    }

    public Slot[] getInventory() {
        return inventory;
    }

    public void restockSlot(int index, int quantity) {
        if (index >= 0 && index < inventory.length) {
            Slot slot = inventory[index];
            slot.setQuantity(slot.getQuantity() + quantity);
        }
    }

    public void dispenseItem() {
        if(paymentMethod == null) {
            System.out.println("No payment method selected.");
            return ;
        }

        currentState.dispenseItem(this);
    }

    public void addPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        currentState.addPaymentMethod(this, paymentMethod);
    }

    public boolean selectSlot(int index) {
        return currentState.selectItem(this, index);
    }

    public Slot getSelectedSlot() {
        if (selectedSlotIndex >= 0 && selectedSlotIndex < inventory.length) {
            return inventory[selectedSlotIndex];
        }
        return null; // no slot selected
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public int getSelectedSlotIndex() {
        return selectedSlotIndex;
    }

    public void setState(State state) {
        this.currentState = state;
    }

    public void reset() {
        this.paymentMethod = null;
        this.selectedSlotIndex = -1;
        this.currentState = new IdleState(); // Reset to initial state
        System.out.println("\nVending machine reset to idle state.");
    }

    public void setSelectedSlotIndex(int index) {
        this.selectedSlotIndex = index;
    }
}
