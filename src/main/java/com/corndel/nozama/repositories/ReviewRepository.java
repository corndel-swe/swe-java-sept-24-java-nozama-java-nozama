package com.corndel.nozama.repositories;

import com.corndel.nozama.DB;
import com.corndel.nozama.models.Review;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository {
    public static void main(String[] args) throws SQLException {
        System.out.println(createReview(5, 5, 5, "love it").getId());
    }

    public static List<Review> getReviewsByProduct(int _productId) throws SQLException {
        var query = "SELECT * FROM reviews WHERE productId = ?";

        try (var conn = DB.getConnection();
             var stmt = conn.prepareStatement(query);) {

            stmt.setInt(1, _productId);

            try (var rs = stmt.executeQuery();) {

                var reviews = new ArrayList<Review>();

                while (rs.next()) {
                    var id = rs.getInt("id");
                    var productId = rs.getInt("productId");
                    var userId = rs.getInt("userId");
                    var rating = rs.getInt("rating");
                    var reviewText = rs.getString("reviewText");
                    var reviewDate = rs.getString("reviewDate");
                    reviews.add(new Review(id, productId, userId, rating, reviewText, reviewDate));
                }
                return reviews;
            }
        }
    }

    public static Double getAvgRatingByProduct(int productId) throws SQLException {
        var reviews = getReviewsByProduct(productId);
        return reviews.stream().mapToInt(Review::getRating).average().orElse(0.0);
    }

    public static Review createReview(int productId, int userId, int rating, String reviewText) throws SQLException {
        var insert_query = "INSERT INTO reviews (productId, userId, rating, reviewText) VALUES (?, ?, ?, ?)";


        try (var conn = DB.getConnection();
             var stmt = conn.prepareStatement(insert_query)) {

            stmt.setInt(1, productId);
            stmt.setInt(2, userId);
            stmt.setInt(3, rating);
            stmt.setString(4, reviewText);

            stmt.executeUpdate();
        }

        var query = "SELECT id, reviewDate FROM reviews ORDER BY id DESC LIMIT 1;";
        int lastId;
        String reviewDate;
        try (var conn = DB.getConnection();
             var stmt = conn.prepareStatement(query)) {

            var rs = stmt.executeQuery();
            lastId = rs.getInt("id");
            reviewDate = rs.getString("reviewDate");
        }

        return new Review(lastId, productId, userId, rating, reviewText, reviewDate);
    }}