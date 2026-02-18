package com.revshop.model;

import java.util.ArrayList;
import java.util.List;

public class Seller extends User {

    private String businessName;
    private List<Product> products;

    public Seller(String id, String name, String email, String password, String businessName) {
        super(id, name, email, password);
        this.businessName = businessName;
        this.products = new ArrayList<>();
    }

    public String getBusinessName() {
        return businessName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }
}
