package parkinglot.impl.payment;

import parkinglot.interfaces.PaymentProcessor;
import parkinglot.model.Payment;

public class CashPaymentProcessor implements PaymentProcessor {
    public void process(Payment payment) {
        System.out.println("Processing cash payment...");
        payment.completePayment();
         System.out.println("Processed cash Payment!!!");
    }
}