package com.corndel.nozama;

import com.corndel.nozama.controllers.ReviewController;
import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.*;

public class App {
  private Javalin app;

  public static void main(String[] args) {
    var app = javalinApp();
    app.start(8080);
  }

  public static Javalin javalinApp() {

      return Javalin.create(config -> {

          config.router.apiBuilder(() -> {
              path("/products", () -> {
                  get("/{productId}/reviews", ReviewController::getReviewByProduct);
                  get("/{productId}/reviews/average", ReviewController::getAvgRatingByProduct);
                  post("/{productId}/reviews", ReviewController:: createReview);
              });
          });

      });
    }
}
