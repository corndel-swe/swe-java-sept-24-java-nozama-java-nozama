package com.corndel.nozama.exercises;

import com.corndel.nozama.App;
import com.corndel.nozama.repositories.UserRepository;
import io.javalin.Javalin;

public class D2E1 {
  /**
   * Creates a Javalin app with a single GET /ping endpoint.
   * This endpoint responds with the simple string "pong".
   * 
   * @see https://tech-docs.corndel.com/javalin/creating-a-server.html
   * @see https://tech-docs.corndel.com/javalin/request-response.html
   * @return a configured Javalin instance
   */
  public static void main(String[] args) {
    var d2E1 = new D2E1().createApp();
    d2E1.start(8080);
  }

  public static Javalin createApp() {
    Javalin app = Javalin.create();

    // TODO: add the GET /ping endpoint
    app.get("/ping", ctx -> {
      ctx.result("pong");
    });

    return app;
  }
}