package com.itismob.s02.salazar.allenaedelweiss.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchFragment extends Fragment {

    private EditText searchInputET;
    private LinearLayout optionReleaseDate;
    private LinearLayout optionGenre;
    private LinearLayout optionMostPopular;
    private LinearLayout optionHighestRated;
    private LinearLayout browseSectionContainer;
    private RecyclerView rvSearchResults;
    private SearchAdapter searchAdapter;

    private final List<Book> allBooks = new ArrayList<>();

    private boolean isForRating = false;

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance(boolean isForRating) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putBoolean("is_for_rating", isForRating);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            isForRating = getArguments().getBoolean("is_for_rating", false);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        allBooks.clear();
        allBooks.addAll(BookDataGenerator.getRecentActivityBooks());
        allBooks.addAll(BookDataGenerator.getContinueReadingBooks());

        searchInputET = view.findViewById(R.id.searchInputET);
        optionReleaseDate = view.findViewById(R.id.optionReleaseDate);
        optionGenre = view.findViewById(R.id.optionGenre);
        optionMostPopular = view.findViewById(R.id.optionMostPopular);
        optionHighestRated = view.findViewById(R.id.optionHighestRated);
        browseSectionContainer = view.findViewById(R.id.sectionContainerBrowse);
        rvSearchResults = view.findViewById(R.id.searchResultsRV);

        searchAdapter = new SearchAdapter(new ArrayList<>(), book -> {
            if (isForRating) {
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.homeFragment, RateFragment.newInstance(book))
                        .addToBackStack(null)
                        .commit();
            } else {
                Bundle bundle = new Bundle();

                bundle.putString("title", book.getTitle());
                bundle.putString("author", book.getAuthor());
                bundle.putString("description", book.getDescription());
                bundle.putString("genre", book.getGenre());

                bundle.putInt("releaseYear", book.getReleaseYear());
                bundle.putInt("popularity", book.getPopularity());

                bundle.putFloat("rating", book.getRating());
                bundle.putInt("imageResId", book.getImageResId());

                BookInformationFragment fragment = new BookInformationFragment();
                fragment.setArguments(bundle);

                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.homeFragment, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        rvSearchResults.setLayoutManager(new LinearLayoutManager(getContext()));
        rvSearchResults.setAdapter(searchAdapter);

        searchInputET.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                String query = searchInputET.getText().toString().trim();
                performSearch(query);
                return true;
            }
            return false;
        });

        searchInputET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterResults(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        optionReleaseDate.setOnClickListener(v -> handleBrowseCategory("Release date"));
        optionGenre.setOnClickListener(v -> handleBrowseCategory("Genre"));
        optionMostPopular.setOnClickListener(v -> handleBrowseCategory("Most popular"));
        optionHighestRated.setOnClickListener(v -> handleBrowseCategory("Highest rated"));
    }

    private void performSearch(String query) {
        if (query.isEmpty()) {
            Toast.makeText(getContext(), "Please enter a search term", Toast.LENGTH_SHORT).show();
            return;
        }
        filterResults(query);
    }

    private void filterResults(String query) {
        if (query.trim().isEmpty()) {
            browseSectionContainer.setVisibility(View.VISIBLE);
            rvSearchResults.setVisibility(View.GONE);
        } else {
            browseSectionContainer.setVisibility(View.GONE);
            rvSearchResults.setVisibility(View.VISIBLE);

            List<Book> matchingBooks = new ArrayList<>();
            for (Book book : allBooks) {
                if (book.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(query.toLowerCase()) ||
                    book.getDescription().toLowerCase().contains(query.toLowerCase()) ||
                    book.getGenre().toLowerCase().contains(query.toLowerCase())) {
                    matchingBooks.add(book);
                }
            }
            searchAdapter.updateData(matchingBooks);
        }
    }

    private void handleBrowseCategory(String category) {
        switch (category) {
            case "Release date":
                showSortDialog("Select Release Order",
                        new String[]{"Newest release first", "Oldest release first"},
                        (dialog, which) -> {
                            List<Book> sortedBooks = new ArrayList<>(allBooks);
                            if (which == 0) { // Newest
                                Collections.sort(sortedBooks, (b1, b2) -> Integer.compare(b2.getReleaseYear(), b1.getReleaseYear()));
                            } else { // Oldest
                                Collections.sort(sortedBooks, (b1, b2) -> Integer.compare(b1.getReleaseYear(), b2.getReleaseYear()));
                            }
                            displayCategoryResults(sortedBooks);
                        });
                break;

            case "Genre":
                showSortDialog("Sort Genres",
                        new String[]{"Alphabetical (A - Z)", "Reverse Alphabetical (Z - A)"},
                        (dialog, which) -> {
                            List<Book> sortedBooks = new ArrayList<>(allBooks);
                            if (which == 0) { // A-Z
                                Collections.sort(sortedBooks, (b1, b2) -> b1.getGenre().compareToIgnoreCase(b2.getGenre()));
                            } else { // Z-A
                                Collections.sort(sortedBooks, (b1, b2) -> b2.getGenre().compareToIgnoreCase(b1.getGenre()));
                            }
                            displayCategoryResults(sortedBooks);
                        });
                break;

            case "Most popular":
                showSortDialog("Select Popularity Order",
                        new String[]{"Most popular first (Descending)", "Least popular first (Ascending)"},
                        (dialog, which) -> {
                            List<Book> sortedBooks = new ArrayList<>(allBooks);
                            if (which == 0) { // High to Low
                                Collections.sort(sortedBooks, (b1, b2) -> Integer.compare(b2.getPopularity(), b1.getPopularity()));
                            } else { // Low to High
                                Collections.sort(sortedBooks, (b1, b2) -> Integer.compare(b1.getPopularity(), b2.getPopularity()));
                            }
                            displayCategoryResults(sortedBooks);
                        });
                break;

            case "Highest rated":
                showSortDialog("Select Rating Order",
                        new String[]{"Highest rated first (Descending)", "Lowest rated first (Ascending)"},
                        (dialog, which) -> {
                            List<Book> sortedBooks = new ArrayList<>(allBooks);
                            if (which == 0) { // High to Low
                                Collections.sort(sortedBooks, (b1, b2) -> Float.compare(b2.getRating(), b1.getRating()));
                            } else { // Low to High
                                Collections.sort(sortedBooks, (b1, b2) -> Float.compare(b1.getRating(), b2.getRating()));
                            }
                            displayCategoryResults(sortedBooks);
                        });
                break;
        }
    }

    private void showSortDialog(String title, String[] options, DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(requireContext())
                .setTitle(title)
                .setItems(options, listener)
                .show();
    }

    private void displayCategoryResults(List<Book> books) {
        browseSectionContainer.setVisibility(View.GONE);
        rvSearchResults.setVisibility(View.VISIBLE);
        searchAdapter.updateData(books);
    }


}
