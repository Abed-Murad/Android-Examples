package com.am.framework.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import com.am.framework.R;
import com.am.framework.model.SliderImage;
import com.am.framework.dummy.DummyDataFactory;
import com.am.framework.view.ImagesSliderDialogFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImagesSliderActivity extends BaseActivity {
    private final static String IMAGES_LIST = "imagesList";
    private final static String POSITION = "position";
    private final static String SLIDE_SHOW = "slideshow";
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images_slider);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        showToolbarBackArrow();
        showImageSlider();
    }

    private void showImageSlider() {
        ArrayList<SliderImage> imagesList = DummyDataFactory.getFakeSliderImagesList();
        Bundle bundle = new Bundle();
        bundle.putSerializable(IMAGES_LIST, imagesList);
        bundle.putInt(POSITION, 0);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ImagesSliderDialogFragment newFragment = ImagesSliderDialogFragment.newInstance();
        newFragment.setArguments(bundle);
        newFragment.show(ft, SLIDE_SHOW);
    }
}
