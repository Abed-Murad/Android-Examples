package com.am.framework.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.am.framework.R;
import com.am.framework.model.SliderImage;
import com.am.framework.view.ImagesSliderDialogFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImagesSliderActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images_slider);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        showBackArrow();



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<SliderImage> imagesList = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    imagesList.add(new SliderImage("Image Title",
                            "http://via.placeholder.com/350x150",
                            "http://via.placeholder.com/350x150",
                            "http://via.placeholder.com/350x150",
                            "2016-06-06"));
                }

                Bundle bundle = new Bundle();
                bundle.putSerializable("imagesList", imagesList);
                bundle.putInt("position", 0);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ImagesSliderDialogFragment newFragment = ImagesSliderDialogFragment.newInstance();
                newFragment.setArguments(bundle);
                newFragment.show(ft, "slideshow");
            }
        });

    }


}
