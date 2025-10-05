package com.singletonvd.movies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Trailer implements Serializable {

    @SerializedName("name")
    private final String name;

    @SerializedName("url")
    private final String url;

    public Trailer(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Trailer{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
