package com.corndel.nozama;

import com.corndel.nozama.models.User;
import com.corndel.nozama.repositories.UserRepository;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;

public class App {
  private Javalin app;

  public static void main(String[] args) {
    Javalin app = new App().javalinApp();
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
    app.post(
      "/users",
      ctx -> {
        createUserRequest body = ctx.bodyAsClass(createUserRequest.class);
        try {
          User user = UserRepository.createUser(body);
          ctx.status(201);
          ctx.json(user);
        } catch (Exception e) {
          System.err.println(e.getMessage());
          ctx.status(400);
        }
      }
    );
  }

  public Javalin javalinApp() {
    return app;
  }
}
