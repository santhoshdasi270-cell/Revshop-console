package com.revshop.service;

import com.revshop.model.*;
import com.revshop.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderService {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void checkout(Buyer buyer) {

        if (buyer.getCart().getItems().isEmpty()) {
            System.out.println("Cart is empty. Cannot checkout.");
            return;
        }

        double total = 0;

        // Validate stock
        for (CartItem item : buyer.getCart().getItems()) {

            if (item.getQuantity() > item.getProduct().getStock()) {
                System.out.println("Not enough stock for: " + item.getProduct().getName());
                return;
            }

            total += item.getProduct().getPrice() * item.getQuantity();
        }

        // Deduct stock
        for (CartItem item : buyer.getCart().getItems()) {
            int newStock = item.getProduct().getStock() - item.getQuantity();
            item.getProduct().setStock(newStock);
        }

        // Simulate Payment
        System.out.println("Processing payment...");
        System.out.println("Payment successful!");

        String orderId = UUID.randomUUID().toString();

        List<CartItem> orderItems = new ArrayList<>(buyer.getCart().getItems());

        Order order = new Order(orderId, buyer, orderItems, total);

        orderRepository.save(order);

        // Clear cart
        buyer.getCart().getItems().clear();

        System.out.println("Order placed successfully!");
        System.out.println("Order ID: " + orderId);
        System.out.println("Total Paid: ₹" + total);
    }

    public void viewOrders(Buyer buyer) {

        List<Order> orders = orderRepository.findAll();

        for (Order order : orders) {

            if (order.getBuyer().getId().equals(buyer.getId())) {

                System.out.println("================================");
                System.out.println("Order ID: " + order.getId());
                System.out.println("Total: ₹" + order.getTotalAmount());

                for (CartItem item : order.getItems()) {
                    System.out.println(" - " + item.getProduct().getName()
                            + " x " + item.getQuantity());
                }
            }
        }
    }
}
