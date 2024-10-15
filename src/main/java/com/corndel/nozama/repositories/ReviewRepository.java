package com.corndel.nozama.repositories;

import com.corndel.nozama.DB;
import com.corndel.nozama.models.Review;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository {
    public static List<Review> getReviewsByProduct(String productId) throws SQLException{
        List<Review> reviews = new ArrayList<>();
        var query = "SELECT * FROM reviews JOIN products ON products.id = reviews.productId WHERE reviews.productId = ?";

        try (var connection = DB.getConnection();
             var stmt = connection.prepareStatement(query)) {

            stmt.setString(1, productId);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy, h:mm:ss a");

            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    String id = resultSet.getString("id");
                    String userId = resultSet.getString("userId");
                    int rating = resultSet.getInt("rating");
                    String reviewText = resultSet.getString("reviewText");
                    String reviewDateStr = resultSet.getString("reviewDate");
                    LocalDateTime reviewDate = null;
                    reviewDate = LocalDateTime.parse(reviewDateStr.replace("Z", ""));
                    Review review = new Review(productId, id, userId, rating, reviewText, reviewDate);
                    reviews.add(review);
                }
            }
        } catch (SQLException ignored) {
            System.out.println(ignored.getMessage());
        }return reviews;
    }

    public static Review postReview (Review review) {

        var query = "INSERT INTO reviews ( productId, userId, rating, reviewText, reviewDate) VALUES (?, ?, ?, ?, ?)";
        try (var con = DB.getConnection();
             var stmt = con.prepareStatement(query)) {
            stmt.setString(1, review.getProductId());
            stmt.setString(2, review.getUserId());
            stmt.setInt(3, review.getRating());
            stmt.setString(4, review.getReviewText());
            stmt.setObject(5, review.getReviewDate());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return review;
    }

    public static void main(String[] args) throws SQLException {
        var reviews = ReviewRepository.getReviewsByProduct("2");
        var currentDate = LocalDateTime.now();
        Review newReview = new Review("200", "77", 1, "I hate this product", currentDate);

        postReview(newReview);
        for (var review: reviews) {
            System.out.println(review);
        }
    }
}
