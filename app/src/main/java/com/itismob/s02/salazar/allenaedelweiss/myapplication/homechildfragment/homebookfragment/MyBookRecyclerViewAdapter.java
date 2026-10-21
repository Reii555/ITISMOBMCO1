package com.itismob.s02.salazar.allenaedelweiss.myapplication.homechildfragment.homebookfragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itismob.s02.salazar.allenaedelweiss.myapplication.Book;
import com.itismob.s02.salazar.allenaedelweiss.myapplication.R;

import java.util.List;

public class MyBookRecyclerViewAdapter extends RecyclerView.Adapter<MyBookRecyclerViewAdapter.ViewHolder> {

    private final List<Book> books;
    private final boolean isFromFriends;

    public MyBookRecyclerViewAdapter(List<Book> books) {
        this(books, false);
    }

    public MyBookRecyclerViewAdapter(List<Book> books, boolean isFromFriends) {
        this.books = books;
        this.isFromFriends = isFromFriends;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutRes = isFromFriends ? R.layout.item_book_friends : R.layout.item_book_popular;
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutRes, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Book book = books.get(position);

        if (book.getImageResId() != 0) {
            holder.bookCoverIv.setImageResource(book.getImageResId());
        }

        if (isFromFriends && holder.friendRatingTv != null) {
            int stars = Math.round(book.getRating());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < stars && i < 5; i++) {
                sb.append("★");
            }
            holder.friendRatingTv.setText(sb.toString());
        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView bookCoverIv;
        public final ImageView friendAvatarIv;
        public final TextView friendRatingTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookCoverIv = itemView.findViewById(R.id.bookCoverIv);
            friendAvatarIv = itemView.findViewById(R.id.friendAvatarIv);
            friendRatingTv = itemView.findViewById(R.id.friendRatingTv);
        }
    }
}
