package atm.model;

import java.util.ArrayList;
import java.util.List;

public class ATMNetwork {
    private final List<Bank> banks;

    public ATMNetwork() {
        this.banks = new ArrayList<>();
    }

    public void addBank(Bank bank) {
        banks.add(bank);
    }

    public List<Bank> getBanks() {
        return banks;
    }
}
