package atm.services;

import atm.model.Atm;
import atm.model.Bank;
import atm.model.Card;

public class BankService {
    private AtmNetworkService atmNetworkService;

    public BankService() {
        this.atmNetworkService = AtmNetworkService.InnerAtmNetworkService.getInstance();
    }

    public void addBank(String name) {
        Bank bank = new Bank(name);
        atmNetworkService.addBank(bank);
    }

    public Bank getBank(String name) {
        return atmNetworkService.getBank(name);
    }

    public void addAtm(String bankName, Atm atm) {
        Bank bank = getBank(bankName);
        if(bank != null) {
            bank.addAtm(atm);
        }
    }

    public void addCard(String bankName, Card card) {
        Bank bank = getBank(bankName);
        if(bank != null) {
            bank.addCard(card);
        }
    }
}
