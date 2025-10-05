package com.singletonvd.movies;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
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

        initViews();

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        moviesAdapter = new MoviesAdapter();
        moviesAdapter.setOnReachEndListener(viewModel::loadMovies);
        moviesAdapter.setOnCLickListener(movie ->
                startActivity(MovieDetailsActivity.makeIntent(this, movie))
        );

        recyclerViewMovies.setAdapter(moviesAdapter);
        recyclerViewMovies.setLayoutManager(new GridLayoutManager(this, 2));

        viewModel.getMovies().observe(
                this,
                movies -> moviesAdapter.setMovieList(movies)
        );

        viewModel.getIsLoading().observe(this, isLoading -> {
            int visibility = isLoading ? View.VISIBLE : View.GONE;
            progressBarLoadingMovies.setVisibility(visibility);
        });
    }

    public void initViews() {
        recyclerViewMovies = findViewById(R.id.recyclerViewMovies);
        progressBarLoadingMovies = findViewById(R.id.progressBarLoadingMovies);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemFavorite) {
            Intent intent = FavoritesListActivity.makeIntent(this);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}