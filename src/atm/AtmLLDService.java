package atm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import atm.model.ATMNetwork;
import atm.model.Card;
import atm.model.Atm;
import atm.model.Bank;
import atm.model.TransactionContext;
import atm.services.AtmNetworkService;
import atm.services.AtmService;
import atm.services.BankService;
import atm.util.AtmConstants.ActionType;
import interfaces.LLDService;

public class AtmLLDService implements LLDService {

    private BankService bankService;
    private AtmNetworkService atmNetworkService;
    private AtmService atmService;
    private Scanner scanner;

    public AtmLLDService() {
        this.bankService = new BankService();
        this.atmNetworkService = AtmNetworkService.InnerAtmNetworkService.getInstance();
        this.atmService = new AtmService();
        this.scanner = new Scanner(System.in);

        createATMNetwork();
    }

    @Override
    public void runExamples() throws Exception {
        
        Card c1 = new Card("123456", "1234", 1000);
        Card c2 = new Card("654321", "4321", 5000);

        System.out.println("\n\n---------------------------------------------");
        System.out.println("                 ATM                         ");
        System.out.println("---------------------------------------------");
        ATMNetwork network = atmNetworkService.getNetwork();
        // CLI interaction
        System.out.println("Banks:");
        for (int i = 0; i < network.getBanks().size(); i++) {
            System.out.println((i + 1) + ". " + network.getBanks().get(i).getName());
        }
        System.out.print("Select Bank: ");
        int bankIndex = scanner.nextInt() - 1;
        Bank selectedBank = network.getBanks().get(bankIndex);

        System.out.println("\nATMs:");
        List<Atm> atmList = selectedBank.getAtms();
        for (int i = 0; i < atmList.size(); i++) {
            System.out.println((i + 1) + ". " + atmList.get(i).getId());
        }
        System.out.print("Select ATM: ");
        int atmIndex = scanner.nextInt() - 1;
        Atm atm = atmList.get(atmIndex);

        simulate(atm, c1);
        simulate(atm, c2);
        scanner.close();

        System.out.println("---------------------------------------------\n");
    }

    private void simulate(Atm atm, Card card) {
        while (true) {
            showATMMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    atmService.insertCard(atm, card);
                    break;
                }
                case 2 -> {
                    System.out.print("Enter PIN: ");
                    String pin = scanner.nextLine();
                    atmService.enterPin(atm, pin);
                }
                case 3 -> {
                    System.out.print("Amount to withdraw: ");
                    double amt = scanner.nextDouble();
                    TransactionContext context = new TransactionContext(ActionType.WITHDRAW);
                    context.setAtm(atm);
                    context.setAmount(amt);

                    atmService.executeTransaction(context);
                }
                case 4 -> {
                    Map<Integer, Integer> cash = getCash(scanner);
                    TransactionContext context = new TransactionContext(ActionType.DEPOSIT);
                    context.setAtm(atm);
                    context.setCash(cash);
                    atmService.executeTransaction(context);
                }
                case 5 -> {
                    TransactionContext context = new TransactionContext(ActionType.BALANCE_INQUIRY);
                    context.setAtm(atm);
                    atmService.executeTransaction(context);
                }
                case 6 -> {
                    TransactionContext context = new TransactionContext(ActionType.MINI_STATEMENT);
                    context.setAtm(atm);
                    atmService.executeTransaction(context);
                }
                case 7 -> {
                    atmService.ejectCard(atm);
                }
                case 8 -> {
                    System.out.print("Enter PIN: ");
                    String pin = scanner.nextLine();
                    card.setPin(pin);
                }
                case 9 -> {
                    System.out.println("Exiting...");
                    return ;
                }
                default -> System.out.println("Invalid option");
            }
        }
    }

    private Map<Integer, Integer> getCash(Scanner scanner) {
        Map<Integer, Integer> cash = new HashMap<>();
        while(true) {
            System.out.print("Denomination (10, 20, 50, 100 etc, enter 0 to exit): ");
            int note = scanner.nextInt();
            if(note == 0) break;
            System.out.print("Number of notes: ");
            int cnt = scanner.nextInt();
            cash.put(note, cnt);
        }
        return cash;
    }

    private void showATMMenu() {
        System.out.println("\n1. Insert Card\n2. Enter PIN\n3. Withdraw\n4. Deposit\n5. Balance Inquiry\n6. Mini Statement\n7. Eject Card\n8. Set PIN\n9. Exit");
        System.out.print("Select option: ");
    }

    private void createATMNetwork() {
        bankService.addBank("ICICI");
        bankService.addBank("AU");
        bankService.addBank("SBI");

        bankService.addAtm("ICICI", new Atm("ICICI-1", "Koramangala"));
        bankService.addAtm("ICICI", new Atm("ICICI-2", "HSR"));

        bankService.addAtm("AU", new Atm("AU-1", "Whitefield"));

        bankService.addAtm("SBI", new Atm("SBI-1", "Koramangala"));
        bankService.addAtm("SBI", new Atm("SBI-1", "MG Road"));
    }

}
