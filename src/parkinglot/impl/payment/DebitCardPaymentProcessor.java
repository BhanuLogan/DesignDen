package parkinglot.impl.payment;

import parkinglot.interfaces.PaymentProcessor;
import parkinglot.model.Payment;

public class DebitCardPaymentProcessor implements PaymentProcessor {
    public void process(Payment payment) {
        System.out.println("Processing debit card payment...");
        payment.completePayment();
        System.out.println("Processed debit card Payment!!!");
    }
}