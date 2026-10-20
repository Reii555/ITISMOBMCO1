package com.itismob.s02.salazar.allenaedelweiss.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    private final int colorActive = Color.parseColor("#F7F8FA");
    private final int colorInactive = Color.parseColor("#5B7C99");

    private TextView tvBooks, tvReviews, tvLists;
    private View underlineBooks, underlineReviews, underlineLists;

    public HomeFragment() {
        super(R.layout.fragment_home);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // find all the TextViews
        tvBooks = view.findViewById(R.id.tvBooks);
        tvReviews = view.findViewById(R.id.tvReviews);
        tvLists = view.findViewById(R.id.tvLists);

        // find all the Underlines
        underlineBooks = view.findViewById(R.id.underlineBooks);
        underlineReviews = view.findViewById(R.id.underlineReviews);
        underlineLists = view.findViewById(R.id.underlineLists);

        // find the clickable Layout Containers
        View tabBooks = view.findViewById(R.id.tabBooks);
        View tabReviews = view.findViewById(R.id.tabReviews);
        View tabLists = view.findViewById(R.id.tabLists);

        // set click listeners
        tabBooks.setOnClickListener(v -> selectTab("Books"));
        tabReviews.setOnClickListener(v -> selectTab("Reviews"));
        tabLists.setOnClickListener(v -> selectTab("Lists"));

        // set the default selected tab on startup
        selectTab("Books");
    }

    /**
     * Updates the UI to reflect the currently selected tab.
     */
    private void selectTab(String activeTab) {
        // reset all tabs to inactive state
        tvBooks.setTextColor(colorInactive);
        tvReviews.setTextColor(colorInactive);
        tvLists.setTextColor(colorInactive);

        underlineBooks.setVisibility(View.INVISIBLE);
        underlineReviews.setVisibility(View.INVISIBLE);
        underlineLists.setVisibility(View.INVISIBLE);

        // set the selected tab to active state
        switch (activeTab) {
            case "Books":
                tvBooks.setTextColor(colorActive);
                underlineBooks.setVisibility(View.VISIBLE);
                // lad books data here
                break;

            case "Reviews":
                tvReviews.setTextColor(colorActive);
                underlineReviews.setVisibility(View.VISIBLE);
                // load reviews data here
                break;

            case "Lists":
                tvLists.setTextColor(colorActive);
                underlineLists.setVisibility(View.VISIBLE);
                // load lists data here
                break;
        }
    }
}
