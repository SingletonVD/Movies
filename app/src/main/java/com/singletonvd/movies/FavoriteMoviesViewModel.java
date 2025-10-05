package com.singletonvd.movies;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class FavoriteMoviesViewModel extends AndroidViewModel {
    private final LiveData<List<Movie>> movies;

    public FavoriteMoviesViewModel(@NonNull Application application) {
        super(application);
        movies = MovieDatabase.getInstance(application).movieDao().getAllFavoriteMovies();
    }

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }
}
