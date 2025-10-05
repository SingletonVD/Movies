package com.singletonvd.movies;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class MovieDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_MOVIE = "movie";

    private ImageView imageViewPoster;
    private TextView textViewTitle;
    private TextView textViewYear;
    private TextView textViewDescription;
    private RecyclerView recyclerViewTrailers;
    private RecyclerView recyclerViewReviews;
    private ImageView imageViewStar;

    private MovieDetailsViewModel viewModel;

    public static Intent makeIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailsActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        initViews();
        viewModel = new ViewModelProvider(this).get(MovieDetailsViewModel.class);

        TrailersAdapter trailersAdapter = new TrailersAdapter();
        trailersAdapter.setOnTrailerClickListener(
                trailer -> {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(trailer.getUrl()));
                    startActivity(intent);
                }
        );
        recyclerViewTrailers.setAdapter(trailersAdapter);

        ReviewsAdapter reviewsAdapter = new ReviewsAdapter();
        recyclerViewReviews.setAdapter(reviewsAdapter);

        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);

        Drawable starOff = ContextCompat.getDrawable(this, android.R.drawable.star_big_off);
        Drawable starOn = ContextCompat.getDrawable(this, android.R.drawable.star_big_on);

        if (movie != null) {
            Glide.with(this)
                    .load(movie.getPoster().getUrl())
                    .into(imageViewPoster);
            textViewTitle.setText(movie.getName());
            textViewYear.setText(String.valueOf(movie.getYear()));
            textViewDescription.setText(movie.getDescription());

            Videos videos = movie.getVideos();
            if (videos != null) {
                trailersAdapter.setTrailers(videos.getTrailers());
            }

            viewModel.loadReviews(movie.getId());
            viewModel.getReviews().observe(this, reviewsAdapter::setReviews);

            viewModel.getFavoriteMovie(movie.getId()).observe(this, movieInDb -> {
                if (movieInDb == null || movieInDb.getId() != movie.getId()) {
                    imageViewStar.setImageDrawable(starOff);
                    imageViewStar.setOnClickListener(view -> viewModel.addMovie(movie));
                } else {
                    imageViewStar.setImageDrawable(starOn);
                    imageViewStar.setOnClickListener(view -> viewModel.removeMovie(movie.getId()));
                }
            });
        }
    }

    private void initViews() {
        imageViewPoster = findViewById(R.id.imageViewPoster);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewYear = findViewById(R.id.textViewYear);
        textViewDescription = findViewById(R.id.textViewDescription);
        recyclerViewTrailers = findViewById(R.id.recyclerViewTrailers);
        recyclerViewReviews = findViewById(R.id.recyclerViewReviews);
        imageViewStar = findViewById(R.id.imageViewStar);
    }
}