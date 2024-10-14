package com.corndel.nozama.repositories;

import com.corndel.nozama.DB;
import com.corndel.nozama.models.Product;
import com.corndel.nozama.models.User;

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
                Product product = new Product();
                product.setId(rs.getString("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setImageURL(rs.getString("imageURL"));
                product.setPrice(rs.getFloat("price"));
                product.setStockQuantity(rs.getInt("stockQuantity"));
                products.add(product);
            }

            return products;
        }
    }


    public static void main(String[] args) throws SQLException {
        System.out.println(findAll());
    }
}
