
package com.itismob.s02.salazar.allenaedelweiss.myapplication.homechildfragment.homebookfragment;

import com.itismob.s02.salazar.allenaedelweiss.myapplication.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.GestureDetector;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.itismob.s02.salazar.allenaedelweiss.myapplication.Book;
import com.itismob.s02.salazar.allenaedelweiss.myapplication.BookDataGenerator;
import com.itismob.s02.salazar.allenaedelweiss.myapplication.BookInformationFragment;
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
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        return inflater.inflate(
                R.layout.fragment_home_book,
                container,
                false);
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        rvPopular = view.findViewById(R.id.rvPopular);
        rvFromFriends = view.findViewById(R.id.rvFromFriends);

        List<Book> popularBooks =
                BookDataGenerator.getRecentActivityBooks();

        List<Book> friendsBooks =
                BookDataGenerator.getContinueReadingBooks();

        if (rvPopular != null) {

            rvPopular.setLayoutManager(
                    new LinearLayoutManager(
                            getContext(),
                            LinearLayoutManager.HORIZONTAL,
                            false));

            rvPopular.setAdapter(
                    new MyBookRecyclerViewAdapter(
                            popularBooks, false));

            setupBookClickListener(rvPopular, popularBooks);
        }

        if (rvFromFriends != null) {

            rvFromFriends.setLayoutManager(
                    new LinearLayoutManager(
                            getContext(),
                            LinearLayoutManager.HORIZONTAL,
                            false));

            rvFromFriends.setAdapter(
                    new MyBookRecyclerViewAdapter(
                            friendsBooks, true));

            setupBookClickListener(rvFromFriends, friendsBooks);
        }
    }

    private void setupBookClickListener(
            RecyclerView recyclerView,
            List<Book> books) {

        GestureDetector gestureDetector =
                new GestureDetector(
                        requireContext(),
                        new GestureDetector.SimpleOnGestureListener() {

                            @Override
                            public boolean onSingleTapUp(
                                    MotionEvent e) {
                                return true;
                            }
                        });

        recyclerView.addOnItemTouchListener(
                new RecyclerView.SimpleOnItemTouchListener() {

                    @Override
                    public boolean onInterceptTouchEvent(
                            @NonNull RecyclerView rv,
                            @NonNull MotionEvent e) {

                        View child = rv.findChildViewUnder(
                                e.getX(), e.getY());

                        if (child != null &&
                                gestureDetector.onTouchEvent(e)) {

                            int position =
                                    rv.getChildAdapterPosition(child);

                            if (position != RecyclerView.NO_POSITION) {
                                openBookInformation(
                                        books.get(position));
                            }

                            return true;
                        }

                        return false;
                    }
                });
    }

    private void openBookInformation(Book book) {

        Bundle bundle = new Bundle();

        // Pass book information individually
        bundle.putString("title", book.getTitle());
        bundle.putString("author", book.getAuthor());
        bundle.putString("description", book.getDescription());
        bundle.putString("genre", book.getGenre());
        bundle.putInt("releaseYear", book.getReleaseYear());
        bundle.putInt("popularity", book.getPopularity());
        bundle.putFloat("rating", book.getRating());
        bundle.putInt("imageResId", book.getImageResId());

        BookInformationFragment fragment =
                new BookInformationFragment();

        fragment.setArguments(bundle);

        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.homeChildFragment, fragment)
                .addToBackStack(null)
                .commit();
    }
}
