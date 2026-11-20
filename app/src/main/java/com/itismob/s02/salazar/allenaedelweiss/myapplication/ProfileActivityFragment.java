package com.itismob.s02.salazar.allenaedelweiss.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivityFragment extends Fragment {

    public ProfileActivityFragment() {
        // Required empty constructor
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        return inflater.inflate(
                R.layout.fragment_profile_activity,
                container,
                false
        );
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView continueReading = view.findViewById(R.id.continueReadingActivityRecyclerView);

        RecyclerView recentReviews = view.findViewById(R.id.recentReviewsActivityRecyclerView);

        // CONTINUE READING
        continueReading.setNestedScrollingEnabled(false);
        continueReading.setLayoutManager(new GridLayoutManager(requireContext(), 4));
        continueReading.setAdapter(
                new BookAdapter(
                        BookDataGenerator.getContinueReadingBooks(),
                        book -> openRateFragment(book)
                )
        );

        // RECENT REVIEWS
        recentReviews.setNestedScrollingEnabled(false);
        recentReviews.setLayoutManager(new LinearLayoutManager(requireContext()));

        recentReviews.setAdapter(new ReviewAdapter(ReviewDataGenerator.getRecentReviews()));

        // EXPAND RECYCLERVIEW TO SHOW ALL REVIEWS
        recentReviews.post(() -> {

            int contentHeight =
                    recentReviews.computeVerticalScrollRange();

            ViewGroup.LayoutParams params =
                    recentReviews.getLayoutParams();

            params.height = contentHeight;

            recentReviews.setLayoutParams(params);

        });
    }


    private void openRateFragment(Book book) {

        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(
                        R.id.homeFragment,
                        RateFragment.newInstance(book)
                )
                .addToBackStack(null)
                .commit();
    }

}
