package vendingmachine.impl;

import vendingmachine.interfaces.PaymentMethod;

public class CashPayment implements PaymentMethod {
    private double balance;

    public CashPayment(double initialBalance) {
        this.balance = initialBalance;
    }
    
    @Override
    public void pay(double amount) {
        if(amount > balance) {
            throw new IllegalArgumentException("Insufficient cash balance for payment.");
        }
        System.out.println("Payment successful. Amount: " + amount);
        balance -= amount; // Deduct the amount from the balance
        System.out.println("Remaining balance: " + balance);
    }

    public double getBalance() {
        return balance;
    }

}
