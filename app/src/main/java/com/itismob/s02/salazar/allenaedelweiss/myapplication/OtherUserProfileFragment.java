package com.itismob.s02.salazar.allenaedelweiss.myapplication;

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

public class OtherUserProfileFragment extends Fragment {

    public OtherUserProfileFragment() {
        super(R.layout.fragment_otheruser_profile);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // handle back button
        ImageView btnBack = view.findViewById(R.id.btnBack);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> {
                if (getActivity() != null) {
                    getActivity().getOnBackPressedDispatcher().onBackPressed();
                }
            });
        }

        // bind views
        TextView tvUsername = view.findViewById(R.id.tvUsername);
        TextView tvDisplayName = view.findViewById(R.id.tvDisplayName);
        ImageView ivProfilePic = view.findViewById(R.id.ivProfilePic);
        Button btnFollow = view.findViewById(R.id.btnFollow);

        // placeholder profile data
        String username = getArguments() != null
                ? getArguments().getString("username", "otherusername")
                : "otherusername";
        String displayName = getArguments() != null
                ? getArguments().getString("displayName", "Wormboxd")
                : "Wormboxd";

        if (tvUsername != null) tvUsername.setText("@" + username);
        if (tvDisplayName != null) tvDisplayName.setText(displayName);

        // follow button toggle
        if (btnFollow != null) {
            final boolean[] isFollowing = {false};
            btnFollow.setOnClickListener(v -> {
                isFollowing[0] = !isFollowing[0];
                if (isFollowing[0]) {
                    btnFollow.setText("Following");
                    btnFollow.setBackgroundTintList(
                            android.content.res.ColorStateList.valueOf(
                                    android.graphics.Color.parseColor("#5B7C99")));
                    Toast.makeText(getContext(), "Following " + username,
                            Toast.LENGTH_SHORT).show();
                } else {
                    btnFollow.setText("Follow");
                    btnFollow.setBackgroundTintList(
                            android.content.res.ColorStateList.valueOf(
                                    android.graphics.Color.parseColor("#243B53")));
                    Toast.makeText(getContext(), "Unfollowed " + username,
                            Toast.LENGTH_SHORT).show();
                }
            });
        }

        // recent activity recyclerView
        RecyclerView recentActivityRv = view.findViewById(R.id.recentActivityRecyclerView);
        if (recentActivityRv != null) {
            recentActivityRv.setLayoutManager(
                    new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            recentActivityRv.setAdapter(
                    new MyBookRecyclerViewAdapter(
                            com.itismob.s02.salazar.allenaedelweiss.myapplication
                                    .homechildfragment.homebookfragment.placeholder
                                    .PlaceholderContent.ITEMS));
        }

        // recent reviews recyclerView (comments)
        RecyclerView commentsRv = view.findViewById(R.id.commentsRv);
        if (commentsRv != null) {
            commentsRv.setLayoutManager(new LinearLayoutManager(getContext()));
            commentsRv.setAdapter(
                    new MyReviewRecyclerViewAdapter(
                            com.itismob.s02.salazar.allenaedelweiss.myapplication
                                    .homechildfragment.homereviewfragment.placeholder
                                    .PlaceholderContent.ITEMS));
        }
    }
}