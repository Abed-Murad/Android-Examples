package com.am.framework.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.am.framework.R;
import com.am.framework.model.Item;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A custom adapter to use with the RecyclerView widget.
 */

/*
 * TODO : Add The RecyclerView OnScroll Listener
 * TODO : Add The Search On The Toolbar
 * */
public class RecyclerViewAdapterSecond extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 9;

    private String mHeaderTitle;
    private String mFooterTitle;

    private Context mContext;
    private List<Item> mItemList;
    private LayoutInflater mInflater;

    private OnHeaderClickListener mHeaderClickListener;
    private OnFooterClickListener mFooterClickListener;
    private OnItemClickListener mItemClickListener;

    public RecyclerViewAdapterSecond(Context context, String headerTitle, String footerTitle) {
        this.mContext = context;
        this.mHeaderTitle = headerTitle;
        this.mFooterTitle = footerTitle;
        this.mItemList = new ArrayList<>();
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case TYPE_HEADER: {
                View v = mInflater.inflate(R.layout.item_recycler_header, parent, false);
                return new HeaderViewHolder(v);
            }
            case TYPE_FOOTER: {
                View v = mInflater.inflate(R.layout.item_recycler_footer, parent, false);
                return new FooterViewHolder(v);
            }
            case TYPE_ITEM: {
                View v = mInflater.inflate(R.layout.item_recycler_list, parent, false);
                return new ViewHolder(v);
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;
            headerHolder.setHeaderData();

        } else if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerHolder = (FooterViewHolder) holder;
            footerHolder.SetFooterData();

        } else if (holder instanceof ViewHolder) {
            final Item item = getItem(position - 1);
            ViewHolder genericViewHolder = (ViewHolder) holder;
            genericViewHolder.bindData(item);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)) {
            return TYPE_HEADER;
        } else if (isPositionFooter(position)) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return mItemList == null ? 2 : mItemList.size() + 2;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    private boolean isPositionFooter(int position) {
        return position == mItemList.size() + 1;
    }


    public void updateList(List<Item> modelList) {
        this.mItemList = modelList;
        notifyDataSetChanged();
    }

    private Item getItem(int position) {
        return mItemList.get(position);
    }

    public void add(Item item) {
        mItemList.add(item);
        notifyItemInserted(mItemList.size() - 1);
    }

    public void addFirst(Item Item) {
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

    public void searchFilter(String search) {
        this.mItemList.clear();
        for (Item item : mItemList) {
            if (item.getTitle().toLowerCase().contains(search.toLowerCase())) {
                this.mItemList.add(item);
            }
        }
        notifyDataSetChanged();
    }

    public List<Item> getDataSet() {
        return this.mItemList;
    }


    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public void SetOnHeaderClickListener(final OnHeaderClickListener headerClickListener) {
        this.mHeaderClickListener = headerClickListener;
    }

    public void SetOnFooterClickListener(final OnFooterClickListener footerClickListener) {
        this.mFooterClickListener = footerClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, Item model);
    }

    public interface OnHeaderClickListener {
        void onHeaderClick(View view, String headerTitle);
    }

    public interface OnFooterClickListener {
        void onFooterClick(View view, String headerTitle);
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtFooter)
        TextView txtFooter;

        public FooterViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mFooterClickListener.onFooterClick(itemView, mFooterTitle);
                }
            });
        }

        private void SetFooterData() {
            txtFooter.setText(mFooterTitle);
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_header)
        TextView txtTitleHeader;

        public HeaderViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mHeaderClickListener.onHeaderClick(itemView, mHeaderTitle);
                }
            });
        }

        private void setHeaderData() {
            txtTitleHeader.setText(mHeaderTitle);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_img_user)
        ImageView imgUser;
        @BindView(R.id.item_txt_title)
        TextView itemTxtTitle;
        @BindView(R.id.item_txt_message)
        TextView itemTxtMessage;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), mItemList.get(getAdapterPosition() - 1));
                }
            });
        }

        private void bindData(Item item) {
            itemTxtTitle.setText(item.getTitle());
            itemTxtMessage.setText(item.getMessage());
        }
    }

}

