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
import com.am.framework.data.WaitlistDbHelper;
import com.am.framework.utill.FakeDataFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.provider.BaseColumns._ID;
import static com.am.framework.data.WaitlistContract.WaitlistEntry;
import static com.am.framework.data.WaitlistContract.WaitlistEntry.COLUMN_TIMESTAMP;
import static com.am.framework.data.WaitlistContract.WaitlistEntry.TABLE_NAME;

public class SqliteActivity extends BaseActivity {

    private static final String TAG = SqliteActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.person_name_edit_text)
    EditText personNameEditText;
    @BindView(R.id.party_count_edit_text)
    EditText partyCountEditText;
    @BindView(R.id.all_guests_list_view)
    RecyclerView allGuestsListView;

    private GuestListAdapter mAdapter;
    private SQLiteDatabase mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        showBackArrow();
        allGuestsListView.setLayoutManager(new LinearLayoutManager(this));
        allGuestsListView.setHasFixedSize(true);
        mAdapter = new GuestListAdapter(this);
        allGuestsListView.setAdapter(mAdapter);

        // Create a DB helper (this will create the DB if run for the first time)
        WaitlistDbHelper dbHelper = new WaitlistDbHelper(this);

        mDatabase = dbHelper.getWritableDatabase();

        //create a list of fake guests
        FakeDataFactory.insertFakeData(mDatabase);

        // Get All Guests From The Database
        Cursor cursor = getAllGuests();
        mAdapter.swapCursor(cursor);
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
        }).attachToRecyclerView(allGuestsListView);

    }

    /**
     * Removes the record with the specified id
     * @param id the DB id to be removed
     * @return True: if removed successfully, False: if failed
     */
    private boolean removeGuest(long id) {
        return mDatabase.delete(TABLE_NAME, _ID + "=" + id, null) > 0;
    }

    /**
     * Query the mDatabase and get all guests from the waitlist table
     *
     * @return Cursor containing the list of guests
     */
    public Cursor getAllGuests() {
        return mDatabase.query(TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                COLUMN_TIMESTAMP);

    }


    public void addToWaitlist(View view) {
        if (personNameEditText.getText().length() == 0
                || partyCountEditText.getText().length() == 0) {
            return;
        }

        int partySize = 1;
        try {
            //mNewPartyCountEditText inputType="number", so this should always work
            partySize = Integer.parseInt(partyCountEditText.getText().toString());
        } catch (NumberFormatException ex) {
            Log.e(TAG, "Failed to parse party size text to number: " + ex.getMessage());
        }

        // Add guest info to mDb
        addNewGuest(personNameEditText.getText().toString(), partySize);

        // Update the cursor in the adapter to trigger UI to display the new list
        mAdapter.swapCursor(getAllGuests());

        //clear UI text fields
        partyCountEditText.clearFocus();
        personNameEditText.getText().clear();
        partyCountEditText.getText().clear();
    }
    /**
     * Adds a new guest to the mDb including the party count and the current timestamp
     *
     * @param name  Guest's name
     * @param partySize Number in party
     * @return id of new record added
     */
    private long addNewGuest(String name, int partySize) {
        ContentValues cv = new ContentValues();
        cv.put(WaitlistEntry.COLUMN_GUEST_NAME, name);
        cv.put(WaitlistEntry.COLUMN_PARTY_SIZE, partySize);
        return mDatabase.insert(WaitlistEntry.TABLE_NAME, null, cv);
    }

}
