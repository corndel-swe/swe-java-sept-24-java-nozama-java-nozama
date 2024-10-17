package com.corndel.nozama.controllers;

import com.corndel.nozama.models.Review;
import com.corndel.nozama.repositories.ReviewRepository;
import io.javalin.http.Context;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class ReviewController {
    record ReviewRequest(Integer productId, Integer userId, Integer rating, String reviewText) {}
    public static void getReviewByProduct(Context ctx) throws SQLException {
        int productId = Integer.parseInt(ctx.pathParam("productId"));
        var reviews = ReviewRepository.getReviewsByProduct(productId);
        ctx.status(200).json(reviews);

    }

    public static void getAvgRatingByProduct(Context ctx) throws SQLException {
        int productId = Integer.parseInt(ctx.pathParam("productId"));
        Double average = ReviewRepository.getAvgRatingByProduct(productId);
        Map<String, Double> response = new HashMap<>();
        response.put("averageRating", average);
        ctx.status(200).json(response);
    }

    public static void createReview(Context ctx) throws SQLException {
        ReviewRequest body = ctx.bodyAsClass(ReviewRequest.class);
        Review review = ReviewRepository.createReview(body.productId(), body.userId(), body.rating(), body.reviewText());
        ctx.status(201).json(review);

    }

}
