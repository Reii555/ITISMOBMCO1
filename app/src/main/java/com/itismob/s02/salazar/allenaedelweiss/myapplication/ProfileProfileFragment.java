package com.itismob.s02.salazar.allenaedelweiss.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProfileProfileFragment extends Fragment {

    public ProfileProfileFragment() {
        // Required empty constructor
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        return inflater.inflate(
                R.layout.fragment_profile_profile,
                container,
                false
        );
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView continueReading =
                view.findViewById(R.id.recentActivityRecyclerView);

        continueReading.setLayoutManager(
                new GridLayoutManager(requireContext(), 4)
        );

        continueReading.setAdapter(
                new BookAdapter(BookDataGenerator.getRecentActivityBooks())
        );
    }

}