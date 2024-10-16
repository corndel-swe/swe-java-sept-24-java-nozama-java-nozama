package com.corndel.nozama.controllers;
import com.corndel.nozama.models.Review;
import com.corndel.nozama.repositories.ReviewRepository;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.sql.SQLException;


public class ReviewController {
    public static void getReviewsByProduct(Context ctx)  {
        int id = Integer.parseInt(ctx.pathParam("productId"));
        try {
            var reviews = ReviewRepository.getReviewsByProduct(id);
            ctx.json(reviews);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void postReview(Context ctx) {
        Review newReview = ctx.bodyAsClass(Review.class);
        try {
            Review createdReview = ReviewRepository.postReview(newReview);
            ctx.status(201).json(createdReview);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
