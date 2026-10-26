package com.itismob.s02.salazar.allenaedelweiss.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OtherUserProfileFragment extends Fragment {

    // recyclerview adapters
    private RecentActivityAdapter activityAdapter;
    private RecentReviewsAdapter reviewsAdapter;

    // empty lists to hold data (will be populated later)
    private final List<Book> recentBooks = new ArrayList<>();
    private final List<Review> recentReviews = new ArrayList<>();

    public OtherUserProfileFragment() {
        super(R.layout.fragment_otheruser_profile);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // back button
        ImageButton btnBack = view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            if (getActivity() instanceof HomeActivity) {
                ((HomeActivity) getActivity()).navigateFragment(new HomeFragment());
            } else if (getActivity() != null) {
                getActivity().onBackPressed();
            }
        });

        // follow toggle
        Button btnFollow = view.findViewById(R.id.btnFollow);
        btnFollow.setOnClickListener(v -> {
            if (btnFollow.getText().toString().equalsIgnoreCase("Follow")) {
                btnFollow.setText("Following");
                btnFollow.setBackgroundTintList(
                        android.content.res.ColorStateList.valueOf(
                                android.graphics.Color.parseColor("#5B7C99")));
                Toast.makeText(getContext(), "You are now following this user", Toast.LENGTH_SHORT).show();
            } else {
                btnFollow.setText("Follow");
                btnFollow.setBackgroundTintList(
                        android.content.res.ColorStateList.valueOf(
                                android.graphics.Color.parseColor("#243B53")));
                Toast.makeText(getContext(), "Unfollowed", Toast.LENGTH_SHORT).show();
            }
        });

        // setup recyclerviews
        RecyclerView recentActivityRv = view.findViewById(R.id.recentActivityRecyclerView);
        recentActivityRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        activityAdapter = new RecentActivityAdapter(recentBooks);
        recentActivityRv.setAdapter(activityAdapter);

        RecyclerView commentsRv = view.findViewById(R.id.commentsRv);
        commentsRv.setLayoutManager(new LinearLayoutManager(getContext()));
        reviewsAdapter = new RecentReviewsAdapter(recentReviews);
        commentsRv.setAdapter(reviewsAdapter);
    }

    // =========================================================
    // data models
    // =========================================================
    public static class Book {
        public String title;
        public String author;
        public String rating;

        public Book(String title, String author, String rating) {
            this.title = title;
            this.author = author;
            this.rating = rating;
        }
    }

    public static class Review {
        public String username;
        public float rating;
        public String comment;
        public String likeCount;

        public Review(String username, float rating, String comment, String likeCount) {
            this.username = username;
            this.rating = rating;
            this.comment = comment;
            this.likeCount = likeCount;
        }
    }

    // =========================================================
    // adapters
    // =========================================================
    static class RecentActivityAdapter extends RecyclerView.Adapter<RecentActivityAdapter.VH> {
        private final List<Book> data;

        RecentActivityAdapter(List<Book> data) {
            this.data = data;
        }

        static class VH extends RecyclerView.ViewHolder {
            ImageView cover;
            TextView title, author, rating;

            VH(@NonNull View v) {
                super(v);
                cover = v.findViewById(R.id.bookCoverIV);
                title = v.findViewById(R.id.bookTitleTV);
                author = v.findViewById(R.id.bookAuthorTV);
                /* rating = v.findViewById(R.id.bookRatingTV);
                * wait ko si rei */
            }
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_book, parent, false);
            return new VH(v);
        }

        @Override
        public void onBindViewHolder(@NonNull VH h, int pos) {
            Book item = data.get(pos);
            h.title.setText(item.title);
            h.author.setText(item.author);
            h.rating.setText(item.rating);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    // recent reviews adapter
    static class RecentReviewsAdapter extends RecyclerView.Adapter<RecentReviewsAdapter.VH> {
        private final List<Review> data;

        RecentReviewsAdapter(List<Review> data) {
            this.data = data;
        }

        static class VH extends RecyclerView.ViewHolder {
            TextView username, review, likeCount;
            android.widget.RatingBar rating;

            VH(@NonNull View v) {
                super(v);
                username = v.findViewById(R.id.commentUsernameTv);
                review = v.findViewById(R.id.commentReviewTv);
                likeCount = v.findViewById(R.id.likeCountCommentTv);
                rating = v.findViewById(R.id.commentRating);
            }
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_comment_otheruser, parent, false);
            return new VH(v);
        }

        @Override
        public void onBindViewHolder(@NonNull VH h, int pos) {
            Review item = data.get(pos);
            h.username.setText(item.username);
            h.review.setText(item.comment);
            h.likeCount.setText(item.likeCount);
            h.rating.setRating(item.rating);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }
}