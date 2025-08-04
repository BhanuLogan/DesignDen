package atm.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Bank {
    private String id;
    private String name;
    private List<Atm> atms;
    private List<Card> cards;

    public Bank(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.cards = new ArrayList<>();
        this.atms = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addAtm(Atm atm) {
        atms.add(atm);
    }

    public List<Atm> getAtms() {
        return new ArrayList<>(atms);
    }

    public void addCard(Card account) {
        cards.add(account);
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public String getId() {
        return id;
    }
}