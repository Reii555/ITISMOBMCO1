package com.itismob.s02.salazar.allenaedelweiss.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ProfileFragment extends Fragment {

    Button profileBtn, myActivityBtn;
    FragmentContainerView profileChildFragment;
    View profileUnderline, myActivityUnderline;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        return inflater.inflate(
                R.layout.fragment_profile,
                container,
                false
        );
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        profileBtn = view.findViewById(R.id.profileBtn);
        myActivityBtn = view.findViewById(R.id.myActivityBtn);

        profileUnderline = view.findViewById(R.id.profileUnderline);
        myActivityUnderline = view.findViewById(R.id.myActivityUnderline);

        if (savedInstanceState == null) {
            showFragment(new ProfileProfileFragment(), true);
        }

        profileBtn.setOnClickListener(v ->
                showFragment(new ProfileProfileFragment(), true));

        myActivityBtn.setOnClickListener(v ->
                showFragment(new ProfileActivityFragment(), false));
    }

    private void showFragment(Fragment fragment, boolean isProfile) {
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.profileChildFragment, fragment)
                .commit();

        if (isProfile) {
            profileUnderline.setVisibility(View.VISIBLE);
            myActivityUnderline.setVisibility(View.INVISIBLE);
        } else {
            profileUnderline.setVisibility(View.INVISIBLE);
            myActivityUnderline.setVisibility(View.VISIBLE);
        }
    }

}