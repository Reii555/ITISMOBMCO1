
package com.itismob.s02.salazar.allenaedelweiss.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private final List<Book> books;
    private final OnBookClickListener listener;

    // Click listener
    public interface OnBookClickListener {
        void onBookClick(Book book);
    }

    // Constructor with click listener
    public BookAdapter(List<Book> books, OnBookClickListener listener) {
        this.books = books;
        this.listener = listener;
    }

    // Original constructor for other screens
    public BookAdapter(List<Book> books) {
        this(books, null);
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book, parent, false);

        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull BookViewHolder holder, int position) {

        Book book = books.get(position);

        holder.title.setText(book.getTitle());
        holder.author.setText(book.getAuthor());
        holder.cover.setImageResource(book.getImageResId());

        // Book click
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onBookClick(book);
            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    static class BookViewHolder extends RecyclerView.ViewHolder {

        ImageView cover;
        TextView title, author;

        BookViewHolder(@NonNull View itemView) {
            super(itemView);

            cover = itemView.findViewById(R.id.bookCoverIV);
            title = itemView.findViewById(R.id.bookTitleTV);
            author = itemView.findViewById(R.id.bookAuthorTV);
        }
    }
}
