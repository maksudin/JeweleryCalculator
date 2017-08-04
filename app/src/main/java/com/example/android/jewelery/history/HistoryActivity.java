package com.example.android.jewelery.history;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.jewelery.R;
import com.example.android.jewelery.data.TestUtil;
import com.example.android.jewelery.db.HistoryDbHelper;
import com.example.android.jewelery.db.HistoryReaderContract;


public class HistoryActivity extends AppCompatActivity {

    private static final int NUM_LIST_ITEMS = 15;

    HistoryAdapter mAdapter;
    private SQLiteDatabase mDb;
    private RecyclerView mHistoryRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mHistoryRecyclerView = (RecyclerView) findViewById(R.id.history_list_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mHistoryRecyclerView.setLayoutManager(layoutManager);
        mHistoryRecyclerView.setHasFixedSize(true);

        mDb = new HistoryDbHelper(this).getWritableDatabase();
//        TestUtil.insertFakeData(mDb);
//        insertData();

        Cursor cursor = getAllHistory();
        mAdapter = new HistoryAdapter(this, cursor);
        mHistoryRecyclerView.setAdapter(mAdapter);
    }

    private Cursor getAllHistory() {
        return mDb.query(
                HistoryReaderContract.HistoryInputEntry.TABLE_NAME,
                null, null, null, null, null,
                HistoryReaderContract.HistoryInputEntry._ID + " DESC");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.history, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemId = item.getItemId();
        if (menuItemId == R.id.action_clear_history) {
//            Toast.makeText(this, "HI", Toast.LENGTH_SHORT).show();
            HistoryInsertDataHelper.deleteAllRows(mDb);
            Cursor cursor = getAllHistory();
            mAdapter = new HistoryAdapter(this, cursor);
            mHistoryRecyclerView.setAdapter(mAdapter);
            mHistoryRecyclerView.invalidate();

        }
        return super.onOptionsItemSelected(item);
    }
}
