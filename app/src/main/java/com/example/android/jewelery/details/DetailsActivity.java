package com.example.android.jewelery.details;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.android.jewelery.R;
import com.example.android.jewelery.databinding.InputInfoBinding;
import com.example.android.jewelery.databinding.ResultsInfoBinding;
import com.example.android.jewelery.db.DbHelper;
import com.example.android.jewelery.db.HistoryReaderContract;
import com.example.android.jewelery.history.HistoryAdapter;

import java.util.List;

import static com.example.android.jewelery.db.HistoryReaderContract.*;

public class DetailsActivity extends AppCompatActivity {


    private InputInfoBinding mInfoBinding;
    private ResultsInfoBinding mResultsBinding;

    private SQLiteDatabase mDb;
    private Cursor mCursor;

    final private int POS = getIntent().getExtras().getInt("pos", 999);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mDb = new DbHelper(this).getReadableDatabase();

        mCursor = getAllInfo();
        showInfo();
    }

    private void showInfo() {

        List<Integer> idList = HistoryAdapter.getmIdList();

        if (idList == null) return;

        if (mCursor.moveToFirst()) {

            do {

                int id = mCursor.getInt(mCursor.getColumnIndex(HistoryInputEntry._ID));

                if (id == idList.get(POS)) {

                    float avaWeight = mCursor.getFloat(
                            mCursor.getColumnIndex(HistoryInputEntry.COLUMN_INPUT_AVA_WEIGHT)
                    );
                    mInfoBinding.textAvaWeight.setText(String.valueOf(avaWeight));

                    float avaProba = mCursor.getFloat(
                            mCursor.getColumnIndex(HistoryInputEntry.COLUMN_INPUT_AVA_PROBA)
                    );
                    mInfoBinding.textAvaProba.setText(String.valueOf(avaProba));

                    String avaColor = mCursor.getString(
                            mCursor.getColumnIndex(HistoryInputEntry.COLUMN_INPUT_AVA_COLOR)
                    );
                    mInfoBinding.textAvaColor.setText(avaColor);

                    float addWeight = mCursor.getFloat(
                            mCursor.getColumnIndex(HistoryInputEntry.COLUMN_INPUT_ADD_WEIGHT)
                    );
                    mInfoBinding.textAddWeight.setText(String.valueOf(addWeight));

                    float addProba = mCursor.getFloat(
                            mCursor.getColumnIndex(HistoryInputEntry.COLUMN_INPUT_ADD_PROBA)
                    );
                    mInfoBinding.textAddProba.setText(String.valueOf(addProba));

                    float desiredProba = mCursor.getFloat(
                            mCursor.getColumnIndex(HistoryInputEntry.COLUMN_INPUT_DESIRED_PROBA)
                    );
                    mInfoBinding.textDesiredProba.setText(String.valueOf(desiredProba));

                    String desiredColor = mCursor.getString(
                            mCursor.getColumnIndex(HistoryInputEntry.COLUMN_INPUT_DESIRED_COLOR)
                    );
                    mInfoBinding.textDesiredColor.setText(String.valueOf(desiredColor));

                }
                while (mCursor.moveToNext()) ;

            }
        }
    }

    private Cursor getAllInfo() {

        return mDb.rawQuery("SELECT * FROM " + HistoryInputEntry.TABLE_NAME
                + " INNER JOIN " + HistoryResultEntry.TABLE_NAME + " ON "
                + HistoryInputEntry._ID + " = " + HistoryResultEntry._ID
                + " GROUP BY " + HistoryInputEntry._ID, null);
    }


}
