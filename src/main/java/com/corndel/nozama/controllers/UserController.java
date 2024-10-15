package com.corndel.nozama.controllers;
import com.corndel.nozama.repositories.UserRepository;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.sql.SQLException;


public class UserController {
    public static void userLogIn(Context ctx) {
        try {

            var logInRequest = ctx.bodyAsClass(UserRequest.class);
            var users = UserRepository.logUserIn(logInRequest.id(), logInRequest.password());
            ctx.json(users);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}

record UserRequest(Integer id, String password) { }
