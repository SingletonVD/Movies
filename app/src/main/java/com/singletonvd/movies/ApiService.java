package com.singletonvd.movies;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService {

    @Headers("X-API-KEY: CA0XT3G-8VX4KS5-GXRM9SC-E4CVS46")
    @GET("movie?selectFields=id&selectFields=name&selectFields=description&selectFields=year&selectFields=rating&selectFields=poster&selectFields=videos&limit=50&notNullFields=name&notNullFields=description&notNullFields=year&notNullFields=rating.kp&notNullFields=poster.url&sortField=votes.kp&sortType=-1&rating.kp=7-10")
    Single<MoviesResponse> loadMovies(@Query("page") int page);

    @Headers("X-API-KEY: CA0XT3G-8VX4KS5-GXRM9SC-E4CVS46")
    @GET("review?selectFields=author&selectFields=review&selectFields=type&notNullFields=author&notNullFields=review&notNullFields=type&page=1&limit=50")
    Single<ReviewsResponse> loadReviews(@Query("movieId") int movieId);
}
