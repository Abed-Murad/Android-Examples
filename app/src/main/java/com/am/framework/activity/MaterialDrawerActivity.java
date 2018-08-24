package com.am.framework.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.am.framework.R;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MaterialDrawerActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private Drawer drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_drawer);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        setupDrawer();
    }

    private void setupDrawer() {
        AccountHeader headerResult = new AccountHeaderBuilder().withActivity(this)
                .withHeaderBackground(R.drawable.side_nav_bar)
                .withSelectionListEnabledForSingleProfile(false)
                .addProfiles(new ProfileDrawerItem().withName(R.string.title_user_name)
                        .withEmail(R.string.title_user_email).withIcon(getResources()
                                .getDrawable(R.drawable.avatar_me_round))).build();

        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1)
                .withName(R.string.title_home).withIcon(FontAwesome.Icon.faw_apple).withBadge("15");
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2)
                .withName(R.string.title_settings).withIcon(FontAwesome.Icon.faw_github);
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(2)
                .withName(R.string.title_extra)
                .withIcon(GoogleMaterial.Icon.gmd_account_balance_wallet).withBadge("1")
                .withBadgeStyle(new BadgeStyle().withTextColor(Color.GRAY));
        DividerDrawerItem drawerDivider = new DividerDrawerItem();

        drawer = new DrawerBuilder().withActivity(this).withDisplayBelowStatusBar(false)
                .withToolbar(mToolbar)
                .withAccountHeader(headerResult)
                .addDrawerItems(item1, drawerDivider, item2, item3, drawerDivider)
                .withOnDrawerItemClickListener((view, position, drawerItem)
                        -> {
                    Toast.makeText(MaterialDrawerActivity.this, String.valueOf(position),
                            Toast.LENGTH_SHORT).show();
                    drawer.closeDrawer();
                    return true;
                }).build();
        drawer.addStickyFooterItem(new PrimaryDrawerItem().withName("StickyFooterItem"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen()) {
            drawer.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }
}
