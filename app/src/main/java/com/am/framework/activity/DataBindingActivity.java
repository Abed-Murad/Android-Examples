package com.am.framework.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.am.framework.R;
import com.am.framework.databinding.ActivityDataBindingBinding;

public class DataBindingActivity extends AppCompatActivity {
    ActivityDataBindingBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this , R.layout.activity_data_binding);
        mBinding.tvName.setText("Abdallah Murad , But With Binding");

    }

}
