package com.am.framework.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.am.framework.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class CustomToolbarActivity extends BaseActivity {

    @BindView(R.id.main_toolbar_avatar)
    CircleImageView toolbarAvatarImageView;
    @BindView(R.id.main_toolbar_name)
    TextView toolbarNameTextView;
    @BindView(R.id.main_toolbar)
    Toolbar toolbar;

    View.OnClickListener onClickListener = (View v) -> Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_toolbar);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        hideToolbarTitle();
        toolbarAvatarImageView.setOnClickListener(onClickListener);
        toolbarNameTextView.setOnClickListener(onClickListener);

    }
}
