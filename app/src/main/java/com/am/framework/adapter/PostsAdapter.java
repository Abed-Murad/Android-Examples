package com.am.framework.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.am.framework.model.Post;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.MyViewHolder> {
    private List<Post> postList;
    private LayoutInflater layoutInflater;
    private PostsAdapterListener listener;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
    public interface PostsAdapterListener {
        void onPostClicked(Post post);
    }
}
