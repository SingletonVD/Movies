package com.singletonvd.movies;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;

    private RecyclerView recyclerViewMovies;
    private MoviesAdapter moviesAdapter;
    private ProgressBar progressBarLoadingMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moviesAdapter = new MoviesAdapter();
        recyclerViewMovies = findViewById(R.id.recyclerViewMovies);
        progressBarLoadingMovies = findViewById(R.id.progressBarLoadingMovies);

        recyclerViewMovies.setAdapter(moviesAdapter);
        recyclerViewMovies.setLayoutManager(new GridLayoutManager(this, 2));

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel.getMovies().observe(
                this,
                movies -> moviesAdapter.setMovieList(movies)
        );

        viewModel.getIsLoading().observe(this, isLoading -> {
            int visibility = isLoading ? View.VISIBLE : View.GONE;
            progressBarLoadingMovies.setVisibility(visibility);
        });

        moviesAdapter.setOnReachEndListener(viewModel::loadMovies);
    }
}