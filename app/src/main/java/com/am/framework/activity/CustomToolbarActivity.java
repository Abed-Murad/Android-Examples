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
    CircleImageView mToolbarAvatarImageView;
    @BindView(R.id.main_toolbar_name)
    TextView mToolbarNameTextView;
    @BindView(R.id.main_toolbar)
    Toolbar mToolbar;

    View.OnClickListener mOnClickListener = (View v) -> Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_toolbar);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        hideToolbarTitle();
        mToolbarAvatarImageView.setOnClickListener(mOnClickListener);
        mToolbarNameTextView.setOnClickListener(mOnClickListener);

    }
}
