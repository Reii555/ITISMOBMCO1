package com.itismob.s02.salazar.allenaedelweiss.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private List<Review> reviews;

    public ReviewAdapter(List<Review> reviews) {
            this.reviews = reviews;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_review, parent, false);

        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {

        Review review = reviews.get(position);

        holder.reviewDateTv.setText(review.getDate());
        holder.reviewBookTV.setText(review.getBookTitle());
        holder.reviewRatingTV.setText(String.valueOf(review.getRating()));
        holder.reviewContentTV.setText(review.getContent());

        holder.reviewLikesTV.setText(String.format("%,d Likes", review.getLikes()));

        holder.reviewBookIV.setImageResource(review.getBookImage());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {

        TextView reviewDateTv;
        TextView reviewBookTV;
        TextView reviewRatingTV;
        TextView reviewContentTV;
        TextView reviewLikesTV;

        ImageView reviewBookIV;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);

            reviewDateTv = itemView.findViewById(R.id.reviewDateTv);
            reviewBookTV = itemView.findViewById(R.id.reviewBookTV);
            reviewRatingTV = itemView.findViewById(R.id.reviewRatingTV);
            reviewContentTV = itemView.findViewById(R.id.reviewContentTV);
            reviewLikesTV = itemView.findViewById(R.id.reviewLikesTV);
            reviewBookIV = itemView.findViewById(R.id.reviewBookIV);
        }
    }
}
