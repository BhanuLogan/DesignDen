package vendingmachine.impl;

import vendingmachine.interfaces.PaymentMethod;

public class CardPayment implements PaymentMethod {

    private double balance;

    public CardPayment(double initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public void pay(double amount) throws InterruptedException {
        if(amount > balance) {
            throw new IllegalArgumentException("Insufficient balance for card payment.");
        }
        
        System.out.println("Processing card payment of amount: " + amount);
        Thread.sleep(1000L); // Simulating delay for card payment processing
        System.out.println("Card payment successful for amount: " + amount);

        balance -= amount; // Deduct the amount from the balance
        System.out.println("Remaining balance: " + balance);
    }

    public double getBalance() {
        return balance;
    }

}
