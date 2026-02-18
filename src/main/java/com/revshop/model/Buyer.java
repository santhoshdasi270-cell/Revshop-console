package com.revshop.model;

import java.util.ArrayList;
import java.util.List;

public class Buyer extends User {

    private List<Product> favorites;
    private Cart cart;


    public Buyer(String id, String name, String email, String password) {
        super(id, name, email, password);
        this.favorites = new ArrayList<>();
        this.cart = new Cart();

    }

    public List<Product> getFavorites() {
        return favorites;
    }

    public void addToFavorites(Product product) {
        favorites.add(product);
    }
    public Cart getCart() {
        return cart;
    }

}
