package vendingmachine.model;

public class Slot {
    private Item item; // assume that a slot can hold only one type of item
    private int quantity;

    public Slot(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void reduceQuantity() {
        if (quantity > 0) {
            quantity--;
        } else {
            System.out.println("Cannot reduce quantity. Slot is empty.");
        }
    }
    
    public boolean isAvailable() {
        return quantity > 0;
    }
}
