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
            var user = UserRepository.logUserIn(logInRequest.username(),
                    logInRequest.firstName(),logInRequest.lastName(), logInRequest.email(),
                    logInRequest.avatar(), logInRequest.password());

            ctx.json(user);
        } catch (Exception e) {
            // test
            System.err.println(e.getMessage());
        }
    }
}


record UserRequest(String username, String firstName , String lastName, String email, String avatar, String password) { }