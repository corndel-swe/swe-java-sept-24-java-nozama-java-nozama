package com.corndel.nozama.exercises;

import io.javalin.http.BadRequestResponse;
import io.javalin.http.ForbiddenResponse;
import io.javalin.http.HttpResponseException;
import io.javalin.http.UnauthorizedResponse;

public class D3E2 {

  public class Account {
    public String username;
    public String email;
    private String password;

    public Account(String username, String email, String password) {
      this.username = username;
      this.email = email;
      this.password = password;
    }

    // https://tech-docs.corndel.com/javalin/sending-errors.html
    public void updateUsername(String newUsername, String givenPassword)
        throws HttpResponseException {
      // TODO REMOVE ANSWERS
      // If newUsername is not given, throw an AppError with code 400 (Bad Request)
      if (newUsername == null || newUsername.isBlank()) {
        throw new BadRequestResponse("New Username cannot be blank");
      }

      // If password is not given, throw an AppError with code 401 (Unauthorized)
      if (givenPassword == null || givenPassword.isBlank()) {
        throw new UnauthorizedResponse("Password not given");
      }

      // If password is given but not correct, throw an AppError with code 403 (Forbidden)
      if (!givenPassword.equals(password)) {
        throw new ForbiddenResponse("Incorrect Password");
      }

      // If newUsername is given and password is correct, update the username
      username = newUsername;
    }
  }
}
