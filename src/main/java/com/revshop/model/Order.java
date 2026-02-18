package com.revshop.model;

import java.util.List;

public class Order {

    private String id;
    private Buyer buyer;
    private List<CartItem> items;
    private double totalAmount;

    public Order(String id, Buyer buyer, List<CartItem> items, double totalAmount) {
        this.id = id;
        this.buyer = buyer;
        this.items = items;
        this.totalAmount = totalAmount;
    }

    public String getId() {
        return id;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
