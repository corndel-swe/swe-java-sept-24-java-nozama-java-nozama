package com.corndel.nozama.controllers;

import com.corndel.nozama.createUserRequest;
import com.corndel.nozama.models.User;
import com.corndel.nozama.repositories.UserRepository;
import io.javalin.http.Context;

public class UserController {
  public static void createUser(Context ctx) {
    createUserRequest body = ctx.bodyAsClass(createUserRequest.class);
    try {
      User user = UserRepository.insertUser(body);
      ctx.status(201);
      ctx.json(user);
    } catch (Exception e) {
      System.err.println(e.getMessage());
      ctx.status(400);
    }
  }
}
