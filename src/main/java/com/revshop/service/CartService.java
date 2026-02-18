package com.revshop.service;

import com.revshop.model.*;

public class CartService {

    public void addToCart(Buyer buyer, Product product, int quantity) {

        for (CartItem item : buyer.getCart().getItems()) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.increaseQuantity(quantity);
                System.out.println("Product quantity updated in cart.");
                return;
            }
        }

        CartItem cartItem = new CartItem(product, quantity);
        buyer.getCart().getItems().add(cartItem);

        System.out.println("Product added to cart.");
    }

    public void viewCart(Buyer buyer) {

        if (buyer.getCart().getItems().isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        double total = 0;

        for (CartItem item : buyer.getCart().getItems()) {

            double itemTotal = item.getProduct().getPrice() * item.getQuantity();
            total += itemTotal;

            System.out.println("--------------------------------");
            System.out.println("Product: " + item.getProduct().getName());
            System.out.println("Quantity: " + item.getQuantity());
            System.out.println("Price: ₹" + item.getProduct().getPrice());
            System.out.println("Total: ₹" + itemTotal);
        }

        System.out.println("--------------------------------");
        System.out.println("Cart Total: ₹" + total);
    }

    public void removeFromCart(Buyer buyer, String productId) {

        buyer.getCart().getItems()
                .removeIf(item -> item.getProduct().getId().equals(productId));

        System.out.println("Product removed from cart.");
    }
}
