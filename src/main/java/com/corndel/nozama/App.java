package com.corndel.nozama;

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

    app.get(
            "/products/",
            ctx -> {
                var products = ProductRepository.findAll();
                ctx.json(products).status(200);
            });
    app.get(
            "/products/{productId}",
            ctx -> {
                var id = Integer.parseInt(ctx.pathParam("productId"));
                var product = ProductRepository.findById(id);
                if(product!=null) {
                    ctx.json(product).status(200);
                }else{
                    ctx.status(400);
                }
            }
    );
    app.get(
            "/products/category/{category}",
            ctx -> {
                var category = ctx.pathParam("category");
                var products = ProductRepository.findByCategory(category);
                if(products!=null) {
                    ctx.json(products).status(200);
                }else{
                    ctx.status(400);
                }
            }
    );

    app.post(
            "/products/",
            ctx -> {
                ProductRequest body = ctx.bodyAsClass(ProductRequest.class);
                // dummy id -1, will be auto assigned in dB
                Product product = new Product(
                        -1, body.name(), body.description(), body.price(),
                        body.stockQuantity(), body.imageURL()
                );
                System.out.println(product);
                Product response = ProductRepository.createProduct(product);
                if(response!=null){
                    ctx.json(response).status(200);
                }else{
                    ctx.status(400);
                }
            }
    );

  }

  public Javalin javalinApp() {
    return app;
  }
}
