package com.corndel.nozama.models;

import java.time.LocalDateTime;

public class Review {
    private String id;
    private String productId;
    private String userId;
    private int rating;
    private String reviewText;
    private LocalDateTime reviewDate;

    public Review(String productId, String id, String userId, int rating, String reviewText, LocalDateTime reviewDate) {
        this.productId = productId;
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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
