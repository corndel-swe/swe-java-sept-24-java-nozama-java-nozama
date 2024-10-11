package com.corndel.nozama.exercises;

import io.javalin.Javalin;

public class D3E3 {
  public Javalin app;
  public D3E2.Account account;

  // https://tech-docs.corndel.com/javalin/sending-errors.html
  public D3E3(D3E2.Account account) {
    this.account = account;

    this.app = Javalin.create();
    app.put(
        "/username",
        ctx -> {
          /**
           * Use try/catch to attempt account.updateUsername with the newUsername and password found
           * in the req.body.
           *
           * <p>On success, respond with the newUsername in the response, e.g. { username:
           * 'NewUsername'}
           *
           * <p>In case this fails, set the status of the response to the error code and send a
           * useful message.
           */
          // TODO REMOVE ANSWER
          var usernameRequest = ctx.bodyAsClass(UsernameRequest.class);
          account.updateUsername(usernameRequest.newUsername, usernameRequest.password);
          ctx.json(new UsernameResponse(usernameRequest.newUsername));
        });
  }
}

class UsernameRequest {
  public String newUsername;
  public String password;

  public UsernameRequest() {}

  public UsernameRequest(String newUsername, String password) {
    this.newUsername = newUsername;
    this.password = password;
  }
}

class UsernameResponse {
  public String username;

  public UsernameResponse() {}

  public UsernameResponse(String username) {
    this.username = username;
  }
}
