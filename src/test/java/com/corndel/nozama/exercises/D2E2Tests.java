package com.corndel.nozama.exercises;

import org.junit.jupiter.api.Test;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;

import static org.assertj.core.api.Assertions.assertThat;

public class D2E2Tests {

  Javalin app = D2E2.createApp();

  @Test
  public void GET_sumup_returns_number() {
    JavalinTest.test(app, (server, client) -> {
      assertThat(client.get("/sumup?n=4").code()).isEqualTo(200);
      assertThat(client.get("/sumup?n=4").body().string()).containsOnlyDigits();
    });
  }

  @Test
  public void GET_sumup_returns_correct_number() {
    JavalinTest.test(app, (server, client) -> {
      assertThat(client.get("/sumup?n=4").code()).isEqualTo(200);
      assertThat(client.get("/sumup?n=4").body().string()).isEqualTo("10");
    });
  }

  @Test
  public void GET_mutiply_returns_number() {
    JavalinTest.test(app, (server, client) -> {
      assertThat(client.get("/multiply/2/5").code()).isEqualTo(200);
      assertThat(client.get("/multiply/2/5").body().string()).containsOnlyDigits();
    });
  }

  @Test
  public void GET_multiply_returns_correct_number() {
    JavalinTest.test(app, (server, client) -> {
      assertThat(client.get("/multiply/2/5").code()).isEqualTo(200);
      assertThat(client.get("/multiply/2/5").body().string()).isEqualTo("10");
    });
  }
}
