package com.example.android.jewelery.history;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.jewelery.R;
import com.example.android.jewelery.data.TestUtil;
import com.example.android.jewelery.db.HistoryDbHelper;
import com.example.android.jewelery.db.HistoryReaderContract;


public class HistoryActivity extends AppCompatActivity {

    private static final int NUM_LIST_ITEMS = 15;

    HistoryAdapter mAdapter;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        RecyclerView HistoryRecyclerView;
        HistoryRecyclerView = (RecyclerView) findViewById(R.id.history_list_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        HistoryRecyclerView.setLayoutManager(layoutManager);
        HistoryRecyclerView.setHasFixedSize(true);


        HistoryDbHelper dbHelper = new HistoryDbHelper(this);
        mDb = dbHelper.getWritableDatabase();


        TestUtil.insertFakeData(mDb);

        Cursor allHistory = getAllHistory();
        mAdapter = new HistoryAdapter(this, allHistory.getCount());
        HistoryRecyclerView.setAdapter(mAdapter);
    }

    private Cursor getAllHistory() {
        return mDb.query(
                HistoryReaderContract.HistoryInputEntry.TABLE_NAME,
                null, null, null, null, null, null
        );
    }


}
