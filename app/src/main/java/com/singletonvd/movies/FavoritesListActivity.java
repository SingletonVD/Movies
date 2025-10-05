package com.singletonvd.movies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

public class FavoritesListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewFavoriteMovies;
    private FavoriteMoviesViewModel viewModel;

    public static Intent makeIntent(Context context) {
        return new Intent(context, FavoritesListActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites_list);
        initViews();

        FavoriteMoviesAdapter favoriteMoviesAdapter = new FavoriteMoviesAdapter();
        favoriteMoviesAdapter.setOnCLickListener(movie ->
                MovieDetailsActivity.makeIntent(this, movie)
        );

        viewModel = new ViewModelProvider(this).get(FavoriteMoviesViewModel.class);
        viewModel.getMovies().observe(this, favoriteMoviesAdapter::setMovieList);

        recyclerViewFavoriteMovies.setAdapter(favoriteMoviesAdapter);
    }

    private void initViews() {
        recyclerViewFavoriteMovies = findViewById(R.id.recyclerViewFavoriteMovies);
    }
}