package vendingmachine.impl;

import vendingmachine.interfaces.PaymentMethod;

public class UPIPayment implements PaymentMethod {

    private double balance;

    public UPIPayment(double initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public void pay(double amount) throws InterruptedException {
        if(amount > balance) {
            throw new IllegalArgumentException("Insufficient balance for UPI payment.");
        }
        System.out.println("Processing UPI payment of amount: " + amount);
        Thread.sleep(500L); // Simulating delay for UPI payment processing
        System.out.println("UPI payment successful for amount: " + amount);

        balance -= amount; // Deduct the amount from the balance
        System.out.println("Remaining balance: " + balance);
    }

    public double getBalance() {
        return balance;
    }

}
