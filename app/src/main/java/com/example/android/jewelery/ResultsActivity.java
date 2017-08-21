package com.example.android.jewelery;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.android.jewelery.data.CalculateData;
import com.example.android.jewelery.data.MainData;
import com.example.android.jewelery.databinding.ActivityResultsBinding;
import com.example.android.jewelery.db.DbHelper;
import com.example.android.jewelery.db.HistoryReaderContract;
import com.example.android.jewelery.db.InsertDataHelper;
import com.example.android.jewelery.details.DetailsActivity;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ResultsActivity extends AppCompatActivity{


    @BindView(R.id.text_weight_results) TextView resultsWeight;
    @BindView(R.id.text_ava_weight_results) TextView avaWeight;
    @BindView(R.id.text_copper_results_label) TextView resultsCopperLabel;
    @BindView(R.id.text_copper_results) TextView resultsCopper;
    @BindView(R.id.text_silver_results_label) TextView resultsSilverLabel;
    @BindView(R.id.text_silver_results) TextView resultsSilver;

    MainData data = new MainData();

    private SQLiteDatabase mDb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);

        getData();
        calculateResult();
        displayData();

        mDb = new DbHelper(this).getWritableDatabase();
        Cursor cursor = mDb.query(
                HistoryReaderContract.HistoryInputEntry.TABLE_NAME,
                null, null, null, null, null, null, null);

        if (cursor.getCount() > 14) {
            InsertDataHelper.deleteOneRow(mDb);
        }

        InsertDataHelper.insertHistoryData(mDb, data);
        InsertDataHelper.insertResultData(mDb, data);
    }

    @Override
    protected void onStop() {
        super.onStop();
        displayData();
    }

    private void displayData() {
        resultsWeight.setText(String.format(Locale.getDefault(), "%.2f", data.finalWeight));
        avaWeight.setText(String.valueOf(data.avaWeight));

        resultsCopperLabel.setText(data.finalCopper > 0 ? "Добавить меди" : "Убрать меди");
        resultsCopper.setText(String.format(Locale.getDefault(), "%.2f", data.finalCopper));

        resultsSilverLabel.setText(data.finalSilver > 0 ? "Добавить серебра" : "Убрать серебра");
        resultsSilver.setText(String.format(Locale.getDefault(), "%.2f", data.finalSilver));
    }

    private void getData() {
        SharedPreferences mainPreferences = getSharedPreferences(MainActivity.PREF_AVA, 0);
        data.avaWeight = mainPreferences.getFloat("avaWeight", 6);
        data.avaProba = mainPreferences.getFloat("avaProba", 750);
        data.avaColor = mainPreferences.getString("avaColor", "Красный");

        SharedPreferences extraPreferences = getSharedPreferences(ExtraActivity.PREF_ADD_AND_DESIRED_DATA, 0);
        data.extraWeight = extraPreferences.getFloat("addWeight", 6);
        data.extraProba = extraPreferences.getFloat("addProba", 999.9f);
        data.desiredProba = extraPreferences.getFloat("desiredProba", 850);
        data.desiredColor = extraPreferences.getString("desiredColor", "Красный");
    }

    private void calculateResult() {
        // Определяем содержание высокопробного метелла.
        data.avaHighWeight = CalculateData.
                getHighWeight(data.avaWeight, data.avaProba, data.extraProba);
        // Определяем содержание лигатуры.
        data.avaLigature = data.avaWeight - data.avaHighWeight;
        // Определяем содержание серебра и меди в лигатуре.
        try {
            data.avaCopperPercent = CalculateData.getAvaCoppperPercent(data.avaColor);
            data.avaSilverPercent = CalculateData.getAvaSilverPercent(data.avaColor);
        } catch (Exception e) {
            e.printStackTrace();
        }

        data.avaCopper = data.avaLigature * data.avaCopperPercent;
        data.avaSilver = data.avaLigature * data.avaSilverPercent;

        // Определяем общий вес высокопробного металла.
        data.totalHighWeight = data.extraWeight + data.avaHighWeight;
        // Если нам нужно получить более высокую пробу, то мы повышаем
        if (data.avaProba < data.desiredProba) {
            data.totalHighWeight += CalculateData.getPromotedWeight(
                            data.avaWeight,
                            data.avaProba,
                            data.desiredProba,
                            data.extraProba);
        }
        // Определяем итоговый вес с лигатурой.
        data.finalWeight = data.totalHighWeight * data.extraProba / data.avaProba;
        // Определяем содержание лигатуры в итоговом весе.
        data.finalLigature = data.finalWeight - data.totalHighWeight;
        // Определяем процентное содержание серебра и меди в итоговой лигатуре.
        try {
            data.finalCopperPercent = CalculateData.getAvaCoppperPercent(data.desiredColor);
            data.finalSilverPercent = CalculateData.getAvaSilverPercent(data.desiredColor);
        } catch (Exception e) {
            e.printStackTrace();
        }

        data.finalCopper = (data.finalLigature * data.finalCopperPercent) - data.avaCopper;
        data.finalSilver = (data.finalLigature * data.finalSilverPercent) - data.avaSilver;

    }

    public void returnToMainActivity(View view) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }
}
