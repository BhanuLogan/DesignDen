package atm.model;

import java.util.HashMap;
import java.util.Map;

public class CashDispenser {
    private Map<Integer, Integer> denominations; // Key: denomination, Value: count
    private double cashAvailable;

public CashDispenser() {
        this.denominations = new HashMap<>();
        this.cashAvailable = 0;
    }

    public CashDispenser(Map<Integer, Integer> denominations) {
        this.denominations = denominations;
        this.cashAvailable = denominations.entrySet().stream()
                .mapToDouble(entry -> entry.getKey() * entry.getValue())
                .sum();
    }

    public double getCashAvailable() {
        return cashAvailable;
    }

    public void addCash(int denomination, int count) {
        denominations.put(denomination, denominations.getOrDefault(denomination, 0) + count);
        cashAvailable += denomination * count;
    }

    public void addCash(Map<Integer, Integer> cash) {
        for (Map.Entry<Integer, Integer> entry : cash.entrySet()) {
            addCash(entry.getKey(), entry.getValue());
        }
    }

    public void dispenseCash(double amount) {
        if (amount <= 0) {
            System.out.println("Amount must be positive");
            return ;
        }

        Map<Integer, Integer> dispensed = new HashMap<>();
        for (Integer denomination : denominations.keySet()) {
            while (amount >= denomination && denominations.get(denomination) > 0) {
                amount -= denomination;
                dispensed.put(denomination, dispensed.getOrDefault(denomination, 0) + 1);
                denominations.put(denomination, denominations.get(denomination) - 1);
                cashAvailable -= denomination;
            }
        }

        if (amount > 0) {
            addCash(dispensed);
            System.out.println("Insufficient cash available to dispense the requested amount");
            return ;
        }

        // Return the dispensed cash
        System.out.println("Dispensed cash: " + dispensed);
    }
}
