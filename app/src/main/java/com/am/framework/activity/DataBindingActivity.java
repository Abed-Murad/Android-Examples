package com.am.framework.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import com.am.framework.R;
import com.am.framework.databinding.ActivityDataBindingBinding;
import com.am.framework.model.Item;

public class DataBindingActivity extends AppCompatActivity {
    ActivityDataBindingBinding mBinding;


    /**
     * If you are using data binding items inside a Fragment, ListView, or RecyclerView adapter,
     * you may prefer to use the inflate() methods of the bindings classes or the DataBindingUtil class
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
//        Alternatively, you can get the view using a LayoutInflater
//        mBinding = ActivityDataBindingBinding.inflate(getLayoutInflater());
        Item item = new Item(1001, "Grating", "Hello it's me ,Abed");
        mBinding.setItem(item);


    }

}
