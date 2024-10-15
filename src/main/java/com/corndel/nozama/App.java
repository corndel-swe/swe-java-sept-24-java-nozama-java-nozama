package com.corndel.nozama;

import com.corndel.nozama.models.Review;
import com.corndel.nozama.repositories.ReviewRepository;
import com.corndel.nozama.repositories.UserRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class App {
  private Javalin app;
  record ReviewRequest(Integer productId, Integer userId, Integer rating, String reviewText) {}

  public static void main(String[] args) {
    var app = new App().javalinApp();
    app.start(8080);
  }

  public App() {
    app = Javalin.create();
    app.get("/",
        ctx -> {
          var users = UserRepository.findAll();
          ctx.json(users);
        });
    app.get(
        "/users/{userId}",
        ctx -> {
          var id = Integer.parseInt(ctx.pathParam("userId"));
          var user = UserRepository.findById(id);
          ctx.status(HttpStatus.IM_A_TEAPOT).json(user);
        });

    // Get all reviews by product
    app.get("/products/{productId}/reviews", ctx -> {
        int productId = Integer.parseInt(ctx.pathParam("productId"));
        var reviews = ReviewRepository.getReviewsByProduct(productId);
        ctx.status(200).json(reviews);
    });

    // Get average review of product
    app.get("/products/{productId}/reviews/average", ctx -> {
        int productId = Integer.parseInt(ctx.pathParam("productId"));
        Double average = ReviewRepository.getAvgRatingByProduct(productId);
        Map<String, Double> response = new HashMap<>();
        response.put("averageRating", average);
        ctx.status(200).json(response);
      });

    // Create a new product review
    app.post("/products/{productId}/reviews", ctx -> {
        ReviewRequest body = ctx.bodyAsClass(ReviewRequest.class);
        Review review = ReviewRepository.createReview(body.productId(), body.userId(), body.rating(), body.reviewText());
        ctx.status(201).json(review);
    });


}

  public Javalin javalinApp() {
    return app;
  }
}
