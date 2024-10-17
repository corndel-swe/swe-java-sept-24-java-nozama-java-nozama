package com.corndel.nozama;

import com.corndel.nozama.controllers.ProductController;
import com.corndel.nozama.models.Product;
import com.corndel.nozama.models.ProductRequest;
import com.corndel.nozama.repositories.ProductRepository;
import com.corndel.nozama.repositories.UserRepository;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;

public class App {
  private Javalin app;

  public static void main(String[] args) {
    var app = new App().javalinApp();
    app.start(8080);
  }

  public App() {
    app = Javalin.create();
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

    app.get("/products", ProductController::getAllProducts);
    app.get("/products/{productId}", ProductController::getProductById);
    app.get("/products/category/{category}",
            ProductController::getProductsByCategory);
    // can refactor to use Product rather than ProductRequest
    app.post("/products/", ProductController::addNewProduct);
  }

  public Javalin javalinApp() {
    return app;
  }
}
