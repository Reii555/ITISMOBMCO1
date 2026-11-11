package com.itismob.s02.salazar.allenaedelweiss.myapplication.homechildfragment.homereviewfragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itismob.s02.salazar.allenaedelweiss.myapplication.R;
import com.itismob.s02.salazar.allenaedelweiss.myapplication.Review;

import java.util.List;

public class MyReviewRecyclerViewAdapter extends RecyclerView.Adapter<MyReviewRecyclerViewAdapter.ViewHolder> {

    private final List<Review> items;
    private final int layoutResId;

    public MyReviewRecyclerViewAdapter(List<Review> items) {
        this(items, R.layout.item_review);
    }

    public MyReviewRecyclerViewAdapter(List<Review> items, int layoutResId) {
        this.items = items;
        this.layoutResId = layoutResId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutResId, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Review item = items.get(position);
        if (holder.reviewDateTv != null && item.getDate() != null) {
            holder.reviewDateTv.setText(item.getDate());
        }
        if (holder.reviewBookTV != null && item.getBookTitle() != null) {
            holder.reviewBookTV.setText(item.getBookTitle());
        }
        if (holder.reviewRatingTV != null) {
            holder.reviewRatingTV.setText(String.valueOf(item.getRating()));
        }
        if (holder.reviewContentTV != null && item.getContent() != null) {
            holder.reviewContentTV.setText(item.getContent());
        }
        if (holder.reviewLikesTV != null) {
            holder.reviewLikesTV.setText(String.format("%,d Likes", item.getLikes()));
        }
        if (holder.reviewBookIV != null && item.getBookImage() != 0) {
            holder.reviewBookIV.setImageResource(item.getBookImage());
        }
        if (holder.friendNameTv != null && item.getFriendName() != null) {
            holder.friendNameTv.setText(item.getFriendName());
        }
        if (holder.friendPfpIv != null && item.getFriendPfp() != 0) {
            holder.friendPfpIv.setImageResource(item.getFriendPfp());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView reviewDateTv;
        public final TextView reviewBookTV;
        public final TextView reviewRatingTV;
        public final TextView reviewContentTV;
        public final TextView reviewLikesTV;
        public final ImageView reviewBookIV;
        public final TextView friendNameTv;
        public final ImageView friendPfpIv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            reviewDateTv = itemView.findViewById(R.id.reviewDateTv);
            reviewBookTV = itemView.findViewById(R.id.reviewBookTV);
            reviewRatingTV = itemView.findViewById(R.id.reviewRatingTV);
            reviewContentTV = itemView.findViewById(R.id.reviewContentTV);
            reviewLikesTV = itemView.findViewById(R.id.reviewLikesTV);
            reviewBookIV = itemView.findViewById(R.id.reviewBookIV);
            friendNameTv = itemView.findViewById(R.id.friendNameTv);
            friendPfpIv = itemView.findViewById(R.id.friendPfpIv);
        }
    }
}
