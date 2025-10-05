package com.singletonvd.movies;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiService {

    @Headers("X-API-KEY: CA0XT3G-8VX4KS5-GXRM9SC-E4CVS46")
    @GET("movie?selectFields=id&selectFields=name&selectFields=description&selectFields=year&selectFields=rating&selectFields=poster&page=1&limit=50&notNullFields=name&notNullFields=description&notNullFields=year&notNullFields=rating.kp&notNullFields=poster.url&sortFields=votes.kp&sortType-1&rating.kp=7-10")
    Single<MoviesResponse> loadMovies();
}
