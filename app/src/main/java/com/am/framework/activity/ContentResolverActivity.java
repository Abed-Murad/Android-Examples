package com.am.framework.activity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.am.framework.R;
import com.udacity.example.droidtermsprovider.DroidTermsExampleContract;

import butterknife.BindView;
import butterknife.ButterKnife;



public class ContentResolverActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.button_next)
    Button nextBtn;
    @BindView(R.id.text_view_word)
    TextView mWordTextView;
    @BindView(R.id.text_view_definition)
    TextView mDefinitionTextView;


    private Cursor mData;
    private int mCrrentState;
    private int mDefinitionColumn;
    private int mWordColumn;
    private final int STATE_HIDDEN = 0;
    private final int STATE_SHOWN = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_resolver);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        new WordFetchTask().execute();
    }

    public void onButtonClick(View view) {
        switch (mCrrentState) {
            case STATE_HIDDEN:
                showDefinition();
                break;
            case STATE_SHOWN:
                fetchNextWord();
        }
    }



    private void fetchNextWord() {
        if (mData != null) {
            // Move to the next position in the cursor, if there isn't one, move to the first
            if (!mData.moveToNext()) {
                mData.moveToFirst();
            }
            mDefinitionTextView.setVisibility(View.INVISIBLE);
            nextBtn.setText(getString(R.string.text_btn_show_definition));

            mWordTextView.setText(mData.getString(mWordColumn));
            mDefinitionTextView.setText(mData.getString(mDefinitionColumn));

            mCrrentState = STATE_HIDDEN;
        }
    }

    private void showDefinition() {
        if (mData != null) {
            mDefinitionTextView.setVisibility(View.VISIBLE);
            nextBtn.setText(getString(R.string.text_btn_fetch_next_word));
            mCrrentState = STATE_SHOWN;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mData = null;
    }

    private class WordFetchTask extends AsyncTask<Void, Void, Cursor> {
        @Override
        protected Cursor doInBackground(Void... voids) {
            ContentResolver contentResolver = getContentResolver();
            Cursor cursor = contentResolver.query(DroidTermsExampleContract.CONTENT_URI,
                    null, null, null, null);
            return cursor;
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            mData = cursor;
            mDefinitionColumn = cursor.getColumnIndex(DroidTermsExampleContract.COLUMN_DEFINITION);
            mWordColumn = cursor.getColumnIndex(DroidTermsExampleContract.COLUMN_WORD);
            super.onPostExecute(cursor);
        }
    }


}
