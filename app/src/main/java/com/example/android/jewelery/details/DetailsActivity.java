package com.example.android.jewelery.details;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.android.jewelery.R;
import com.example.android.jewelery.databinding.InputInfoBinding;
import com.example.android.jewelery.databinding.ResultsInfoBinding;
import com.example.android.jewelery.history.HistoryAdapter;

public class DetailsActivity extends AppCompatActivity implements HistoryAdapter.HistoryAdapterOnClickListener {


    private InputInfoBinding mInfoBinding;
    private ResultsInfoBinding mResultsBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }

    @Override
    public void onClick(int position) {

    }
}
