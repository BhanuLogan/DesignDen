package vendingmachine.interfaces;

public interface PaymentMethod {
    void pay(double amount) throws InterruptedException;
    double getBalance();
}