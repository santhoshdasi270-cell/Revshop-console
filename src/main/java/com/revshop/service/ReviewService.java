package com.revshop.service;

import com.revshop.model.Buyer;
import com.revshop.model.Product;
import com.revshop.model.Review;

public class ReviewService {

    public static void addReview(Buyer buyer, Product product, int rating, String comment) {

        if (rating < 1 || rating > 5) {
            System.out.println("Rating must be between 1 and 5.");
            return;
        }

        Review review = new Review(buyer, rating, comment);
        product.addReview(review);

        System.out.println("Review added successfully!");
    }

    public void viewReviews(Product product) {

        if (product.getReviews().isEmpty()) {
            System.out.println("No reviews yet.");
            return;
        }

        for (Review review : product.getReviews()) {
            System.out.println("-------------------------");
            System.out.println("Buyer: " + review.getBuyer().getName());
            System.out.println("Rating: " + review.getRating());
            System.out.println("Comment: " + review.getComment());
        }
    }
}
