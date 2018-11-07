package com.am.framework.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.am.framework.R;
import com.am.framework.adapter.GuestListAdapter;
import com.am.framework.data.sqlite.WaitListDbHelper;
import com.am.framework.dummy.DummyDataFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.provider.BaseColumns._ID;
import static com.am.framework.data.sqlite.WaitListContract.WaitListEntry;
import static com.am.framework.data.sqlite.WaitListContract.WaitListEntry.COLUMN_TIMESTAMP;
import static com.am.framework.data.sqlite.WaitListContract.WaitListEntry.TABLE_NAME;

public class SQLiteActivity extends BaseActivity {

    private static final String TAG = SQLiteActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_person_name)
    EditText mPersonNameEditText;
    @BindView(R.id.et_party_count)
    EditText mPartyCountEditText;
    @BindView(R.id.rv_all_guests)
    RecyclerView mRecyclerView;

    private GuestListAdapter mAdapter;
    private SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        showToolbarBackArrow();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new GuestListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        // Create a DB helper (this will create the DB if run for the first time)
        WaitListDbHelper dbHelper = new WaitListDbHelper(this);
        mDatabase = dbHelper.getWritableDatabase();
        //create a list of fake guests And Insert Them to the Database
        DummyDataFactory.insertFakeData(mDatabase);
        // Get All Guests From The Database & Update The Adapter
        Cursor cursor = getAllGuests();
        mAdapter.swapCursor(cursor);
        //Add ItemTouchHelper to mRecyclerView to Catch Right & Left Swipes
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            // Used With Drag Action , will just ignore it here
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                long id = (long) viewHolder.itemView.getTag();
                removeGuest(id);
                mAdapter.swapCursor(getAllGuests());
            }
        }).attachToRecyclerView(mRecyclerView);//Attach The ItemTouchHelper to The RecyclerView
    }

    /**Query the mDatabase and get all guests from the waitList table
     * @return Cursor containing the list of guests     */
    public Cursor getAllGuests() {
        return mDatabase.query(TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                COLUMN_TIMESTAMP);
    }

    /**Removes the record with the specified id
     * @param id the DB id to be removed
     * @return True: if removed successfully, False: if failed      */
    private boolean removeGuest(long id) {
        return mDatabase.delete(TABLE_NAME, _ID + "=" + id, null) > 0;
    }

    public void onClickAddToWaitListBtn(View view) {
        if (mPersonNameEditText.getText().length() == 0
                || mPartyCountEditText.getText().length() == 0) {
            return;
        }
        int partySize = 1;
        try {
            //mNewPartyCountEditText inputType="number", so this should always work
            partySize = Integer.parseInt(mPartyCountEditText.getText().toString());
        } catch (NumberFormatException ex) {
            Log.e(TAG, "Failed to parse party size text to number: " + ex.getMessage());
        }

        // Add guest info to mDb
        addNewGuest(mPersonNameEditText.getText().toString(), partySize);

        // Update the cursor in the adapter to trigger UI to display the new list
        mAdapter.swapCursor(getAllGuests());

        //clear UI text fields
        mPartyCountEditText.clearFocus();
        mPersonNameEditText.getText().clear();
        mPartyCountEditText.getText().clear();
    }

    /**Adds a new guest to the mDb including the party count and the current timestamp
     * @param name  Guest's name
     * @param partySize Number in party
     * @return id of new record added       */
    private long addNewGuest(String name, int partySize) {
        ContentValues cv = new ContentValues();
        cv.put(WaitListEntry.COLUMN_GUEST_NAME, name);
        cv.put(WaitListEntry.COLUMN_PARTY_SIZE, partySize);
        return mDatabase.insert(WaitListEntry.TABLE_NAME, null, cv);
    }

}
