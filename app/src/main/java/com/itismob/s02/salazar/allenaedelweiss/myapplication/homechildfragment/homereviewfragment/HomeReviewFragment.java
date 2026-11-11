package com.itismob.s02.salazar.allenaedelweiss.myapplication.homechildfragment.homereviewfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.itismob.s02.salazar.allenaedelweiss.myapplication.R;
import com.itismob.s02.salazar.allenaedelweiss.myapplication.Review;
import com.itismob.s02.salazar.allenaedelweiss.myapplication.ReviewDataGenerator;

import java.util.List;

public class HomeReviewFragment extends Fragment {

    public HomeReviewFragment() {
        // Required empty public constructor
    }

    public static HomeReviewFragment newInstance() {
        return new HomeReviewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_review, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvFriends = view.findViewById(R.id.homeReviewListFriendsRv);
        RecyclerView rvPopular = view.findViewById(R.id.homeReviewListPopularRv);

        List<Review> friendReviews = ReviewDataGenerator.getFriendsReviews();
        List<Review> popularReviews = ReviewDataGenerator.getPopularReviews();

        if (rvFriends != null) {
            rvFriends.setLayoutManager(new LinearLayoutManager(getContext()));
            rvFriends.setNestedScrollingEnabled(false);
            rvFriends.setAdapter(new MyReviewRecyclerViewAdapter(friendReviews, R.layout.item_review_newfromfriends));
        }

        if (rvPopular != null) {
            rvPopular.setLayoutManager(new LinearLayoutManager(getContext()));
            rvPopular.setNestedScrollingEnabled(false);
            rvPopular.setAdapter(new MyReviewRecyclerViewAdapter(popularReviews, R.layout.item_review_popular));
        }

        NestedScrollView scrollView = view.findViewById(R.id.homeReviewScrollView);
        View popularHeader = view.findViewById(R.id.popularThisWeekHeaderLayout);
        TextView stickyHeaderTv = view.findViewById(R.id.stickyHeaderTv);

        if (scrollView != null && popularHeader != null && stickyHeaderTv != null) {
            scrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                int popularTop = popularHeader.getTop();
                if (scrollY >= popularTop) {
                    stickyHeaderTv.setText("Popular this week");
                } else {
                    stickyHeaderTv.setText("New from friends");
                }
            });
        }
    }
}
