package com.am.framework.activity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.am.droidtermsprovider.DroidTermsExampleContract;
import com.am.framework.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContentResolverActivity extends BaseActivity {

    private final int VISIBILITY_HIDDEN = 0;
    private final int VISIBILITY_SHOWN = 1;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.button_next)
    Button mNextBtn;
    @BindView(R.id.text_view_word)
    TextView mWordTextView;
    @BindView(R.id.text_view_definition)
    TextView mDefinitionTextView;

    private Cursor mFetchedTableData;
    //Used To Know if the Definition TextView is Visible or InVisible
    private int mDefinitionTextViewVisibilityCurrentState;
    //Used to Retrieve Data From Cursor
    private int mDefinitionColumnIndex, mWordColumnIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_resolver);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        showToolbarBackArrow();

        //Retrieve All The Words From The Content Provider Using The Content Resolver
        new WordsFetchingTask().execute();
    }

    public void onClickNextWordBtn(View view) {
        switch (mDefinitionTextViewVisibilityCurrentState) {
            case VISIBILITY_HIDDEN:
                showDefinition();
                break;
            case VISIBILITY_SHOWN:
                fetchNextWord();
        }
    }

    /**
     * Used to Fetch The Next word form Cursor then show the word on the Screen and Hide the
     * Definition tell the user click on nextWordBtn
     */
    private void fetchNextWord() {

        if (mFetchedTableData != null) {
            // Move to the next position in the cursor, if there isn't one, move to the first
            if (!mFetchedTableData.moveToNext()) {
                mFetchedTableData.moveToFirst();
            }
            mDefinitionTextView.setVisibility(View.INVISIBLE);
            mNextBtn.setText(getString(R.string.text_btn_show_definition));

            String word = mFetchedTableData.getString(mWordColumnIndex);
            String wordDefinition = mFetchedTableData.getString(mDefinitionColumnIndex);

            mWordTextView.setText(word);
            mDefinitionTextView.setText(wordDefinition);

            mDefinitionTextViewVisibilityCurrentState = VISIBILITY_HIDDEN;
        }
    }

    private void showDefinition() {
        if (mFetchedTableData != null) {
            mDefinitionTextView.setVisibility(View.VISIBLE);
            mNextBtn.setText(getString(R.string.text_btn_fetch_next_word));
            mDefinitionTextViewVisibilityCurrentState = VISIBILITY_SHOWN;
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class WordsFetchingTask extends AsyncTask<Void, Void, Cursor> {
        @Override
        protected Cursor doInBackground(Void... voids) {
            // Fetch The Data From the Content Provider
            ContentResolver contentResolver = getContentResolver();
            return contentResolver.query(DroidTermsExampleContract.CONTENT_URI,
                    null, null, null, null);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);

            mFetchedTableData = cursor;

            //get the Columns Indexes [Word Column , Definition Column]
            mDefinitionColumnIndex = cursor.getColumnIndex(DroidTermsExampleContract.COLUMN_DEFINITION);
            mWordColumnIndex = cursor.getColumnIndex(DroidTermsExampleContract.COLUMN_WORD);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFetchedTableData = null;
    }

}
