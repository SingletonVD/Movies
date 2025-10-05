package com.singletonvd.movies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Videos implements Serializable {

    @SerializedName("trailers")
    private final List<Trailer> trailers;

    public Videos(List<Trailer> trailers) {
        this.trailers = trailers;
    }

    public List<Trailer> getTrailers() {
        return trailers;
    }
}
