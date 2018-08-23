package com.am.framework.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.am.framework.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContentResolverActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.button_next)
    Button buttonNext;
    @BindView(R.id.text_view_word)
    TextView textViewWord;
    @BindView(R.id.text_view_definition)
    TextView textViewDefinition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_resolver);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);


    }

    public void onButtonClick(View view) {
    }
}
