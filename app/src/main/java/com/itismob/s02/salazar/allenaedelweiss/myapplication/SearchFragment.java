package com.itismob.s02.salazar.allenaedelweiss.myapplication;

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
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchFragment extends Fragment {

    private EditText searchInputET;
    private LinearLayout optionReleaseDate;
    private LinearLayout optionGenre;
    private LinearLayout optionMostPopular;
    private LinearLayout optionHighestRated;
    private List<Book> allBooks = new ArrayList<>();

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        allBooks.addAll(BookDataGenerator.getRecentActivityBooks());
        allBooks.addAll(BookDataGenerator.getContinueReadingBooks());

        searchInputET = view.findViewById(R.id.searchInputET);
        optionReleaseDate = view.findViewById(R.id.optionReleaseDate);
        optionGenre = view.findViewById(R.id.optionGenre);
        optionMostPopular = view.findViewById(R.id.optionMostPopular);
        optionHighestRated = view.findViewById(R.id.optionHighestRated);

        searchInputET.setOnEditorActionListener((v, actionId, event) -> {
            if(actionId == EditorInfo.IME_ACTION_SEARCH) {
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

    private void performSearch(String query){
        if(query.isEmpty()){
            Toast.makeText(getContext(), "Please enter a search term", Toast.LENGTH_SHORT).show();
            return;
        }

        List<Book> searchResults = new ArrayList<>();

        for(Book book : allBooks){
            if(book.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                book.getAuthor().toLowerCase().contains(query.toLowerCase()) ||
                book.getDescription().toLowerCase().contains(query.toLowerCase())){
                searchResults.add(book);
            }
        }

        if(searchResults.isEmpty()){
            Toast.makeText(getContext(), "No results found for '" + query + "'", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Found " + searchResults.size() + " results for '" + query + "'", Toast.LENGTH_SHORT).show();
        }
    }

    private void filterResults(String query){
        if(query.trim().isEmpty()){
            return;
        }

        List<Book> filteredBooks = new ArrayList<>();
        for(Book book : allBooks){
            if(book.getTitle().toLowerCase().contains(query.toLowerCase()) ||
            book.getAuthor().toLowerCase().contains(query.toLowerCase())){
                filteredBooks.add(book);
            }
        }
    }

    private void handleBrowseCategory(String category){
        Toast.makeText(getContext(), "Browsing by: " + category, Toast.LENGTH_SHORT).show();

        List<Book> categoryBooks = new ArrayList<>(allBooks);
        switch (category){
            case "Release date":
                Collections.sort(categoryBooks, (b1, b2) -> Integer.compare(b2.getReleaseYear(), b1.getReleaseYear()));
                break;
            case "Genre":
                Collections.sort(categoryBooks, (b1, b2) -> b1.getGenre().compareToIgnoreCase(b2.getGenre()));
                break;
            case "Most popular":
                Collections.sort(categoryBooks, (b1, b2) -> Integer.compare(b2.getPopularity(), b1.getPopularity()));
                break;
            case "Highest rated":
                Collections.sort(categoryBooks, (b1, b2) -> Float.compare(b2.getRating(), b1.getRating()));
                break;
        }
    }
}