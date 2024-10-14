package com.corndel.nozama.repositories;

import com.corndel.nozama.DB;
import com.corndel.nozama.models.Product;
import com.corndel.nozama.models.User;

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
                var id = rs.getInt("id");
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

    public static Product findById(int id) throws SQLException {
        // make the query
        var query = "SELECT *\n";
        query += "FROM products \n";
        query += ("WHERE id = ?");

        // try with resources - get connection
        try (var con = DB.getConnection();
             var stmt = con.prepareStatement(query);) {

            // sub in the id argument
            stmt.setInt(1, id);

            // try with resources - execute query
            try (var rs = stmt.executeQuery();){

                if(rs.next()){
                    var name = rs.getString("name");
                    var description = rs.getString("description");
                    var price = rs.getFloat("price");
                    var stockQuantity = rs.getInt("stockQuantity");
                    var imageURL = rs.getString("imageURL");

                    Product product = new Product(id, name, description, price, stockQuantity, imageURL);
                    return product;
                }else{
                    System.out.println("not a valid id.");
                    return null;
                }
            }
        }
    }


    public static List<Product> findByCategory(String category) throws SQLException {
        // make the query
        var query = "SELECT *\n";
        query += "FROM products INNER JOIN product_categories ON products.id = product_categories.productId\n";
        query += ("INNER JOIN categories ON categories.id = product_categories.categoryId WHERE categories.name = ?");

        // try with resources - get connection
        try (var con = DB.getConnection();
             var stmt = con.prepareStatement(query);) {

            // sub in the id argument
            stmt.setString(1, category);
            try (var rs = stmt.executeQuery();) {

                var products = new ArrayList<Product>();
                while (rs.next()) {
                    var id = rs.getInt("id");
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
    }

//    public static void main(String[] args) {
//        String dbUrl = "jdbc:sqlite:nozama.db";
//        try (var connection = DriverManager.getConnection(dbUrl)) {
//            // call findall
//            System.out.println(ProductRepository.findAll());
//            System.out.println(ProductRepository.findById(3));
//            System.out.println(ProductRepository.findByCategory("Baby"));
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//    }


    }