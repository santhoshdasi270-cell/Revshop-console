package com.revshop.repository;

import com.revshop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private List<Product> products = new ArrayList<>();

    // Save product
    public void save(Product product) {
        products.add(product);
    }

    // Get all products
    public List<Product> findAll() {
        return products;
    }

    // Find product by ID
    public Product findById(String id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    // Delete product
    public void delete(String id) {
        products.removeIf(product -> product.getId().equals(id));
    }
}
