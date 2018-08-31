package com.am.framework.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.content.Loader;
import android.support.v4.content.AsyncTaskLoader;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.am.framework.R;

import java.net.URL;

public class AsyncTaskLoaderActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String[]> {

//  This ID will uniquely identify the Loader. We can use it, for example, to get a handle
//  on our Loader at a later point in time through the support LoaderManager.
    private static final int LOADER_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_loader);


        /*
         * From MainActivity, we have implemented the LoaderCallbacks interface with the type of
         * String array. (implements LoaderCallbacks<String[]>) The variable callback is passed
         * to the call to initLoader below. This means that whenever the loaderManager has
         * something to notify us of, it will do so through this callback.
         */
        LoaderManager.LoaderCallbacks<String[]> loaderCallbacks = AsyncTaskLoaderActivity.this;
        /*
         * The second parameter of the initLoader method below is a Bundle. Optionally, you can
         * pass a Bundle to initLoader that you can then access from within the onCreateLoader
         * callback. In our case, we don't actually use the Bundle, but it's here in case we wanted
         * to.
         */
        Bundle bundleForLoader = null;
        /*
         * Ensures a loader is initialized and active. If the loader doesn't already exist, one is
         * created and (if the activity/fragment is currently started) starts the loader. Otherwise
         * the last created loader is re-used.
         */
        getSupportLoaderManager().initLoader(LOADER_ID, bundleForLoader, loaderCallbacks);




    }


    @SuppressLint("StaticFieldLeak")
    @NonNull
    @Override
    public Loader<String[]> onCreateLoader(int id, @Nullable Bundle args) {
        return new AsyncTaskLoader<String[]>(this) {
            /* This String array will hold and help cache our weather data */
            String[] mWeatherData = null;
            /**
             * Subclasses of AsyncTaskLoader must implement this to take care of loading their data.
             */
            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                if (mWeatherData != null) {
                    deliverResult(mWeatherData);
                } else {
                    forceLoad();
                }
            }

            @Nullable
            @Override
            public String[] loadInBackground() {
                return new String[0];
            }

            @Override
            public void cancelLoadInBackground() {
                super.cancelLoadInBackground();
            }

            @Override
            public void deliverResult(@Nullable String[] data) {
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String[]> loader, String[] data) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<String[]> loader) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Use AppCompatActivity's method getMenuInflater to get a handle on the menu inflater */
        MenuInflater inflater = getMenuInflater();
        /* Use the inflater's inflate method to inflate our menu layout to this menu */
        inflater.inflate(R.menu.menu_async_task_loader, menu);
        /* Return true so that the menu is displayed in the Toolbar */
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            getSupportLoaderManager().restartLoader(LOADER_ID, null, this);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }



}
