package com.am.framework.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;

import com.am.framework.R;
import com.am.framework.helper.FullScreenHelper;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerFullScreenListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YoutubePlayerActivity extends BaseActivity {

    @BindView(R.id.youtube_player_view)
    YouTubePlayerView mYouTubePlayerView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private FullScreenHelper fullScreenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_player);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        showToolbarBackArrow();

        getLifecycle().addObserver(mYouTubePlayerView);
        //mToolbar , fab will be hidden and shown when FullScreen toggles
        fullScreenHelper = new FullScreenHelper(this, mToolbar, fab);

        mYouTubePlayerView.initialize(
                initializedYouTubePlayer ->
                        initializedYouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady() {
                String videoId = "pS-gbqbVd8c"; //Game of Thorns - Light of the Seven
                initializedYouTubePlayer.loadVideo(videoId, 0);
            }
        }), true);

        mYouTubePlayerView.addFullScreenListener(new YouTubePlayerFullScreenListener() {
            @Override
            public void onYouTubePlayerEnterFullScreen() {
                fullScreenHelper.enterFullScreen();
            }

            @Override
            public void onYouTubePlayerExitFullScreen() {
                fullScreenHelper.exitFullScreen();
            }
        });
    }
}
