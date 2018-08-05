package com.am.framework.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.am.framework.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //handle the click on the back arrow click
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @OnClick({R.id.btn_translate_activity,
            R.id.btn_images_slider_activity,
            R.id.btn_Spanny,
            R.id.btn_notification_badge,
            R.id.btn_drawer,
            R.id.btn_material_drawer,
            R.id.btn_youtube_player})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_translate_activity:
                startActivity(new Intent(MainActivity.this, YendexTranslateActivity.class));
                break;
            case R.id.btn_images_slider_activity:
                startActivity(new Intent(MainActivity.this, ImagesSliderActivity.class));
                break;
            case R.id.btn_Spanny:
                startActivity(new Intent(MainActivity.this, SpannyActivity.class));
                break;
            case R.id.btn_notification_badge:
                startActivity(new Intent(MainActivity.this, NotificationBadgeActivity.class));
                break;
            case R.id.btn_drawer:
                startActivity(new Intent(MainActivity.this, DrawerActivity.class));
                break;
            case R.id.btn_material_drawer:
                startActivity(new Intent(MainActivity.this, MaterialDrawerActivity.class));
                break;
                case R.id.btn_youtube_player:
                startActivity(new Intent(MainActivity.this, YoutubePlayerActivity.class));
                break;
        }
    }
}
