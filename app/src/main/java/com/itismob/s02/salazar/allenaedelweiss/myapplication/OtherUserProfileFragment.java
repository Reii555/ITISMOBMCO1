package com.itismob.s02.salazar.allenaedelweiss.myapplication;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
                        ColorStateList.valueOf(
                                Color.parseColor("#5B7C99")));
                Toast.makeText(getContext(), "You are now following this user", Toast.LENGTH_SHORT).show();
            } else {
                btnFollow.setText("Follow");
                btnFollow.setBackgroundTintList(
                        ColorStateList.valueOf(
                                Color.parseColor("#243B53")));
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

                ((HomeActivity) getActivity()).navigateFragment(fragment, true);
            }
        });
        recentActivityRv.setAdapter(activityAdapter);

        // recent reviews
        RecyclerView commentsRv = view.findViewById(R.id.commentsRv);
        if (commentsRv != null) {
            commentsRv.setLayoutManager(new LinearLayoutManager(getContext()));
            commentsRv.setNestedScrollingEnabled(false);

            List<com.itismob.s02.salazar.allenaedelweiss.myapplication.Review> friendsReviews = ReviewDataGenerator.getFriendsReviews();
            recentReviews.clear();
            for (com.itismob.s02.salazar.allenaedelweiss.myapplication.Review r : friendsReviews) {
                recentReviews.add(new Review(
                        "@" + (r.getFriendName() != null ? r.getFriendName() : "deartheodosia"),
                        r.getRating(),
                        r.getContent(),
                        String.format("%,d likes", r.getLikes())
                ));
            }

            // pass click listener to adapter
            reviewsAdapter = new RecentReviewsAdapter(recentReviews, review -> {
                if (getActivity() instanceof HomeActivity) {
                    int index = recentReviews.indexOf(review);
                    com.itismob.s02.salazar.allenaedelweiss.myapplication.Review targetReview =
                            (index >= 0 && index < friendsReviews.size()) ? friendsReviews.get(index) : null;
                    if (targetReview != null) {
                        ((HomeActivity) getActivity()).navigateFragment(ReviewFragment.newInstance(targetReview), true);
                    } else {
                        ((HomeActivity) getActivity()).navigateFragment(new ReviewFragment(), true);
                    }
                }
            });
            commentsRv.setAdapter(reviewsAdapter);
        }
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
            RatingBar rating;

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