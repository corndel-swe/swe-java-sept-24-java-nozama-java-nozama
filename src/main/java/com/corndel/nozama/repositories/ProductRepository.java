package com.corndel.nozama.repositories;

import com.corndel.nozama.DB;
import com.corndel.nozama.models.Product;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    public static List<Product> findAll() throws SQLException {
        var query = "SELECT id, name, description, price, stockQuantity, imageURL FROM products";

        try (var con = DB.getConnection();
             var stmt = con.createStatement();
             var rs = stmt.executeQuery(query)) {

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

    public static Product findById(String id) throws SQLException {
        var query = "SELECT id, name, description, price, stockQuantity, imageURL FROM products WHERE id = ?";

        try (var con = DB.getConnection();
             var stmt = con.prepareStatement(query)) {

            stmt.setString(1, id);
            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    var name = rs.getString("name");
                    var description = rs.getString("description");
                    var price = rs.getFloat("price");
                    var stockQuantity = rs.getInt("stockQuantity");
                    var imageURL = rs.getString("imageURL");

                    return new Product(id, name, description, price, stockQuantity, imageURL);
                } else {
                    return null;
                }
            }
        }
    }
    public static void main(String[] args) {
        String productId = "1";
        try {
            Product product = findById(productId);
            if (product != null) {
                System.out.println("Product ID: " + product.getId());
                System.out.println("Product Name: " + product.getName());
                System.out.println("Description: " + product.getDescription());
                System.out.println("Price: " + product.getPrice());
                System.out.println("Stock Quantity: " + product.getStockQuantity());
                System.out.println("Image URL: " + product.getImageURL());
            } else {
                System.out.println("Product not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error fetching product: " + e.getMessage());
        }
    }

}
