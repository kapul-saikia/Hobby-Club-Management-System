package com.kapul.hobbyclub;

public class BookReview {
    private String name;
    private String review;
    private String imageUrl;

    public BookReview() {

    }

    public BookReview(String name, String review, String imageUrl) {
        this.name = name;
        this.review = review;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}