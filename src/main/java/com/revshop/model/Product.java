package com.revshop.model;
import java.util.ArrayList;
import java.util.List;


public class Product {

    private String id;
    private String name;
    private String description;
    private double price;
    private double mrp;
    private int stock;
    private Category category;
    private Seller seller;
    private List<Review> reviews = new ArrayList<>();
    private int threshold = 5;
    private double discountPrice;




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
    public List<Review> getReviews() {
        return reviews;
    }
    public void addReview(Review review) {
        reviews.add(review);
    }
    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
    public int getThreshold(){
        return threshold;
    }
    public double getFinalPrice() {
        return discountPrice > 0 ? discountPrice : price;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }
}
