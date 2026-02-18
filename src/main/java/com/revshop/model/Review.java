package com.revshop.model;

public class Review {

    private Buyer buyer;
    private int rating; // 1 to 5
    private String comment;

    public Review(Buyer buyer, int rating, String comment) {
        this.buyer = buyer;
        this.rating = rating;
        this.comment = comment;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }
}
