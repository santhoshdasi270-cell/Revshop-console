package com.revshop.payment;

import java.util.Scanner;

public class CardPayment implements PaymentMethod {

    @Override
    public boolean pay(double amount) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Card Payment ===");
        System.out.print("Enter Card Number: ");
        String cardNumber = scanner.nextLine();

        System.out.print("Enter Card Holder Name: ");
        String cardHolder = scanner.nextLine();

        System.out.print("Enter CVV: ");
        String cvv = scanner.nextLine();

        System.out.print("Enter Expiry Date (MM/YY): ");
        String expiry = scanner.nextLine();

        // Simulate payment processing
        System.out.println("Processing card payment of ₹" + amount + "...");
        System.out.println("Payment successful via Card!");

        return true;
    }
}
