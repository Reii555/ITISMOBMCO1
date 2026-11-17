package com.itismob.s02.salazar.allenaedelweiss.myapplication;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;
import com.itismob.s02.salazar.allenaedelweiss.myapplication.homechildfragment.homebookfragment.MyBookRecyclerViewAdapter;

public class ListDetailFragment extends Fragment {

    public ListDetailFragment() {
        // Required empty public constructor
    }

    public static ListDetailFragment newInstance(UserList userList) {
        ListDetailFragment fragment = new ListDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable("userList", userList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        UserList userList = null;
        if (getArguments() != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                userList = getArguments().getSerializable("userList", UserList.class);
            } else {
                userList = (UserList) getArguments().getSerializable("userList");
            }
        }

        if (userList == null) {
            return;
        }

        ImageView ivBackdrop = view.findViewById(R.id.ivBackdrop);
        ShapeableImageView ivAuthorAvatar = view.findViewById(R.id.ivAuthorAvatar);
        TextView tvAuthorName = view.findViewById(R.id.tvAuthorName);
        TextView tvTitle = view.findViewById(R.id.tvListDetailTitle);
        TextView tvDescription = view.findViewById(R.id.tvListDetailDescription);
        TextView tvCredit = view.findViewById(R.id.tvListDetailCredit);
        TextView tvLikes = view.findViewById(R.id.tvListDetailLikes);
        LinearLayout llTagsContainer = view.findViewById(R.id.llTagsContainer);
        RecyclerView rvPosters = view.findViewById(R.id.rvListPosters);

        // Populate header & author details
        if (tvTitle != null) tvTitle.setText(userList.getTitle());
        if (tvAuthorName != null) tvAuthorName.setText(userList.getUsername());
        if (tvDescription != null) tvDescription.setText(userList.getDescription());
        if (tvCredit != null) tvCredit.setText(userList.getCredit());
        if (tvLikes != null) tvLikes.setText("♥  " + userList.getLikes() + " likes");

        if (ivAuthorAvatar != null) {
            if (userList.getAvatarResId() != 0) {
                ivAuthorAvatar.setImageResource(userList.getAvatarResId());
            } else {
                ivAuthorAvatar.setImageResource(R.drawable.otherpfp);
            }
        }

        // Set backdrop image using the first book cover
        if (ivBackdrop != null && userList.getBooks() != null && !userList.getBooks().isEmpty()) {
            ivBackdrop.setImageResource(userList.getBooks().get(0).getImageResId());
        }

        // Populate tags dynamically
        if (llTagsContainer != null && userList.getTags() != null) {
            llTagsContainer.removeAllViews();
            for (String tag : userList.getTags()) {
                MaterialCardView card = new MaterialCardView(requireContext());
                LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
                cardParams.setMargins(0, 0, 16, 0);
                card.setLayoutParams(cardParams);
                card.setCardBackgroundColor(0xFF2C3440);
                card.setRadius(8f);
                card.setStrokeWidth(0);

                TextView tvTag = new TextView(requireContext());
                tvTag.setText(tag);
                tvTag.setTextColor(0xFF99AABB);
                tvTag.setTextSize(12f);
                tvTag.setPadding(20, 10, 20, 10);

                card.addView(tvTag);
                llTagsContainer.addView(card);
            }
        }

        // Populate 3-column posters grid
        if (rvPosters != null && userList.getBooks() != null) {
            rvPosters.setLayoutManager(new GridLayoutManager(requireContext(), 3));
            rvPosters.setNestedScrollingEnabled(false);
            rvPosters.setAdapter(new MyBookRecyclerViewAdapter(
                    userList.getBooks(),
                    false,
                    this::openBookInformation
            ));
        }
    }

    private void openBookInformation(Book book) {
        Bundle bundle = new Bundle();
        bundle.putString("title", book.getTitle());
        bundle.putString("author", book.getAuthor());
        bundle.putString("description", book.getDescription());
        bundle.putString("genre", book.getGenre());
        bundle.putInt("releaseYear", book.getReleaseYear());
        bundle.putInt("popularity", book.getPopularity());
        bundle.putFloat("rating", book.getRating());
        bundle.putInt("imageResId", book.getImageResId());

        BookInformationFragment fragment = new BookInformationFragment();
        fragment.setArguments(bundle);

        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.homeChildFragment, fragment)
                .addToBackStack(null)
                .commit();
    }
}
