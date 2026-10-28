package com.itismob.s02.salazar.allenaedelweiss.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    public interface OnBookClickListener {
        void onBookClick(Book book);
    }

    private final List<Book> books = new ArrayList<>();
    private final OnBookClickListener listener;

    public SearchAdapter(List<Book> initialBooks) {
        this(initialBooks, null);
    }

    public SearchAdapter(List<Book> initialBooks, OnBookClickListener listener) {
        if (initialBooks != null) {
            this.books.addAll(initialBooks);
        }
        this.listener = listener;
    }

    public void updateData(List<Book> newBooks) {
        this.books.clear();
        if (newBooks != null) {
            this.books.addAll(newBooks);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book book = books.get(position);
        holder.bookTitleTv.setText(book.getTitle());
        holder.bookAuthorTv.setText(book.getAuthor());
        holder.bookDescriptionTv.setText(book.getDescription());

        if (book.getImageResId() != 0) {
            holder.bookCoverIv.setImageResource(book.getImageResId());
        }

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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView bookCoverIv;
        public final TextView bookTitleTv;
        public final TextView bookAuthorTv;
        public final TextView bookDescriptionTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookCoverIv = itemView.findViewById(R.id.bookCoverIv);
            bookTitleTv = itemView.findViewById(R.id.bookTitleTv);
            bookAuthorTv = itemView.findViewById(R.id.bookAuthorTv);
            bookDescriptionTv = itemView.findViewById(R.id.bookDescriptionTv);
        }
    }
}
