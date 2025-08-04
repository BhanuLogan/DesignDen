package atm.model;

import java.util.ArrayList;
import java.util.List;

public class Card {
    private String accountNumber;
    private double balance;
    private String cardHolderName;
    private List<Transaction> transactions;
    private String pin;

    public Card(String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.cardHolderName = accountHolderName;
        this.transactions = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Invalid withdrawal amount");
        }
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactionHistory() {
        return new ArrayList<>(transactions);
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public boolean validatePin(String pin) {
        return this.pin != null && this.pin.equals(pin);
    }
}
