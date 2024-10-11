package com.corndel.nozama.exercises;

import io.javalin.Javalin;
import io.javalin.http.Context;

public class D3E1 {
  private Javalin app;

  public static Counter counter = new Counter();

  public D3E1() {
    app = Javalin.create();
    app.get("/counter", CounterRouter::getCounter);

    app.put("/counter/increment", CounterRouter::increment);
  }

  public Javalin javalinApp() {
    return app;
  }
}

class Counter {
  public int count;

  public Counter() {
    count = 0;
  }
}

class CounterRouter {

  public static void getCounter(Context ctx) {
    /**
     * Add a GET /counter endpoint.
     *
     * <p>It should simply ctx.json(state)
     */

    // TODO REMOVE ANSWER
    ctx.json(D3E1.counter);
  }

  public static void increment(Context ctx) {
    /**
     * Add a PUT /counter/increment endpoint.
     *
     * <p>It should increase the counter by 1 and then res.json(state)
     */

    // TODO REMOVE ANSWER
    D3E1.counter.count++;
    ctx.json(D3E1.counter);
  }
}
