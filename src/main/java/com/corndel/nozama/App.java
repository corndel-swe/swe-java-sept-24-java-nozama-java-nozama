package com.corndel.nozama;

import com.corndel.nozama.controllers.UserController;
import com.corndel.nozama.repositories.UserRepository;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;
import com.corndel.nozama.models.User;
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
              path("users", () -> {
                  get("", UserController::getAllUsers);
                  get("/{userId}", UserController::getUserById);
                  post("/login", UserController::loginUser);
              });
          });
      });
  }



//    app = Javalin.create();
//    app.get(
//        "/",
//        ctx -> {
//          var users = UserRepository.findAll();
//          ctx.json(users);
//        });
//    app.get(
//        "/users/{userId}",
//        ctx -> {
//          var id = Integer.parseInt(ctx.pathParam("userId"));
//          var user = UserRepository.findById(id);
//          ctx.status(HttpStatus.IM_A_TEAPOT).json(user);
//        });
//    app.post(
//            "/users/login",
//            ctx ->{
//                User body = ctx.bodyAsClass(User.class);
//                var user = UserRepository.loginUser(body.getUsername(),body.getPassword());
//                ctx.status(201);
//                ctx.json(user);
//            }
//    );




    public Javalin javalinApp() {
    return app;
  }
}
