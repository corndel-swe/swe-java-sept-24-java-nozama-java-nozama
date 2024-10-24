package com.corndel.nozama;

import com.corndel.nozama.controllers.UserController;
import com.corndel.nozama.controllers.ProductController;
import com.corndel.nozama.controllers.ReviewController;
import io.javalin.Javalin;

import java.util.Arrays;
import java.util.List;

public class App {

    private Javalin app;

    public static void main(String[] args) {
        var app = new App().javalinApp();
        app.start(8080);
    }

    public App() {
        app = Javalin.create();

        app.get("/", ctx -> {
            List<String> endpoints = Arrays.asList(
                    "GET /users",
                    "GET /users/{userId}",
                    "POST /users",
                    "DELETE /users/{userId}",
                    "POST /users/login",
                    "GET /products",
                    "GET /products/{id}",
                    "POST /products",
                    "PUT /products/{id}",
                    "PATCH /products/{id}",
                    "DELETE /products/{id}",
                    "GET /products/category/{categoryId}",
                    "GET /products/{productId}/reviews/average",
                    "GET /products/{productId}/reviews",
                    "POST /products/{productId}/reviews"
            );
            ctx.result("Available Endpoints:\n" + String.join("\n", endpoints));
        });

        app.get("/users", UserController::getAllUsers);
        app.get("/users/{userId}", UserController::getUserById);
        app.post("/users", UserController::createUser);
        app.delete("/users/{userId}", UserController::deleteUser);
        app.post("/users/login", UserController::userLogIn);

        app.get("/products", ProductController::getAllProducts);
        app.get("/products/{id}", ProductController::getProductById);
        app.post("/products", ProductController::createProduct);
        app.put("/products/{id}", ProductController::updateProduct);
        app.patch("/products/{id}", ProductController::patchProduct);
        app.delete("/products/{id}", ProductController::deleteProduct);
        app.get("/products/category/{categoryId}", ProductController::getProductsByCategory);

        app.get("/products/{productId}/reviews/average", ReviewController::getAverageRating);
        app.get("/products/{productId}/reviews", ReviewController::getReviewsByProduct);
        app.post("/products/{productId}/reviews", ReviewController::postReview);
    }

    public Javalin javalinApp() {
        return app;
    }
}
