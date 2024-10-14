package com.corndel.nozama.repositories;

import com.corndel.nozama.DB;
import com.corndel.nozama.models.Review;
import com.corndel.nozama.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository {
    public static List<Review> getReviewsByProduct(String productId) throws SQLException{
        List<Review> reviews = new ArrayList<>();
        var query = "SELECT * FROM reviews WHERE productId = ?";

        try (var connection = DB.getConnection();
             var stmt = connection.prepareStatement(query)) {

            stmt.setString(1, productId);

            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    String id = resultSet.getString("id");
                    String userId = resultSet.getString("userId");
                    int rating = resultSet.getInt("rating");
                    String reviewText = resultSet.getString("reviewText");
                    LocalDateTime reviewDate = resultSet.getTimestamp("reviewDate").toLocalDateTime();

                    Review review = new Review(productId, id, userId, rating, reviewText, reviewDate);
                    reviews.add(review);
                }
            }
        } catch (SQLException ignored) {

        }return reviews;
    }

    public static void main(String[] args) throws SQLException {
        var reviews = ReviewRepository.getReviewsByProduct("200");

        for (var review: reviews) {
            System.out.println(review);
        }
    }
}
