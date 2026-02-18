package com.revshop.service;

import com.revshop.model.*;
import com.revshop.repository.OrderRepository;
import com.revshop.payment.CardPayment;
import com.revshop.payment.PaymentMethod;
import com.revshop.payment.UpiPayment;
import com.revshop.util.NotificationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class OrderService {

    private OrderRepository orderRepository;
    private NotificationService notificationService;

    public OrderService(OrderRepository orderRepository,
                        NotificationService notificationService) {
        this.orderRepository = orderRepository;
        this.notificationService = notificationService;
    }

    // Buyer Checkout
    public void checkout(Buyer buyer) {

        if (buyer.getCart().getItems().isEmpty()) {
            System.out.println("Cart is empty. Cannot checkout.");
            return;
        }

        double total = 0;

        // Validate stock and calculate total
        for (CartItem item : buyer.getCart().getItems()) {
            if (item.getQuantity() > item.getProduct().getStock()) {
                System.out.println("Not enough stock for: " + item.getProduct().getName());
                return;
            }
            total += item.getProduct().getPrice() * item.getQuantity();
        }

        // Choose payment method
        Scanner scanner = new Scanner(System.in);
        PaymentMethod paymentMethod;

        System.out.println("\nChoose Payment Method:");
        System.out.println("1. Card");
        System.out.println("2. UPI");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                paymentMethod = new CardPayment();
                break;
            case 2:
                paymentMethod = new UpiPayment();
                break;
            default:
                System.out.println("Invalid choice! Payment cancelled.");
                return;
        }

        // Process payment
        if (!paymentMethod.pay(total)) {
            System.out.println("Payment failed! Checkout aborted.");
            return;
        }

        // Deduct stock and notify for low stock
        for (CartItem item : buyer.getCart().getItems()) {
            Product product = item.getProduct();
            int newStock = product.getStock() - item.getQuantity();
            product.setStock(newStock);

            if (product.getStock() <= product.getThreshold()) {
                notificationService.notifyUser("Low stock alert for product: " + product.getName());
            }
        }

        // Save order
        String orderId = UUID.randomUUID().toString();
        List<CartItem> orderItems = new ArrayList<>(buyer.getCart().getItems());
        Order order = new Order(orderId, buyer, orderItems, total);
        orderRepository.save(order);

        // Clear buyer cart
        buyer.getCart().getItems().clear();

        notificationService.notifyUser("Order placed successfully!");
        System.out.println("Order ID: " + orderId);
        System.out.println("Total Paid: ₹" + total);
    }

    // View buyer's orders
    public void viewOrders(Buyer buyer) {

        List<Order> orders = orderRepository.findAll();

        for (Order order : orders) {
            if (order.getBuyer().getId().equals(buyer.getId())) {

                System.out.println("================================");
                System.out.println("Order ID: " + order.getId());
                System.out.println("Total: ₹" + order.getTotalAmount());

                for (CartItem item : order.getItems()) {
                    System.out.println(" - " + item.getProduct().getName() + " x " + item.getQuantity());
                }
            }
        }
    }

    // View all orders for a seller
    public void viewSellerOrders(Seller seller) {

        List<Order> orders = orderRepository.findAll();

        for (Order order : orders) {

            for (CartItem item : order.getItems()) {
                if (item.getProduct().getSeller().getId().equals(seller.getId())) {

                    System.out.println("================================");
                    System.out.println("Order ID: " + order.getId());
                    System.out.println("Buyer: " + order.getBuyer().getName());
                    System.out.println("Product: " + item.getProduct().getName());
                    System.out.println("Quantity: " + item.getQuantity());
                }
            }
        }
    }
}
