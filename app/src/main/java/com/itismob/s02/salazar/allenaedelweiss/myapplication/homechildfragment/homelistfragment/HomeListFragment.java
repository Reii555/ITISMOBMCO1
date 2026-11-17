package com.itismob.s02.salazar.allenaedelweiss.myapplication.homechildfragment.homelistfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.itismob.s02.salazar.allenaedelweiss.myapplication.ListDataGenerator;
import com.itismob.s02.salazar.allenaedelweiss.myapplication.R;
import com.itismob.s02.salazar.allenaedelweiss.myapplication.UserList;

import java.util.List;

public class HomeListFragment extends Fragment {

    public HomeListFragment() {
        // Required empty public constructor
    }

    public static HomeListFragment newInstance() {
        return new HomeListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvPopular = view.findViewById(R.id.rvPopular);
        RecyclerView rvFromFriends = view.findViewById(R.id.rvFromFriends);

        List<UserList> popularLists = ListDataGenerator.getPopularLists();
        List<UserList> friendsLists = ListDataGenerator.getFriendsLists();

        if (rvPopular != null) {
            rvPopular.setLayoutManager(new LinearLayoutManager(getContext()));
            rvPopular.setNestedScrollingEnabled(false);
            rvPopular.setAdapter(new MyListRecyclerViewAdapter(popularLists));
        }

        if (rvFromFriends != null) {
            rvFromFriends.setLayoutManager(new LinearLayoutManager(getContext()));
            rvFromFriends.setNestedScrollingEnabled(false);
            rvFromFriends.setAdapter(new MyListRecyclerViewAdapter(friendsLists));
        }
    }
}
