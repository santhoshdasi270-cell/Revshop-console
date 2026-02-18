package com.revshop.payment;

public interface PaymentMethod {
    boolean pay(double amount);  // returns true if payment successful
}
