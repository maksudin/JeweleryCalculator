package com.example.android.jewelery;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.android.jewelery.data.CalculateData;
import com.example.android.jewelery.data.MainData;
import com.example.android.jewelery.databinding.ResultsBinding;

/**
 * Created by milju on 7/19/2017.
 */

public class ResultsActivity extends AppCompatActivity{


    ResultsBinding rBinding;
    MainData data = new MainData();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);

        rBinding = DataBindingUtil.setContentView(this, R.layout.results);

        getData();
        calculateResult();
        displayData();
    }

    private void displayData() {
        rBinding.resultsWeight.setText(String.valueOf(data.finalWeight));
        rBinding.resultsAvaWeightMetal.setText(String.valueOf(data.avaWeight));

        if (data.avaCopperPercent < data.finalCopperPercent) {
            rBinding.resultsCopperLabel.setText("Добавить меди");
        } else {
            rBinding.resultsCopperLabel.setText("Убрать меди");
        }
        rBinding.resultsCopper.setText(String.valueOf(data.finalCopper));

        if (data.avaSilverPercent < data.finalSilverPercent) {
            rBinding.resultsSilverLabel.setText("Добавить серебра");
        } else {
            rBinding.resultsSilverLabel.setText("Убрать серебра");
        }
        rBinding.resultsSilver.setText(String.valueOf(data.finalSilver));
    }

    private void getData() {
        SharedPreferences mainPreferences = getSharedPreferences(MainActivity.PREFS_NAME, 0);
        data.avaWeight = mainPreferences.getFloat("avaWeight", 6);
        data.avaProba = mainPreferences.getFloat("avaProba", 750);
        data.avaColor = mainPreferences.getString("avaColor", "Красный");

        SharedPreferences extraPreferences = getSharedPreferences(ExtraActivity.PREFS_NAME, 0);
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


    public void testMainLayout(View view) {
        Intent mainIntent = new Intent(ResultsActivity.this, MainActivity.class);
        startActivity(mainIntent);
    }
}
