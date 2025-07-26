package parkinglot.impl.payment;

import parkinglot.interfaces.PaymentProcessor;
import parkinglot.model.Payment;

public class UpiPaymentProcessor implements PaymentProcessor {
    public void process(Payment payment) {
        System.out.println("Processing UPI payment...");
        try {
            Thread.sleep(2000l);
        } catch (Throwable e) {
            e.printStackTrace();
        } // Simulating UPI processing delay
        payment.completePayment();
        System.out.println("Processed UPI Payment!!!");
    }
}
