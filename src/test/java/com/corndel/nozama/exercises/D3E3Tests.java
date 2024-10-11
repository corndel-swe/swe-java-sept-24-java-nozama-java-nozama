package com.corndel.nozama.exercises;

import static org.assertj.core.api.Assertions.assertThat;

import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.Test;

public class D3E3Tests {
  D3E2 d3e2 = new D3E2();
  D3E2.Account account = d3e2.new Account("legolas", "legolas@thefellowship.com", "elf4life");
  D3E3 d3e3 = new D3E3(account);
  Javalin app = d3e3.app;

  @Test
  public void PUT_throws_bad_request_if_username_is_absent() {
    JavalinTest.test(
        app,
        (server, client) -> {
          var response = client.put("/username", new UsernameRequest(null, "elf4life"));
          assertThat(response.code()).isEqualTo(400);
        });
  }

  @Test
  public void PUT_throws_bad_request_if_username_is_blank() {
    JavalinTest.test(
        app,
        (server, client) -> {
          var response = client.put("/username", new UsernameRequest("", "elf4life"));
          assertThat(response.code()).isEqualTo(400);
        });
  }

  @Test
  public void PUT_throws_unauthorized_if_password_is_null() {
    JavalinTest.test(
        app,
        (server, client) -> {
          var response = client.put("/username", new UsernameRequest("aragorn", null));
          assertThat(response.code()).isEqualTo(401);
        });
  }

  @Test
  public void PUT_throws_unauthorized_if_password_is_blank() {
    JavalinTest.test(
        app,
        (server, client) -> {
          var response = client.put("/username", new UsernameRequest("aragorn", ""));
          assertThat(response.code()).isEqualTo(401);
        });
  }

  @Test
  public void PUT_throws_forbidden_if_password_is_incorrect() {
    JavalinTest.test(
        app,
        (server, client) -> {
          var response = client.put("/username", new UsernameRequest("aragorn", "wrongpassword"));
          assertThat(response.code()).isEqualTo(403);
        });
  }

  @Test
  public void PUT_updates_username() {
    JavalinTest.test(
        app,
        (server, client) -> {
          var response = client.put("/username", new UsernameRequest("aragorn", "elf4life"));
          var expectedResponse = new UsernameResponse("aragorn");
          var expectedResponseJson =
              new JavalinJackson().toJsonString(expectedResponse, UsernameResponse.class);
          assertThat(response.code()).isEqualTo(200);
          assertThat(response.body().string()).isEqualTo(expectedResponseJson);
        });
  }
}
