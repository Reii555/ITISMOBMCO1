package com.itismob.s02.salazar.allenaedelweiss.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
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

        // CONT READING
        continueReading.setLayoutManager(new GridLayoutManager(requireContext(), 4));
        continueReading.setAdapter(new BookAdapter(BookDataGenerator.getContinueReadingBooks()));

        // RECENT REVS
        recentReviews.setLayoutManager(new GridLayoutManager(requireContext(), 1));
        recentReviews.setAdapter(new ReviewAdapter(ReviewDataGenerator.getRecentReviews()));

    }
}
