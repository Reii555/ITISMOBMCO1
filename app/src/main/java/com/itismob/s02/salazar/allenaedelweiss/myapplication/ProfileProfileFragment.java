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

        // LOGOUT
        Button logoutBtn = view.findViewById(R.id.logoutBtn);

        logoutBtn.setOnClickListener(v -> {

            // sign out
            FirebaseAuth.getInstance().signOut();

            Toast.makeText(requireContext(),
                    "Logged out successfully!",
                    Toast.LENGTH_SHORT).show();

            // return to login
            Intent intent = new Intent(
                    requireActivity(),
                    MainActivity.class
            );

            intent.setFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK
            );

            startActivity(intent);

            requireActivity().finish();
        });
    }

}