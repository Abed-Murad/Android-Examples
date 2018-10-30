package com.am.framework.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.am.framework.R;
import com.am.framework.databinding.ItemPostBinding;
import com.am.framework.model.Post;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.MyViewHolder> {
    private List<Post> postList;
    private LayoutInflater layoutInflater;
    private PostsAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final ItemPostBinding mBinding;

        public MyViewHolder(ItemPostBinding itemPostBinding) {
            super(itemPostBinding.getRoot());
            this.mBinding = itemPostBinding;
        }
    }

    public PostsAdapter(List<Post> postList, PostsAdapterListener listener) {
        this.postList = postList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        ItemPostBinding mBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_post,
                parent, false);

        return new MyViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Post post = postList.get(position);
        holder.mBinding.setPost(post);
        holder.mBinding.thumbnail.setOnClickListener(v -> {
            if (listener != null) {
                listener.onPostClicked(post);
            }
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public interface PostsAdapterListener {
        void onPostClicked(Post post);
    }
}
