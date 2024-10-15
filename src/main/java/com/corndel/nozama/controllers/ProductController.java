package com.corndel.nozama.controllers;

import io.javalin.Javalin;
import io.javalin.http.Context;
import com.corndel.nozama.models.Product;
import com.corndel.nozama.repositories.ProductRepository;

import java.sql.SQLException;
import java.util.List;


public class ProductController {

    public static void getAllProducts(Context ctx) throws SQLException {
        List<Product> products = ProductRepository.findAll();
        ctx.json(products);
    }

    public static void getProductById(Context ctx) throws SQLException {
        String id = ctx.pathParam("id");
        Product product = ProductRepository.findById(id);
        if (product != null) {
            ctx.json(product);
        } else {
            ctx.status(404).result("Product not found");
        }
    }

}
