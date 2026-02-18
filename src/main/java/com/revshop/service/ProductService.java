package com.revshop.service;

import com.revshop.model.Category;
import com.revshop.model.Product;
import com.revshop.model.Seller;
import com.revshop.repository.ProductRepository;

import java.util.List;
import java.util.UUID;

public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public ProductRepository getProductRepository() {
        return productRepository;
    }


    // Add product
    public void addProduct(String name,
                           String description,
                           double price,
                           double mrp,
                           int stock,
                           String categoryName,
                           Seller seller) {

        String productId = UUID.randomUUID().toString();
        String categoryId = UUID.randomUUID().toString();

        Category category = new Category(categoryId, categoryName);

        Product product = new Product(
                productId,
                name,
                description,
                price,
                mrp,
                stock,
                category,
                seller
        );

        productRepository.save(product);

        seller.addProduct(product);

        System.out.println("Product added successfully!");
    }

    // View all products
    public void viewAllProducts() {

        List<Product> products = productRepository.findAll();

        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }
        if (product.getStock() <= 5) {
            System.out.println("⚠ LOW STOCK ALERT!");
        }


        for (Product product : products) {
            System.out.println("--------------------------------");
            System.out.println("Product ID: " + product.getId());
            System.out.println("Name: " + product.getName());
            System.out.println("Price: ₹" + product.getPrice());
            System.out.println("MRP: ₹" + product.getMrp());
            System.out.println("Stock: " + product.getStock());
            System.out.println("Category: " + product.getCategory().getName());
            System.out.println("Seller: " + product.getSeller().getName());
        }
    }
}
