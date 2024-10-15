package com.corndel.nozama.exercises;

import io.javalin.Javalin;

public class D2E2 {
  /**
   * Creates a Javalin app with two endpoints:
   * 
   * - GET /sumup responds with the sum of the integers from 1 to n, where n is
   * provided as a query parameter. If n is not provided, it responds with 0.
   * 
   * - GET /multiply/{x}/{y} responds with the product of x and y.
   * 
   * @see https://tech-docs.corndel.com/javalin/query-params.html
   * @see https://tech-docs.corndel.com/javalin/url-params.html
   * @return a configured Javalin instance
   */
  public static void main(String[] args) {
      var d2E2 = new D2E2().createApp();
      d2E2.start(8080);
  }

  public static Javalin createApp() {
    var app = Javalin.create();
    app.get(
            // http://localhost:8080/sumup?n=15 > 120
        "/sumup",
        ctx -> {
          // TODO:
            String nParam = ctx.queryParam("n");
            int n = (nParam == null) ? 0 : Integer.parseInt(nParam);
            int sum = 0;
            for (int i = 1; i <= n; i++) {
                sum += i;
            }
            ctx.result(String.valueOf(sum));
        });

    app.get(
            // http://localhost:8080/multiply/2/3 > 6
        "/multiply/{x}/{y}",
        ctx -> {
          // TODO
            var x = Integer.parseInt(ctx.pathParam("x"));
            var y = Integer.parseInt(ctx.pathParam("y"));
            int product = x*y;
            ctx.result(String.valueOf(product));
        });

    return app;
  }
}
