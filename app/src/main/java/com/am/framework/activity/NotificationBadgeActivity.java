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
    Toolbar mToolbar;

    private LayerDrawable mNotificationIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_badge);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        showToolbarBackArrow();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_notifications, menu);
        MenuItem mNotificationMenuItem = menu.findItem(R.id.action_notification);
        mNotificationIcon = (LayerDrawable) mNotificationMenuItem.getIcon();
        setBadgeCount(this, mNotificationIcon, "10");
        return true;
    }

    public static void setBadgeCount(Context context, LayerDrawable icon, String count) {
        NotificationsBadgeDrawable badge;
        Drawable reusableDrawable = icon.findDrawableByLayerId(R.id.ic_badge);
        if (reusableDrawable != null && reusableDrawable instanceof NotificationsBadgeDrawable) {
            badge = (NotificationsBadgeDrawable) reusableDrawable;
        } else {
            badge = new NotificationsBadgeDrawable(context);
        }
        badge.setCount(count);
        icon.mutate();
        icon.setDrawableByLayerId(R.id.ic_badge, badge);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_notification:
                setBadgeCount(this, mNotificationIcon, "0");
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
