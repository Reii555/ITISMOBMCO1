package com.itismob.s02.salazar.allenaedelweiss.myapplication;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ReviewFragment extends Fragment {

    private ImageButton backBtn;
    private Button followBtn;
    private ImageView likeBtnIv;
    private TextView likeCountTv;
    private RecyclerView commentsRv;
    private TextView noCommentsTv;

    private boolean isFollowing = false;
    private boolean isLiked = false;
    private int likeCount = 142;

    public ReviewFragment() {
        super(R.layout.fragment_review);
    }

    public static ReviewFragment newInstance(Review review) {
        ReviewFragment fragment = new ReviewFragment();
        if (review != null) {
            Bundle args = new Bundle();
            args.putString("book_title", review.getBookTitle());
            args.putString("date", review.getDate());
            args.putFloat("rating", review.getRating());
            args.putString("content", review.getContent());
            args.putInt("likes", review.getLikes());
            args.putInt("book_cover_res", review.getBookImage());
            args.putString("friend_name", review.getFriendName());
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Find views from layout
        backBtn = view.findViewById(R.id.backBtn);
        followBtn = view.findViewById(R.id.followBtn);
        likeBtnIv = view.findViewById(R.id.likeBtnIv);
        likeCountTv = view.findViewById(R.id.likeCountTv);
        commentsRv = view.findViewById(R.id.commentsRv);
        noCommentsTv = view.findViewById(R.id.noCommentsTv);

        TextView reviewByUsernameTv = view.findViewById(R.id.reviewByUsernameTv);
        TextView bookTitleTv = view.findViewById(R.id.bookTitleTv);
        TextView reviewDateTv = view.findViewById(R.id.reviewDateTv);
        TextView reviewCommentTv = view.findViewById(R.id.reviewCommentTv);
        RatingBar bookRatingBar = view.findViewById(R.id.bookRatingBar);
        ImageView bookCoverIv = view.findViewById(R.id.bookCoverIv);

        if (getArguments() != null) {
            String title = getArguments().getString("book_title");
            String date = getArguments().getString("date");
            float rating = getArguments().getFloat("rating", 0f);
            String content = getArguments().getString("content");
            int likes = getArguments().getInt("likes", 142);
            int coverRes = getArguments().getInt("book_cover_res", 0);
            String friendName = getArguments().getString("friend_name");

            if (friendName != null && reviewByUsernameTv != null) {
                reviewByUsernameTv.setText("Review by @" + friendName);
            }

            if (reviewByUsernameTv != null) {
                reviewByUsernameTv.setOnClickListener(v -> {
                    String authorName = friendName != null ? friendName : "deartheodosia";
                    if (getActivity() instanceof HomeActivity) {
                        ((HomeActivity) getActivity()).navigateFragment(OtherUserProfileFragment.newInstance(authorName, R.drawable.otherpfp), true);
                    }
                });
            }
            if (title != null && bookTitleTv != null) bookTitleTv.setText(title);
            if (date != null && reviewDateTv != null) reviewDateTv.setText("Read on " + date);
            if (content != null && reviewCommentTv != null) reviewCommentTv.setText(content);
            if (likes > 0) {
                likeCount = likes;
                if (likeCountTv != null) likeCountTv.setText(String.format("%,d likes", likes));
            }
            if (bookRatingBar != null && rating > 0) bookRatingBar.setRating(rating);
            if (coverRes != 0 && bookCoverIv != null) bookCoverIv.setImageResource(coverRes);
        }

        // Back button navigation
        if (backBtn != null) {
            backBtn.setOnClickListener(v -> {
                if (getActivity() != null) {
                    getActivity().getOnBackPressedDispatcher().onBackPressed();
                }
            });
        }

        // Follow / Unfollow toggle
        if (followBtn != null) {
            followBtn.setOnClickListener(v -> {
                isFollowing = !isFollowing;
                if (isFollowing) {
                    followBtn.setText("Following");
                    followBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#1E2C3A")));
                    Toast.makeText(getContext(), "Followed user", Toast.LENGTH_SHORT).show();
                } else {
                    followBtn.setText("Follow");
                    followBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#2A3A4E")));
                    Toast.makeText(getContext(), "Unfollowed user", Toast.LENGTH_SHORT).show();
                }
            });
        }

        // Like / Unlike review toggle
        if (likeBtnIv != null) {
            likeBtnIv.setOnClickListener(v -> {
                isLiked = !isLiked;
                if (isLiked) {
                    likeCount++;
                    likeBtnIv.setAlpha(1.0f);
                    Toast.makeText(getContext(), "Liked review!", Toast.LENGTH_SHORT).show();
                } else {
                    likeCount--;
                    likeBtnIv.setAlpha(0.6f);
                }
                if (likeCountTv != null) {
                    likeCountTv.setText(likeCount + " likes");
                }
            });
        }

        // Setup Comments RecyclerView LayoutManager
        if (commentsRv != null) {
            commentsRv.setLayoutManager(new LinearLayoutManager(getContext()));
        }
    }
}
