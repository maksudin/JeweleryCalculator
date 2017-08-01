package com.example.android.jewelery.history;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.jewelery.R;

/**
 * Created by milju on 7/28/2017.
 */

public class HistoryActivity extends AppCompatActivity {

    private static final int NUM_LIST_ITEMS = 15;

    HistoryAdapter historyAdapter;
    RecyclerView hNumbersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        hNumbersList = (RecyclerView) findViewById(R.id.history_data);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        hNumbersList.setLayoutManager(layoutManager);
        hNumbersList.setHasFixedSize(true);

        historyAdapter = new HistoryAdapter(NUM_LIST_ITEMS);

        hNumbersList.setAdapter(historyAdapter);


    }
}
