package com.corndel.nozama.controllers;

import com.corndel.nozama.repositories.UserRepository;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import io.javalin.http.UnauthorizedResponse;
import com.corndel.nozama.models.User;
import java.sql.SQLException;

public class UserController {

    public static void getAllUsers(Context ctx) throws SQLException {
        var users = UserRepository.findAll();
        ctx.json(users);
    }

    public static void getUserById(Context ctx) throws SQLException {
        var id = Integer.parseInt(ctx.pathParam("userId"));
        var user = UserRepository.findById(id);
        if (user != null){
        ctx.status(200).json(user);
        } else {
        throw new BadRequestResponse("Cant find user with this Id");
        }
    }


    public static void loginUser(Context ctx) throws SQLException {
        User body = ctx.bodyAsClass(User.class);
        var user = UserRepository.loginUser(body.getUsername(), body.getPassword());
        if (user != null) {
            ctx.status(201).json(user);
        } else {
            throw new UnauthorizedResponse("Invalid username or password");
        }
    }
}
