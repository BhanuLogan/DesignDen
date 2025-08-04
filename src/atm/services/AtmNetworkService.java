package atm.services;

import atm.model.ATMNetwork;
import atm.model.Bank;

public class AtmNetworkService {
    private ATMNetwork network;

    private AtmNetworkService() {
        this.network = new ATMNetwork();
    }

    // singleton
    public static class InnerAtmNetworkService {
        private static final AtmNetworkService instance = new AtmNetworkService();

        public static AtmNetworkService getInstance() {
            return instance;
        }
    }
    
    public void addBank(Bank bank) {
        network.addBank(bank);
    }

    public Bank getBank(String name)  {
        for(Bank bank : network.getBanks()) {
            if(name.equalsIgnoreCase(bank.getName())) {
                return bank;
            }
        }
        System.out.println("Bank not found - " + name);
        return null;
    }

    public ATMNetwork getNetwork() {
        return network;
    }
}
