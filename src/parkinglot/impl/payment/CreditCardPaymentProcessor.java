package parkinglot.impl.payment;

import parkinglot.interfaces.PaymentProcessor;
import parkinglot.model.Payment;

public class CreditCardPaymentProcessor implements PaymentProcessor {
    public void process(Payment payment) {
        System.out.println("Processing credit card payment...");
        payment.completePayment();
        System.out.println("Processed credit card Payment!!!");
    }
}
