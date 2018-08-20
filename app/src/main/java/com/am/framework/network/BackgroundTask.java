package com.am.framework.network;

import android.os.AsyncTask;
import android.util.Log;

import java.net.URL;

public class BackgroundTask extends AsyncTask<URL, Integer, String> {

    private static final String TAG = BackgroundTask.class.getSimpleName();

    /**
     * In this method change the visibility of the views, for example here you hide the
     * container and show the progress bar indicating that the data is beaning load
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /**
     * You can use publishProgress() to update the UI while the task is beaning executed
     *
     * @param params : those params comes form the execute() method arguments
     * @return : a String which is a parameter for the onPostExecute()
     */
    @Override
    protected String doInBackground(URL... params) {
        URL searchUrl = params[0];
        for (int i = 0; i < 10; i++) {
            publishProgress(i);
        }
        return searchUrl.toString();
    }

    /**
     * Use this method to hide the Progress Bar and populate the UI with the new Data
     *
     * @param githubSearchResults : return value form doInBackground()
     */
    @Override
    protected void onPostExecute(String githubSearchResults) {
        super.onPreExecute();
    }

    /**
     * Use this method to update the Ui , For example updating a horizontal Progress Bar
     * @param values : the argument form publishProgress()
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        Log.d(TAG, "onProgressUpdate:");
        super.onProgressUpdate(values);

    }
}
