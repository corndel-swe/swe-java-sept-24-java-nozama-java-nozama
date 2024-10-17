package com.corndel.nozama;

import com.corndel.nozama.controllers.UserController;
import io.javalin.Javalin;
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



    public Javalin javalinApp() {
    return app;
  }
}
