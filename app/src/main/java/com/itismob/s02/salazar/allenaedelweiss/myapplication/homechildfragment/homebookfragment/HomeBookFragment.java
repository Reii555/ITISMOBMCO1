package com.itismob.s02.salazar.allenaedelweiss.myapplication.homechildfragment.homebookfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.itismob.s02.salazar.allenaedelweiss.myapplication.Book;
import com.itismob.s02.salazar.allenaedelweiss.myapplication.BookDataGenerator;
import com.itismob.s02.salazar.allenaedelweiss.myapplication.R;

import java.util.List;

public class HomeBookFragment extends Fragment {

    private RecyclerView rvPopular;
    private RecyclerView rvFromFriends;

    public HomeBookFragment() {
        // Required empty public constructor
    }

    public static HomeBookFragment newInstance() {
        return new HomeBookFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_book, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvPopular = view.findViewById(R.id.rvPopular);
        rvFromFriends = view.findViewById(R.id.rvFromFriends);

        List<Book> popularBooks = BookDataGenerator.getRecentActivityBooks();
        List<Book> friendsBooks = BookDataGenerator.getContinueReadingBooks();

        if (rvPopular != null) {
            rvPopular.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            rvPopular.setAdapter(new MyBookRecyclerViewAdapter(popularBooks, false));
        }

        if (rvFromFriends != null) {
            rvFromFriends.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            rvFromFriends.setAdapter(new MyBookRecyclerViewAdapter(friendsBooks, true));
        }
    }
}
