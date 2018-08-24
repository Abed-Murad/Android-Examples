package com.am.framework.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.am.framework.R;
import com.am.framework.view.NotificationsBadgeDrawable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationBadgeActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private LayerDrawable notificationIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_badge);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        showToolbarBackArrow();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_notifications, menu);

        MenuItem notificationMenuItem = menu.findItem(R.id.action_notification);
        notificationIcon = (LayerDrawable) notificationMenuItem.getIcon();
        setBadgeCount(this, notificationIcon, "10");
        return true;
    }


    public static void setBadgeCount(Context context, LayerDrawable icon, String count) {
        NotificationsBadgeDrawable badge;
        Drawable reuse = icon.findDrawableByLayerId(R.id.ic_badge);
        if (reuse != null && reuse instanceof NotificationsBadgeDrawable) {
            badge = (NotificationsBadgeDrawable) reuse;
        } else {
            badge = new NotificationsBadgeDrawable(context);
        }
        badge.setCount(count);
        icon.mutate();
        icon.setDrawableByLayerId(R.id.ic_badge, badge);
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //handle the click on the back arrow click
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_notification:
                setBadgeCount(this, notificationIcon, "0");
            default:
                return super.onOptionsItemSelected(item);
        }
    }





}
