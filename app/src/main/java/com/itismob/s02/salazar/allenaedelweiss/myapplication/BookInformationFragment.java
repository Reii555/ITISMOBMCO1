package com.itismob.s02.salazar.allenaedelweiss.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.itismob.s02.salazar.allenaedelweiss.myapplication.homechildfragment.homereviewfragment.MyReviewRecyclerViewAdapter;

import java.util.List;
import java.util.Locale;

public class BookInformationFragment extends Fragment {

    // Book information views
    private TextView bookTitleTv;
    private TextView bookAuthorTv;
    private TextView publishDateTv;
    private TextView genreTv;
    private TextView bookSummaryTv;
    private TextView ratingTv;
    private TextView popularityTv;

    // Book cover and back button
    private ImageView bookCoverIv;
    private ImageButton backBtn;

    public BookInformationFragment() {
        super(R.layout.fragment_book_info);
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        // Initialize views
        bookTitleTv = view.findViewById(R.id.bookTitleTv);
        bookAuthorTv = view.findViewById(R.id.bookAuthorTv);
        publishDateTv = view.findViewById(R.id.publishDateTv);
        genreTv = view.findViewById(R.id.genreTv);
        bookSummaryTv = view.findViewById(R.id.bookSummaryTv);
        ratingTv = view.findViewById(R.id.ratingTv);
        popularityTv = view.findViewById(R.id.popularityTv);

        bookCoverIv = view.findViewById(R.id.bookCoverIv);
        backBtn = view.findViewById(R.id.backBtn);

        RecyclerView rvBookReviews = view.findViewById(R.id.rvBookReviews);
        TextView noReviewsTv = view.findViewById(R.id.noReviewsTv);

        // Retrieve selected book information
        Bundle args = getArguments();

        if (args != null) {

            String title = args.getString("title", "");
            String author = args.getString("author", "");
            String description = args.getString("description", "");
            String genre = args.getString("genre", "");

            int releaseYear = args.getInt("releaseYear");
            int popularity = args.getInt("popularity");
            float rating = args.getFloat("rating");
            int imageResId = args.getInt("imageResId");

            // Display book details
            bookTitleTv.setText(title);
            bookAuthorTv.setText("Author: " + author);

            publishDateTv.setText(
                    "Published: " + releaseYear);

            genreTv.setText("Genre: " + genre);

            // Display book summary
            bookSummaryTv.setText(description);

            // Display average rating
            ratingTv.setText(
                    String.format(
                            Locale.getDefault(),
                            "Average Rating: %.2f / 5",
                            rating));

            // Display popularity
            popularityTv.setText(
                    "Popularity: " + popularity);

            // Display book cover
            if (imageResId != 0) {
                bookCoverIv.setImageResource(imageResId);
            }

            // Display reviews specific to this book
            List<Review> bookReviews = ReviewDataGenerator.getReviewsForBook(title);
            if (bookReviews.isEmpty()) {
                if (noReviewsTv != null) noReviewsTv.setVisibility(View.VISIBLE);
                if (rvBookReviews != null) rvBookReviews.setVisibility(View.GONE);
            } else {
                if (noReviewsTv != null) noReviewsTv.setVisibility(View.GONE);
                if (rvBookReviews != null) {
                    rvBookReviews.setVisibility(View.VISIBLE);
                    rvBookReviews.setLayoutManager(new LinearLayoutManager(getContext()));
                    rvBookReviews.setNestedScrollingEnabled(false);
                    rvBookReviews.setAdapter(new MyReviewRecyclerViewAdapter(bookReviews, R.layout.item_book_review, review -> {
                        int containerId = getId() != 0 ? getId() : R.id.homeFragment;
                        getParentFragmentManager()
                                .beginTransaction()
                                .replace(containerId, ReviewFragment.newInstance(review))
                                .addToBackStack(null)
                                .commit();
                    }));
                }
            }
        }

        // Back button functionality
        backBtn.setOnClickListener(v -> {
            if (getParentFragmentManager().getBackStackEntryCount() > 0) {
                getParentFragmentManager().popBackStack();
            } else if (getActivity() != null) {
                getActivity().getOnBackPressedDispatcher().onBackPressed();
            }
        });
    }
}
