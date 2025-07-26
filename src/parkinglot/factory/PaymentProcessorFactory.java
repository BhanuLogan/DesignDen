package parkinglot.factory;

import parkinglot.impl.payment.CashPaymentProcessor;
import parkinglot.impl.payment.CreditCardPaymentProcessor;
import parkinglot.impl.payment.DebitCardPaymentProcessor;
import parkinglot.impl.payment.UpiPaymentProcessor;
import parkinglot.interfaces.PaymentProcessor;
import parkinglot.util.ParkingLotConstants.PaymentMethod;

public class PaymentProcessorFactory {
    public static PaymentProcessor getProcessor(PaymentMethod method) {
        return switch (method) {
            case CASH -> new CashPaymentProcessor();
            case UPI -> new UpiPaymentProcessor();
            case CREDIT_CARD -> new CreditCardPaymentProcessor();
            case DEBIT_CARD -> new DebitCardPaymentProcessor();
            default -> throw new IllegalArgumentException("Invalid payment method");
        };
    }
}