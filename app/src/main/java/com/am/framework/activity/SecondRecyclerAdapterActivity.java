package com.am.framework.activity;

import android.app.SearchManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.am.framework.R;
import com.am.framework.adapter.RecyclerViewAdapterSecond;
import com.am.framework.model.Item;
import com.am.framework.dummy.DummyDataFactory;
import com.am.framework.utils.RecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondRecyclerAdapterActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_recycler_list)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private RecyclerViewAdapterSecond mAdapter;
    private RecyclerViewScrollListener mScrollListener;
    private LinearLayoutManager mLayoutManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_recycler_adapter);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        showToolbarBackArrow();

        setupAdapter();
        mSwipeRefreshLayout.setOnRefreshListener(this);

    }

    private void setupAdapter() {
        List<Item> itemList = DummyDataFactory.getFakeItemList();
        mAdapter = new RecyclerViewAdapterSecond(SecondRecyclerAdapterActivity.this,
                "Header", "Footer");
        mLayoutManger = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter.addAll(itemList);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManger);
        mRecyclerView.setAdapter(mAdapter);

        // Add dividers
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                mRecyclerView.getContext(),
                mLayoutManger.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        mAdapter.SetOnItemClickListener((view, position, model) -> {
            //handle item click events here
            Toast.makeText(SecondRecyclerAdapterActivity.this,
                    "Hey " + model.getTitle(), Toast.LENGTH_SHORT).show();
        });

        mAdapter.SetOnHeaderClickListener((view, headerTitle) -> {
            //handle item click events here
            Toast.makeText(SecondRecyclerAdapterActivity.this,
                    "Hey I am a header", Toast.LENGTH_SHORT).show();
        });

        mAdapter.SetOnFooterClickListener((view, footerTitle) -> {
            //handle item click events here
            Toast.makeText(SecondRecyclerAdapterActivity.this,
                    "Hey I am a footer", Toast.LENGTH_SHORT).show();
        });

        mScrollListener = new RecyclerViewScrollListener() {
            public void onEndOfScrollReached(RecyclerView rv) {
                Toast.makeText(SecondRecyclerAdapterActivity.this,
                        "The End , Do your pagination stuff here", Toast.LENGTH_SHORT).show();
                mScrollListener.disableScrollListener();
            }
        };
        /*
             Note: The below two methods should be used wisely to
             handle the pagination enable and disable states based on the use case.
             1. mScrollListener.disableScrollListener(); - Should be called to disable the scroll state.
             2. mScrollListener.enableScrollListener(); - Should be called to enable the scroll state.
        */
        mRecyclerView.addOnScrollListener(mScrollListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_search, menu);

        // Retrieve the SearchView and plug it into SearchManager
        final SearchView searchView = (SearchView) MenuItemCompat
                .getActionView(menu.findItem(R.id.action_search));

        SearchManager searchManager = (SearchManager) this.getSystemService(this.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));

        //changing edittext color
        EditText searchEdit = ((EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text));
        searchEdit.setTextColor(Color.WHITE);
        searchEdit.setHintTextColor(Color.WHITE);
        searchEdit.setBackgroundColor(Color.TRANSPARENT);
        searchEdit.setHint("Search");

        InputFilter[] fArray = new InputFilter[2];
        fArray[0] = new InputFilter.LengthFilter(40);
        fArray[1] = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

                for (int i = start; i < end; i++) {
                    if (!Character.isLetterOrDigit(source.charAt(i)))
                        return "";
                }
                return null;
            }
        };
        searchEdit.setFilters(fArray);
        View v = searchView.findViewById(android.support.v7.appcompat.R.id.search_plate);
        v.setBackgroundColor(Color.TRANSPARENT);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<Item> filterList = new ArrayList<>();
                List<Item> dataSet = mAdapter.getDataSet();
                if (s.length() > 0) {
                    for (int i = 0; i < dataSet.size(); i++) {
                        if (dataSet.get(i).getTitle().toLowerCase().contains(s.toLowerCase())) {
                            filterList.add(dataSet.get(i));
                            mAdapter.updateList(filterList);
                        }
                    }
                } else {
                    mAdapter.updateList(dataSet);
                }
                return false;
            }
        });
        return true;
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(() -> {
            if (mSwipeRefreshLayout.isRefreshing()) {
                mSwipeRefreshLayout.setRefreshing(false);
            }
            // Do your stuff on refresh

        }, 5000);
    }
}
