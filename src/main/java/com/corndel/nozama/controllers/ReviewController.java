package com.corndel.nozama.controllers;
import com.corndel.nozama.models.Review;
import com.corndel.nozama.repositories.ReviewRepository;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.sql.SQLException;
import java.util.Map;


public class ReviewController {
    public static void getReviewsByProduct(Context ctx)  {
        try {
            int id = Integer.parseInt(ctx.pathParam("productId"));
            var reviews = ReviewRepository.getReviewsByProduct(id);
            ctx.status(200).json(reviews);
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

    public static void getAverageRating(Context ctx) {

        try {
            int id = Integer.parseInt(ctx.pathParam("productId"));
            var averageRating = ReviewRepository.getAverageRating(id);
            ctx.status(200).json(Map.of("averageRating", averageRating));
        } catch (Exception e) {
            ctx.status(404);
            throw new BadRequestResponse("Product Not Found.");
        }
    }


}
