
package com.itismob.s02.salazar.allenaedelweiss.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
    private Button backBtn;

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
        }

        // Back button functionality
        backBtn.setOnClickListener(v -> {
            getParentFragmentManager().popBackStack();
        });
    }
}
