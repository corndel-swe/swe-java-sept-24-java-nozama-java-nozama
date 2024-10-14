package com.corndel.nozama.repositories;

import com.corndel.nozama.DB;
import com.corndel.nozama.models.Product;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    public static List<Product> findAll() throws SQLException {
        var query = "SELECT * FROM products";

        try (var con = DB.getConnection();
             var stmt = con.createStatement();
             var rs = stmt.executeQuery(query);) {

            var products = new ArrayList<Product>();
            while (rs.next()) {
                var id = rs.getString("id");
                var name = rs.getString("name");
                var description = rs.getString("description");
                var price = rs.getFloat("price");
                var stockQuantity = rs.getInt("stockQuantity");
                var imageURL = rs.getString("imageURL");

                products.add(new Product(id, name, description, price, stockQuantity, imageURL));
            }

            return products;
        }
    }

//    public static void main(String[] args) {
//        String dbUrl = "jdbc:sqlite:nozama.db";
//        try (var connection = DriverManager.getConnection(dbUrl)) {
//            // call findall
//            System.out.println(ProductRepository.findAll());
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//    }
}

