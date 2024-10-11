package com.corndel.nozama.exercises;

import io.javalin.Javalin;

public class D2E2 {
  private Javalin app;

  public D2E2() {
    app = Javalin.create();

    // https://tech-docs.corndel.com/javalin/query-params.html
    app.get(
        "/sumup",
        ctx -> {
          /**
           * This endpoint accepts a query param, n res.send() the sum of integers from 1 to n if n
           * is not given, respond with 0 e.g. /sumup?n=4 => 10
           */
          var n = Integer.parseInt(ctx.queryParam("n"));
          var sum = n * (n + 1) / 2;
          ctx.result(Integer.toString(sum));
        });

    // https://tech-docs.corndel.com/javalin/url-params.html
    app.get(
        "/multiply/{x}/{y}",
        ctx -> {
          /** This endpoint responds with the product of x and y e.g. /multiply/3/5 => 15 */
          var x = Integer.parseInt(ctx.pathParam("x"));
          var y = Integer.parseInt(ctx.pathParam("y"));
          ctx.result(Integer.toString(x * y));
        });
  }

  public Javalin javalinApp() {
    return app;
  }
}
