package com.singletonvd.movies;

import com.google.gson.annotations.SerializedName;

public class Poster {

    @SerializedName("url")
    private final String url;

    public Poster(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
