package com.singletonvd.movies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviewViewHolder> {

    private List<Movie> movieList = new ArrayList<>();

    @NonNull
    @Override
    public MoviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        return new MoviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviewViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        Glide.with(holder.itemView)
                .load(movie.getPoster().getUrl())
                .into(holder.imageViewPoster);
        holder.textViewRating.setText(movie.getRating().getKp());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    public static class MoviewViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageViewPoster;
        private final TextView textViewRating;

        public MoviewViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPoster = itemView.findViewById(R.id.imageViewPoster);
            textViewRating = itemView.findViewById(R.id.textViewRating);
        }
    }
}
