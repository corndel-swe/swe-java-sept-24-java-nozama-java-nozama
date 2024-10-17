package com.corndel.nozama;

import com.corndel.nozama.controllers.ReviewController;
import com.corndel.nozama.repositories.UserRepository;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;
import static io.javalin.apibuilder.ApiBuilder.*;

public class App {
  private Javalin app;

  public static void main(String[] args) {
    var app = new App().javalinApp();
    app.start(8080);
  }

  public App() {
    app = Javalin.create(config -> {
        config.router.apiBuilder(() -> {
            path("/products", () -> {
                get("/{productId}/reviews", ReviewController::getReviewByProduct);
                get("/{productId}/reviews/average", ReviewController::getAvgRatingByProduct);
                post("/{productId}/reviews", ReviewController:: createReview);
            });
        });
    });

    app.exception(Exception.class, (e, ctx) -> {
        ctx.status(500);
        ctx.result("An unknown error occurred.");
    });

    app.get(
        "/",
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

  }

  public Javalin javalinApp() {
    return app;
  }
}
