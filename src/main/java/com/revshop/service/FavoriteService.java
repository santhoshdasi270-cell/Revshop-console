package com.revshop.service;

import com.revshop.model.Buyer;
import com.revshop.model.Product;

import java.util.List;

public class FavoriteService {

    // Add product to buyer's favorites
    public void addToFavorites(Buyer buyer, Product product) {
        List<Product> favorites = buyer.getFavorites();

        if (favorites.contains(product)) {
            System.out.println("Product is already in favorites.");
            return;
        }

        favorites.add(product);
        System.out.println("Product added to favorites.");
    }

    // View all favorite products
    public void viewFavorites(Buyer buyer) {
        List<Product> favorites = buyer.getFavorites();

        if (favorites.isEmpty()) {
            System.out.println("No favorite products yet.");
            return;
        }

        System.out.println("===== Favorite Products =====");
        for (Product product : favorites) {
            System.out.println("Name: " + product.getName() +
                    " | Price: ₹" + product.getPrice());
        }
    }
}
