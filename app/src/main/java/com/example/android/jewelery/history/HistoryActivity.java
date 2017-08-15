package com.example.android.jewelery.history;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.jewelery.R;
import com.example.android.jewelery.db.DbHelper;
import com.example.android.jewelery.db.HistoryReaderContract;
import com.example.android.jewelery.db.InsertDataHelper;
import com.example.android.jewelery.details.DetailsActivity;

import java.util.ArrayList;
import java.util.List;


public class HistoryActivity extends AppCompatActivity implements HistoryAdapter.HistoryAdapterOnClickListener {

//    private static final int NUM_LIST_ITEMS = 15;

    private HistoryAdapter mAdapter;
    private SQLiteDatabase mDb;
    private RecyclerView mHistoryRecyclerView;
    private List<Integer> mIdList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mHistoryRecyclerView = (RecyclerView) findViewById(R.id.history_list_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mHistoryRecyclerView.setLayoutManager(layoutManager);
        mHistoryRecyclerView.setHasFixedSize(true);

        mDb = new DbHelper(this).getWritableDatabase();
        Cursor cursor = getAllHistory();
        mAdapter = new HistoryAdapter(this, cursor, this, mIdList);
        mHistoryRecyclerView.setAdapter(mAdapter);
    }


    /**
     * This method gets all the data in HistoryInput table in descending order
     * @return - returns a cursor
     */
    private Cursor getAllHistory() {
        return mDb.query(
                HistoryReaderContract.HistoryInputEntry.TABLE_NAME,
                null, null, null, null, null,
                HistoryReaderContract.HistoryInputEntry.COLUMN_ID + " DESC");
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
            InsertDataHelper.deleteAllRows(mDb);
            Cursor cursor = getAllHistory();
            mAdapter = new HistoryAdapter(this, cursor, this, mIdList);
            mHistoryRecyclerView.setAdapter(mAdapter);
            mHistoryRecyclerView.invalidate();

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(int position) {
        Intent intent = new Intent(this, DetailsActivity.class);
        mIdList = mAdapter.getIdList();
        intent.putExtra("inputId", mIdList.get(position));
        startActivity(intent);
    }
}
