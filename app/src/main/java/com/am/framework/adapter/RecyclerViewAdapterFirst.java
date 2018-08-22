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
    private List<Item> itemList;
    private Context context;
    private OnItemClickListener onItemClickListener;


    public RecyclerViewAdapterFirst(Context context) {
        this.itemList = new ArrayList<>();
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(getItem(position));
    }


    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    public Item getItem(int position) {
        return itemList.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(view -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(view ,itemList.get(getAdapterPosition()));
                }
            });
        }

        private void bindData(Item item) {
        }
    }


    public void add(Item item) {
        itemList.add(item);
        notifyItemInserted(itemList.size() - 1);
    }

    public void addAtFirst(Item Item) {
        itemList.add(0, Item);
        notifyItemInserted(0);

    }


    public void addAll(List<Item> appendedItemList) {
        if (appendedItemList == null || appendedItemList.size() <= 0) {
            return;
        }
        if (this.itemList == null) {
            this.itemList = new ArrayList<>();
        }
        this.itemList.addAll(appendedItemList);
        notifyDataSetChanged();
    }

    /*Simple Search Filter Method */
    public void searchFilter(List<Item> list, String search) {
        this.itemList.clear();
        if (search == null || search.isEmpty()) {
            this.itemList.addAll(list);
        } else {
            for (Item item : list) {
                if (item.getTitle().toLowerCase().contains(search.toLowerCase())) {
                    this.itemList.add(item);
                }
            }
        }
        notifyDataSetChanged();

    }


    public void addOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, Item item);
    }


}
