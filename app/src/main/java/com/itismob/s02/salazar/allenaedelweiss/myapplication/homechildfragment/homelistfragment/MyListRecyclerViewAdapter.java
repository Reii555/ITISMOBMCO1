package com.itismob.s02.salazar.allenaedelweiss.myapplication.homechildfragment.homelistfragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.itismob.s02.salazar.allenaedelweiss.myapplication.R;
import com.itismob.s02.salazar.allenaedelweiss.myapplication.UserList;
import com.itismob.s02.salazar.allenaedelweiss.myapplication.homechildfragment.homebookfragment.MyBookRecyclerViewAdapter;

import java.util.List;

public class MyListRecyclerViewAdapter extends RecyclerView.Adapter<MyListRecyclerViewAdapter.ViewHolder> {

    private final List<UserList> userLists;

    public MyListRecyclerViewAdapter(List<UserList> userLists) {
        this.userLists = userLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        UserList userList = userLists.get(position);

        if (holder.titleTv != null) {
            holder.titleTv.setText(userList.getTitle());
        }

        if (holder.usernameTv != null) {
            holder.usernameTv.setText(userList.getUsername());
        }

        if (holder.descriptionTv != null) {
            holder.descriptionTv.setText(userList.getDescription());
        }

        if (holder.creditTv != null) {
            holder.creditTv.setText(userList.getCredit());
        }

        if (holder.avatarIv != null) {
            if (userList.getAvatarResId() != 0) {
                holder.avatarIv.setImageResource(userList.getAvatarResId());
            } else {
                holder.avatarIv.setImageResource(R.drawable.otherpfp);
            }
        }

        if (holder.postersRv != null && userList.getBooks() != null) {
            holder.postersRv.setLayoutManager(
                    new LinearLayoutManager(holder.itemView.getContext(), LinearLayoutManager.HORIZONTAL, false)
            );
            holder.postersRv.setAdapter(new MyBookRecyclerViewAdapter(userList.getBooks(), false));
        }
    }

    @Override
    public int getItemCount() {
        return userLists != null ? userLists.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView titleTv;
        public final TextView usernameTv;
        public final ImageView avatarIv;
        public final TextView descriptionTv;
        public final TextView creditTv;
        public final RecyclerView postersRv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.tv_list_title);
            usernameTv = itemView.findViewById(R.id.tv_username);
            avatarIv = itemView.findViewById(R.id.iv_avatar);
            descriptionTv = itemView.findViewById(R.id.tv_description);
            creditTv = itemView.findViewById(R.id.tv_credit);
            postersRv = itemView.findViewById(R.id.rv_movie_posters);
        }
    }
}
