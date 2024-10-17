package com.corndel.nozama.repositories;

import com.corndel.nozama.DB;
import com.corndel.nozama.models.Product;
import com.corndel.nozama.models.Review;
import io.javalin.http.BadRequestResponse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository {
  public static List<Review> getReviewsByProduct(int productId) throws SQLException {
    List<Review> reviews = new ArrayList<>();
    var query = "SELECT * FROM reviews JOIN products ON products.id = reviews.productId WHERE reviews.productId = ?";

    try (var connection = DB.getConnection();
         var stmt = connection.prepareStatement(query)) {

      stmt.setInt(1, productId);

      try (ResultSet resultSet = stmt.executeQuery()) {
        while (resultSet.next()) {
          int id = resultSet.getInt("id");
          int userId = resultSet.getInt("userId");
          int rating = resultSet.getInt("rating");
          String reviewText = resultSet.getString("reviewText");
          String reviewDate = resultSet.getString("reviewDate");
          Review review = new Review(productId, id, userId, rating, reviewText, reviewDate);
          reviews.add(review);
        }
      }
    }
    return reviews;
  }

  public static Review postReview(Review review) throws SQLException {
    int productId = review.getProductId();
    int userId = review.getUserId();
    int rating = review.getRating();
    String reviewText = review.getReviewText();

    var query = "INSERT INTO reviews (productId, userId, rating, reviewText) VALUES (?, ?, ?, ?)";
    try (var con = DB.getConnection();
         var stmt = con.prepareStatement(query)) {
      stmt.setInt(1, productId);
      stmt.setInt(2, userId);
      stmt.setInt(3, rating);
      stmt.setString(4, reviewText);

      try (var rs = stmt.executeQuery()) {
        rs.next();
        int id = rs.getInt("id");
        String reviewDate = rs.getString("reviewDate");
        Review postedReview = new Review(productId, id, userId, rating, reviewText, reviewDate);
        return postedReview;
      }
    }
  }

  public static float getAverageRating(int productId) throws SQLException {

    var query = "SELECT AVG(reviews.rating) as averageRating FROM reviews JOIN products ON products.id = reviews.productId WHERE reviews.productId = ?";

      float averageRating = 0;
      try (var connection = DB.getConnection();
           var stmt = connection.prepareStatement(query)) {

          stmt.setInt(1, productId);

          try (ResultSet resultSet = stmt.executeQuery()) {
              while (resultSet.next()) {
                averageRating =  resultSet.getFloat("averageRating");
              }
          }
      }
      if (averageRating <= 0) {
        throw new BadRequestResponse("Product not found");
      }
      return averageRating;
  }


}
