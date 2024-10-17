package com.corndel.nozama.controllers;

import com.corndel.nozama.models.Product;
import com.corndel.nozama.models.ProductRequest;
import com.corndel.nozama.repositories.ProductRepository;
import io.javalin.http.Context;

import java.sql.SQLException;

public class ProductController {
    public static void getAllProducts(Context ctx) throws SQLException {
        var products = ProductRepository.findAll();
        ctx.json(products).status(200);
    }
    public static void getProductById(Context ctx) throws SQLException {
        var id = Integer.parseInt(ctx.pathParam("productId"));
        var product = ProductRepository.findById(id);
        if(product!=null) {
            ctx.json(product).status(200);
        }else{
            ctx.status(400);
        }
    }
    public static void getProductsByCategory(Context ctx) throws SQLException {
        var category = ctx.pathParam("category");
        var products = ProductRepository.findByCategory(category);
        if(products!=null) {
            ctx.json(products).status(200);
        }else{
            ctx.status(400);
        }
    }
    public static void addNewProduct(Context ctx) throws SQLException {
        ProductRequest body = ctx.bodyAsClass(ProductRequest.class);
        // dummy id -1, will be auto assigned in dB
        Product product = new Product(
                -1, body.name(), body.description(), body.price(),
                body.stockQuantity(), body.imageURL()
        );
        System.out.println(product);
        Product response = ProductRepository.createProduct(product);
        if(response!=null){
            ctx.json(response).status(200);
        }else{
            ctx.status(400);
        }
    }
}
