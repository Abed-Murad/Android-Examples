package com.am.framework.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.am.framework.model.Item;

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewAdapterFirst extends RecyclerView.Adapter<RecyclerViewAdapterFirst.ViewHolder> {

    private Context mContext;
    private List<Item> mItemList;
    private OnItemClickListener mOnItemClickListener;

    public RecyclerViewAdapterFirst(Context context) {
        this.mContext = context;
        this.mItemList = new ArrayList<>();
    }
    public RecyclerViewAdapterFirst(Context context, OnItemClickListener onItemClickListener) {
        this.mContext = context;
        this.mItemList = new ArrayList<>();
        this.mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = getItem(position);
        holder.bindData(item);
    }

    @Override
    public int getItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

    private Item getItem(int position) {
        return mItemList.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(view -> {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(view, mItemList.get(getAdapterPosition()));
                }
            });
        }

        private void bindData(Item item) {
            // Do Data Binding Here
        }
    }


    public void add(Item item) {
        mItemList.add(item);
        notifyItemInserted(mItemList.size() - 1);
    }

    public void addAtFirst(Item Item) {
        mItemList.add(0, Item);
        notifyItemInserted(0);

    }

    public void addAll(List<Item> appendedItemList) {
        if (appendedItemList == null || appendedItemList.size() <= 0) {
            return;
        }
        if (this.mItemList == null) {
            this.mItemList = new ArrayList<>();
        }
        this.mItemList.addAll(appendedItemList);
        notifyDataSetChanged();
    }

    /*Simple Search Filter Method */
    public void searchFilter(List<Item> list, String search) {
        this.mItemList.clear();
        if (search == null || search.isEmpty()) {
            this.mItemList.addAll(list);
        } else {
            for (Item item : list) {
                if (item.getTitle().toLowerCase().contains(search.toLowerCase())) {
                    this.mItemList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void addOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, Item item);
    }

}
