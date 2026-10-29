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

    private RecentActivityAdapter activityAdapter;
    private RecentReviewsAdapter reviewsAdapter;

    private List<Book> recentBooks = new ArrayList<>();
    private List<Review> recentReviews = new ArrayList<>();

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

        // fetch data from generator
        recentBooks = BookDataGenerator.getRecentActivityBooks();

        // pass click listener to adapter
        activityAdapter = new RecentActivityAdapter(recentBooks, book -> {
            if (getActivity() instanceof HomeActivity) {
                // navigate to fragment_rate
                ((HomeActivity) getActivity()).navigateFragment(new RateFragment());
            }
        });
        recentActivityRv.setAdapter(activityAdapter);

        // recent reviews
        RecyclerView commentsRv = view.findViewById(R.id.commentsRv);
        commentsRv.setLayoutManager(new LinearLayoutManager(getContext()));

        // pass click listener to adapter
        reviewsAdapter = new RecentReviewsAdapter(recentReviews, review -> {
            if (getActivity() instanceof HomeActivity) {
                // navigate to fragment_review
                ((HomeActivity) getActivity()).navigateFragment(new ReviewFragment());
            }
        });
        commentsRv.setAdapter(reviewsAdapter);
    }

    // =========================================================
    // data models
    // =========================================================
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
    // interfaces for clicks
    // =========================================================
    public interface OnBookClickListener {
        void onBookClick(Book book);
    }

    public interface OnReviewClickListener {
        void onReviewClick(Review review);
    }

    // =========================================================
    // adapters
    // =========================================================

    // --- recent activity adapter ---
    static class RecentActivityAdapter extends RecyclerView.Adapter<RecentActivityAdapter.VH> {
        private final List<Book> data;
        private final OnBookClickListener listener;

        RecentActivityAdapter(List<Book> data, OnBookClickListener listener) {
            this.data = data;
            this.listener = listener;
        }

        static class VH extends RecyclerView.ViewHolder {
            ImageView cover;
            TextView title, author;

            VH(@NonNull View v) {
                super(v);
                cover = v.findViewById(R.id.bookCoverIV);
                title = v.findViewById(R.id.bookTitleTV);
                author = v.findViewById(R.id.bookAuthorTV);
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
            h.title.setText(item.getTitle());
            h.author.setText(item.getAuthor());
            h.cover.setImageResource(item.getImageResId());

            // handles click
            h.itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onBookClick(item);
                }
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    // --- recent reviews adapter ---
    static class RecentReviewsAdapter extends RecyclerView.Adapter<RecentReviewsAdapter.VH> {
        private final List<Review> data;
        private final OnReviewClickListener listener;

        RecentReviewsAdapter(List<Review> data, OnReviewClickListener listener) {
            this.data = data;
            this.listener = listener;
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

            // handles click
            h.itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onReviewClick(item);
                }
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }
}