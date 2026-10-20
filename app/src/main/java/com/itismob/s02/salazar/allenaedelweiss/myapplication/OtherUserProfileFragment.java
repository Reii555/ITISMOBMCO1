package com.itismob.s02.salazar.allenaedelweiss.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation; // If using Navigation Component

public class OtherUserProfileFragment extends Fragment {

    public OtherUserProfileFragment() {
        super(R.layout.fragment_other_user_profile);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 1. Handle Back Button
        ImageView btnBack = view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If you are using Android Navigation Component, uncomment the line below:
                // Navigation.findNavController(v).popBackStack();

                // Otherwise, handle onBackPressed:
                if (getActivity() != null) {
                    getActivity().onBackPressed();
                }
            }
        });

        // 2. Handle Bottom Navigation Clicks (Example setup)
        // Assuming you want to interact with the bottom icons, you'd assign IDs to them in XML.
        // For example, if you add android:id="@+id/navSearch" to the search icon:

        /*
        view.findViewById(R.id.navSearch).setOnClickListener(v -> {
            // Navigate to Search
            Toast.makeText(getContext(), "Go to Search", Toast.LENGTH_SHORT).show();
        });
        
        view.findViewById(R.id.navProfile).setOnClickListener(v -> {
             // Navigate to own profile
            Toast.makeText(getContext(), "Go to My Profile", Toast.LENGTH_SHORT).show();
        });
        */

        // 3. Example of clicking the "Following" Button
        // Assuming you add an ID to the Following TextView: android:id="@+id/btnFollowing"
        /*
        TextView btnFollowing = view.findViewById(R.id.btnFollowing);
        btnFollowing.setOnClickListener(v -> {
            // Toggle follow state
            if (btnFollowing.getText().toString().equals("Following")) {
                btnFollowing.setText("Follow");
                // Change background/colors here
            } else {
                btnFollowing.setText("Following");
                // Change background/colors here
            }
        });
        */
    }
}