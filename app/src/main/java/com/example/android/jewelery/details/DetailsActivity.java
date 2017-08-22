package com.example.android.jewelery.details;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.android.jewelery.R;
import com.example.android.jewelery.db.DbHelper;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.jewelery.db.HistoryReaderContract.*;

public class DetailsActivity extends AppCompatActivity {

//  Input text views. {
    @BindView(R.id.text_ava_weight) TextView mAvaWeight;
    @BindView(R.id.text_ava_proba) TextView mAvaProba;
    @BindView(R.id.text_ava_color) TextView mAvaColor;
    @BindView(R.id.text_add_weight) TextView mAddWeight;
    @BindView(R.id.text_add_proba) TextView mAddProba;
    @BindView(R.id.text_desired_proba) TextView mDesiredProba;
    @BindView(R.id.text_desired_color) TextView mDesiredColor;
// }

//  Result text views. {
    @BindView(R.id.text_weight_results) TextView mFinalWeight;
    @BindView(R.id.text_ava_weight_results) TextView mResultsAvaWeight;
    @BindView(R.id.text_ava_weight_results_label) TextView mResultsAvaWeightLabel;
    @BindView(R.id.text_copper_results_label) TextView mCopperLabel;
    @BindView(R.id.text_copper_results) TextView mCopper;
    @BindView(R.id.text_silver_results_label) TextView mSilverLabel;
    @BindView(R.id.text_silver_results) TextView mSilver;
// }



    private SQLiteDatabase mDb;
    private Cursor mInputCursor;
    private Cursor mResultsCursor;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        final int INPUT_ID = intent.getIntExtra("inputId", -1);

        mDb = new DbHelper(this).getReadableDatabase();

        mInputCursor = getInputInfo(INPUT_ID);
        mResultsCursor = getResultsInfo(INPUT_ID);
        showInputInfo();
        showResultsInfo();
    }

    private void showInputInfo() {

        mInputCursor.moveToFirst();
        float  avaWeight = mInputCursor.getFloat(
                mInputCursor.getColumnIndex(HistoryInputEntry.COLUMN_INPUT_AVA_WEIGHT)
        );
        float  avaProba = mInputCursor.getFloat(
                mInputCursor.getColumnIndex(HistoryInputEntry.COLUMN_INPUT_AVA_PROBA)
        );
        String  avaColor = mInputCursor.getString(
                mInputCursor.getColumnIndex(HistoryInputEntry.COLUMN_INPUT_AVA_COLOR)
        );
        float  addWeight = mInputCursor.getFloat(
                mInputCursor.getColumnIndex(HistoryInputEntry.COLUMN_INPUT_ADD_WEIGHT)
        );
        float  addProba = mInputCursor.getFloat(
                mInputCursor.getColumnIndex(HistoryInputEntry.COLUMN_INPUT_ADD_PROBA)
        );
        float  desiredProba = mInputCursor.getFloat(
                mInputCursor.getColumnIndex(HistoryInputEntry.COLUMN_INPUT_DESIRED_PROBA)
        );
        String  desiredColor = mInputCursor.getString(
                mInputCursor.getColumnIndex(HistoryInputEntry.COLUMN_INPUT_DESIRED_COLOR)
        );
        mAvaWeight.setText(String.valueOf(avaWeight));
        mAvaProba.setText(String.valueOf(avaProba));
        mAvaColor.setText(avaColor);
        mAddWeight.setText(String.valueOf(addWeight));
        mAddProba.setText(String.valueOf(addProba));
        mDesiredProba.setText(String.valueOf(desiredProba));
        mDesiredColor.setText(desiredColor);


        mInputCursor.close();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showResultsInfo() {
        mResultsCursor.moveToFirst();

        float finalWeight = mResultsCursor.getFloat(
                mResultsCursor.getColumnIndex(HistoryResultEntry.COLUMN_RESULT_FINAL_WEIGHT)
        );
        float finalCopper = mResultsCursor.getFloat(
                mResultsCursor.getColumnIndex(HistoryResultEntry.COLUMN_RESULT_FINAL_COPPER)
        );
        float finalSilver = mResultsCursor.getFloat(
                mResultsCursor.getColumnIndex(HistoryResultEntry.COLUMN_RESULT_FINAL_SILVER)
        );

        mFinalWeight.setText(String.format(Locale.getDefault(), "%.2f", finalWeight));
        mResultsAvaWeight.setVisibility(View.GONE);
        mResultsAvaWeightLabel.setVisibility(View.GONE);
        mCopperLabel.setText(finalCopper > 0 ? "Добавить меди" : "Убрать меди");
        mSilverLabel.setText(finalSilver > 0 ? "Добавить серебра" : "Убрать серебра");
        mCopper.setText(String.format(Locale.getDefault(), "%.2f", finalCopper));
        mSilver.setText(String.format(Locale.getDefault(), "%.2f", finalSilver));
        mResultsCursor.close();


    }


    private Cursor getInputInfo(int inputId) {

        return mDb.rawQuery("SELECT * FROM " + HistoryInputEntry.TABLE_NAME
                + " WHERE TRIM(" + HistoryInputEntry.COLUMN_ID + ") = '" + String.valueOf(inputId).trim() + "'", null);
    }

    private Cursor getResultsInfo(int id) {
        return mDb.rawQuery("SELECT * FROM " + HistoryResultEntry.TABLE_NAME
                + " WHERE TRIM(" + HistoryResultEntry.COLUMN_ID + ") = '" + String.valueOf(id).trim() + "'", null);
    }


}
