package parkinglot.interfaces;

import parkinglot.model.Payment;

public interface PaymentProcessor {
    void process(Payment payment);
}