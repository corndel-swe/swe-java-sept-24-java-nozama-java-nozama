package com.corndel.nozama.exercises;

import org.junit.jupiter.api.Test;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;

import static org.assertj.core.api.Assertions.assertThat;

public class D2E1Tests {

  Javalin app = new D2E1().javalinApp();

  @Test
  public void GET_ping_returns_pong() {
    JavalinTest.test(app, (server, client) -> {
      var pong = "pong";
      assertThat(client.get("/ping").code()).isEqualTo(200);
      assertThat(client.get("/ping").body().string()).isEqualTo(pong);
    });
  }

}
