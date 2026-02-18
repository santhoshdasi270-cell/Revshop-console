package com.revshop.payment;

import java.util.Scanner;

public class UpiPayment implements PaymentMethod {

    @Override
    public boolean pay(double amount) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== UPI Payment ===");
        System.out.print("Enter UPI ID: ");
        String upiId = scanner.nextLine();

        // Simulate payment processing
        System.out.println("Processing UPI payment of ₹" + amount + "...");
        System.out.println("Payment successful via UPI!");

        return true;
    }
}
