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
    // Find products by category
    public List<Product> findByCategory(String categoryName) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory().getName().equalsIgnoreCase(categoryName)) {
                result.add(product);
            }
        }
        return result;
    }

    // Search products by keyword (in name or description)
    public List<Product> searchByKeyword(String keyword) {
        List<Product> result = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(lowerKeyword) ||
                    product.getDescription().toLowerCase().contains(lowerKeyword)) {
                result.add(product);
            }
        }
        return result;
    }

}
