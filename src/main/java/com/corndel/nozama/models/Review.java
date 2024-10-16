package com.corndel.nozama.models;

import java.time.LocalDateTime;

public class Review {
    private int id;
    private int productId;
    private int userId;
    private int rating;
    private String reviewText;
    private LocalDateTime reviewDate;

    public Review(int productId, int id, int userId, int rating, String reviewText, LocalDateTime reviewDate) {
        this.productId = productId;
        this.id = id;
        this.userId = userId;
        this.rating = rating;
        this.reviewText = reviewText;
        this.reviewDate = reviewDate;
    }
    public Review(int productId, int userId, int rating, String reviewText, LocalDateTime reviewDate) {
        this.productId = productId;
        this.userId = userId;
        this.rating = rating;
        this.reviewText = reviewText;
        this.reviewDate = reviewDate;
    }

    @Override
    public String toString() {
        String formattedDate = reviewDate != null
                ? reviewDate.getMonth() + " " + reviewDate.getDayOfMonth() + ", " + reviewDate.getYear()
                : "N/A";

        return "Review{" +
                "id='" + id + '\'' +
                ", productId='" + productId + '\'' +
                ", userId='" + userId + '\'' +
                ", rating=" + rating +
                ", reviewText='" + reviewText + '\'' +
                ", reviewDate=" + formattedDate +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public LocalDateTime getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDateTime reviewDate) {
        this.reviewDate = LocalDateTime.parse(String.valueOf(reviewDate));
    }

}
