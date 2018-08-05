package com.am.framework.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MaterialDrawerActivity extends AppCompatActivity {

    private Drawer result;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_drawer);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setupDrawer();
    }

    private void setupDrawer() {
        AccountHeader headerResult = new AccountHeaderBuilder().withActivity(this)
                .withHeaderBackground(R.drawable.side_nav_bar)
                .withSelectionListEnabledForSingleProfile(false)
                .addProfiles(new ProfileDrawerItem().withName("Abdallah Murad")
                .withEmail("Abdallah.Murad@Mail.com").withIcon(getResources()
                .getDrawable(R.drawable.avatar_me_round)))
                .build();


        final PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Home").withIcon(FontAwesome.Icon.faw_apple).withBadge("15");
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("Settings").withIcon(FontAwesome.Icon.faw_github);
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(2).withName("Extra").withIcon(GoogleMaterial.Icon.gmd_account_balance_wallet).withBadge("1").withBadgeStyle(new BadgeStyle().withTextColor(Color.GRAY));
        DividerDrawerItem drawerDivider = new DividerDrawerItem();

        result = new DrawerBuilder().withActivity(this).withDisplayBelowStatusBar(false)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .addDrawerItems(item1, drawerDivider, item2, item3, drawerDivider)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Toast.makeText(MaterialDrawerActivity.this, position + "", Toast.LENGTH_SHORT).show();
                        result.closeDrawer();
                        return true;
                    }
                }).build();
        result.addStickyFooterItem(new PrimaryDrawerItem().withName("StickyFooterItem"));
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

    @Override
    public void onBackPressed() {
        if (result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }
}
