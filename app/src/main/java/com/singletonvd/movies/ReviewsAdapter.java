package com.singletonvd.movies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder> {

    private List<Review> reviews = new ArrayList<>();

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_item, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Review review = reviews.get(position);

        holder.textViewReviewAuthor.setText(review.getAuthor());
        holder.textViewReviewContent.setText(review.getReview());

        int colorResId;
        if (review.getType().equals("Позитивный")) {
            colorResId = android.R.color.holo_green_light;
        } else {
            colorResId = android.R.color.holo_red_light;
        }
        int color = ContextCompat.getColor(holder.itemView.getContext(), colorResId);
        holder.cardViewReview.setCardBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewReviewAuthor;
        private final TextView textViewReviewContent;
        private final CardView cardViewReview;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewReviewAuthor = itemView.findViewById(R.id.textViewReviewAuthor);
            textViewReviewContent = itemView.findViewById(R.id.textViewReviewContent);
            cardViewReview = itemView.findViewById(R.id.cardViewReview);
        }
    }
}
