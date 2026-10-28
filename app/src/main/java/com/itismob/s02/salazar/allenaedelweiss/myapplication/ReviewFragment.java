package com.itismob.s02.salazar.allenaedelweiss.myapplication;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ReviewFragment extends Fragment {

    private Button backBtn;
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

        // Back button navigation
        if (backBtn != null) {
            backBtn.setOnClickListener(v -> {
                if (getParentFragmentManager().getBackStackEntryCount() > 0) {
                    getParentFragmentManager().popBackStack();
                } else if (getActivity() != null) {
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
