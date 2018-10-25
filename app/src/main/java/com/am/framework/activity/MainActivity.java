package com.am.framework.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.am.framework.BuildConfig;
import com.am.framework.R;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String URL_TERMS_OF_SERVICE = "https://www.kabam.com/corporate/terms-of-service.html";
    private static final int FIREBASE_UI_SIGN_IN_REQUEST_CODE = 1010;
    private static final int GOOGLE_SIGN_IN_REQUEST_CODE = 9001;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @OnClick({R.id.btn_translate_activity,
            R.id.btn_images_slider_activity,
            R.id.btn_Spanny,
            R.id.btn_notification_badge,
            R.id.btn_drawer,
            R.id.btn_material_drawer,
            R.id.btn_youtube_player,
            R.id.btn_many_image_views,
            R.id.btn_custom_toolbar,
            R.id.btn_firebase_ui_auth,
            R.id.btn_settings_activity,
            R.id.btn_sqlite_activity,
            R.id.btn_recyclerview_activity,
            R.id.btn_content_resolver,
            R.id.btn_maps,
            R.id.btn_data_binding,
            R.id.btn_notification})
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
            case R.id.btn_many_image_views:
                startActivity(new Intent(MainActivity.this, ManyImageViewsActivity.class));
                break;
            case R.id.btn_custom_toolbar:
                startActivity(new Intent(MainActivity.this, CustomToolbarActivity.class));
                break;
            case R.id.btn_firebase_ui_auth:
                startFirebaseUiForAuth();
                break;
            case R.id.btn_settings_activity:
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                break;
            case R.id.btn_sqlite_activity:
                startActivity(new Intent(MainActivity.this, SQLiteActivity.class));
                break;
            case R.id.btn_recyclerview_activity:
                startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class));
                break;
            case R.id.btn_content_resolver:
                startActivity(new Intent(MainActivity.this, ContentResolverActivity.class));
                break;
            case R.id.btn_maps:
                startActivity(new Intent(MainActivity.this, MapsActivity.class));
                break;
            case R.id.btn_data_binding:
                startActivity(new Intent(MainActivity.this, DataBindingActivity.class));
                break;
            case  R.id.btn_notification:
                startActivity(new Intent(MainActivity.this, NotificationActivity.class));
                break;
        }
    }


    private void startFirebaseUiForAuth() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.FacebookBuilder()
                        .setPermissions(Arrays.asList("user_friends", "instagram_basic")).build(),
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.TwitterBuilder().build()
//              new AuthUI.IdpConfig.PhoneBuilder().build(),
        );

        Intent intent = AuthUI.getInstance().createSignInIntentBuilder()
                .setIsSmartLockEnabled(!BuildConfig.DEBUG, true)
                .setAvailableProviders(providers)
                .setLogo(R.drawable.logo_placehoder_gray)
                .setTheme(R.style.LoginTheme)
                .setTosAndPrivacyPolicyUrls(URL_TERMS_OF_SERVICE, URL_TERMS_OF_SERVICE)
                .build();

        startActivityForResult(intent, FIREBASE_UI_SIGN_IN_REQUEST_CODE);
    }

    private void updateUI() {
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(this, FirebaseUserProfileActivity.class));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        updateUI();
    }

    /**
     * This method is called after this activity has been paused or restarted.
     * Often, this is after new data has been inserted through an AddTaskActivity,
     * so this re-queries the database data for any changes.
     */
    @Override
    protected void onResume() {
        super.onResume();
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
}
