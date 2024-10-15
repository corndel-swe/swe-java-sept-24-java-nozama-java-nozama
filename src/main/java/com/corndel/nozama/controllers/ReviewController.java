package com.corndel.nozama.controllers;
import com.corndel.nozama.repositories.ReviewRepository;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.sql.SQLException;


public class ReviewController {
    public static void getReviewsByProduct(Context ctx) throws SQLException {
        var id = ctx.pathParam("productId");
        var reviews = ReviewRepository.getReviewsByProduct(id);
        ctx.json(reviews);
    }
}
