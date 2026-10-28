package com.itismob.s02.salazar.allenaedelweiss.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RateFragment extends Fragment {

    private RatingBar ratingBar;
    private EditText reviewEt;

    public RateFragment() {
        super(R.layout.fragment_rate);
    }

    public static RateFragment newInstance(Book book) {
        RateFragment fragment = new RateFragment();
        if (book != null) {
            Bundle args = new Bundle();
            args.putString("book_title", book.getTitle());
            args.putString("book_author", book.getAuthor());
            args.putString("book_summary", book.getDescription());
            args.putInt("book_cover_res", book.getImageResId());
            args.putString("book_publish_date", "Published " + book.getReleaseYear());
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button backBtn = view.findViewById(R.id.backBtn);
        TextView bookTitleTv = view.findViewById(R.id.bookTitleTv);
        TextView bookAuthorTv = view.findViewById(R.id.bookAuthorTv);
        TextView bookSummaryTv = view.findViewById(R.id.bookSummaryTv);
        TextView publishDateTv = view.findViewById(R.id.publishDateTv);
        ImageView bookCoverIv = view.findViewById(R.id.bookCoverIv);

        ratingBar = view.findViewById(R.id.ratingBar);
        reviewEt = view.findViewById(R.id.reviewEt);
        Button cancelReviewBtn = view.findViewById(R.id.cancelReviewBtn);
        Button submitReviewBtn = view.findViewById(R.id.submitReviewBtn);

        // Populate dynamic book details if passed via arguments
        if (getArguments() != null) {
            String title = getArguments().getString("book_title");
            String author = getArguments().getString("book_author");
            String summary = getArguments().getString("book_summary");
            String publishDate = getArguments().getString("book_publish_date");
            int coverRes = getArguments().getInt("book_cover_res", 0);

            if (title != null && bookTitleTv != null) bookTitleTv.setText(title);
            if (author != null && bookAuthorTv != null) bookAuthorTv.setText(author);
            if (summary != null && bookSummaryTv != null) bookSummaryTv.setText(summary);
            if (publishDate != null && publishDateTv != null) publishDateTv.setText(publishDate);
            if (coverRes != 0 && bookCoverIv != null) bookCoverIv.setImageResource(coverRes);
        }

        // Back button navigation
        if (backBtn != null) {
            backBtn.setOnClickListener(v -> navigateBack());
        }

        // Cancel button navigation
        if (cancelReviewBtn != null) {
            cancelReviewBtn.setOnClickListener(v -> navigateBack());
        }

        // Submit review button click listener
        if (submitReviewBtn != null) {
            submitReviewBtn.setOnClickListener(v -> {
                float rating = ratingBar != null ? ratingBar.getRating() : 0;
                String reviewText = reviewEt != null ? reviewEt.getText().toString().trim() : "";

                if (rating == 0) {
                    Toast.makeText(getContext(), "Please select a star rating", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(getContext(), "Review submitted successfully!", Toast.LENGTH_SHORT).show();
                navigateBack();
            });
        }
    }

    private void navigateBack() {
        if (getParentFragmentManager().getBackStackEntryCount() > 0) {
            getParentFragmentManager().popBackStack();
        } else if (getActivity() != null) {
            getActivity().getOnBackPressedDispatcher().onBackPressed();
        }
    }
}
