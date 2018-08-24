package com.am.framework.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.am.framework.R;
import com.am.framework.model.SliderImage;
import com.am.framework.view.ImagesSliderDialogFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.am.framework.utill.FakeDataFactory.DUMMY_IMG_THUNDER_CAT;
import static com.am.framework.utill.FakeDataFactory.DUMMY_IMG_URL_FOOTBALL;
import static com.am.framework.utill.FakeDataFactory.DUMMY_IMG_URL_QUEEN;
import static com.am.framework.utill.FakeDataFactory.DUMMY_IMG_URL_STARS_AT_NIGHT;

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
        showToolbarBackArrow();



        button.setOnClickListener(view -> {
            ArrayList<SliderImage> imagesList = new ArrayList<>();
                imagesList.add(new SliderImage("Football Field", DUMMY_IMG_URL_FOOTBALL, DUMMY_IMG_URL_FOOTBALL, DUMMY_IMG_URL_FOOTBALL, "2018-05-06"));
                imagesList.add(new SliderImage("Stars At Night", DUMMY_IMG_URL_STARS_AT_NIGHT, DUMMY_IMG_URL_STARS_AT_NIGHT, DUMMY_IMG_URL_STARS_AT_NIGHT, "2000-01-09"));
                imagesList.add(new SliderImage("Best Band Ever", DUMMY_IMG_URL_QUEEN, DUMMY_IMG_URL_QUEEN, DUMMY_IMG_URL_QUEEN, "2014-12-04"));
                imagesList.add(new SliderImage("Thunder Cat Meme", DUMMY_IMG_THUNDER_CAT, DUMMY_IMG_THUNDER_CAT, DUMMY_IMG_THUNDER_CAT, "2020-02-20"));
            Bundle bundle = new Bundle();
            bundle.putSerializable("imagesList", imagesList);
            bundle.putInt("position", 0);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ImagesSliderDialogFragment newFragment = ImagesSliderDialogFragment.newInstance();
            newFragment.setArguments(bundle);
            newFragment.show(ft, "slideshow");
        });

    }


}
