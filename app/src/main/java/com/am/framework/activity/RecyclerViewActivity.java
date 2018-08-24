package com.am.framework.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.am.framework.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecyclerViewActivity extends AppCompatActivity {

    @BindView(R.id.btn_second_adapter)
    Button secondAdapterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_second_adapter)
    public void onViewClicked() {
        startActivity(new Intent(RecyclerViewActivity.this, SecondRecyclerAdapterActivity.class));
    }
}
