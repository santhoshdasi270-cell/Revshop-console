package com.revshop.model;

public class Product {

    private String id;
    private String name;
    private String description;
    private double price;
    private double mrp;
    private int stock;
    private Category category;
    private Seller seller;

    public Product(String id, String name, String description,
                   double price, double mrp, int stock,
                   Category category, Seller seller) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.mrp = mrp;
        this.stock = stock;
        this.category = category;
        this.seller = seller;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getMrp() {
        return mrp;
    }

    public int getStock() {
        return stock;
    }

    public Category getCategory() {
        return category;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
