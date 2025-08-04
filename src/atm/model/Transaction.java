package atm.model;

import java.sql.Date;
import java.util.UUID;

import atm.util.AtmConstants;
import atm.util.AtmConstants.TransactionType;

public class Transaction {
    private String id;
    private String description;
    private double amount;
    private AtmConstants.TransactionType type;
    private Date date;

    public Transaction(String description, double amount, TransactionType type, Date date) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.type = type;
    }

    public String getId() {
        return id;
    }
    
    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public TransactionType getType() {
        return type;
    }
}
