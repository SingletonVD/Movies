package com.singletonvd.movies;

import com.google.gson.annotations.SerializedName;

public class Review {

    @SerializedName("author")
    private final String author;

    @SerializedName("review")
    private final String review;

    @SerializedName("type")
    private final String type;

    public Review(String author, String review, String type) {
        this.author = author;
        this.review = review;
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public String getReview() {
        return review;
    }

    public String getType() {
        return type;
    }
}
