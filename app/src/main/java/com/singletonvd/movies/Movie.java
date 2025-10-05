package com.singletonvd.movies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable {

    @SerializedName("id")
    private final int id;

    @SerializedName("name")
    private final String name;

    @SerializedName("description")
    private final String description;

    @SerializedName("year")
    private final int year;

    @SerializedName("poster")
    private final Poster poster;

    @SerializedName("rating")
    private final Rating rating;

    @SerializedName("videos")
    private final Videos videos;


    public Movie(
            int id,
            String name,
            String description,
            int year,
            Poster poster,
            Rating rating,
            Videos videos
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.year = year;
        this.poster = poster;
        this.rating = rating;
        this.videos = videos;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getYear() {
        return year;
    }

    public Poster getPoster() {
        return poster;
    }

    public Rating getRating() {
        return rating;
    }

    public Videos getVideos() {
        return videos;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", year=" + year +
                ", poster=" + poster +
                ", rating=" + rating +
                '}';
    }
}
