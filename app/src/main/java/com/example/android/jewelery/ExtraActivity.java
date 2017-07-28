package com.example.android.jewelery;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * Created by milju on 7/19/2017.
 */

public class ExtraActivity extends AppCompatActivity{

    private AutoCompleteTextView eAddWeightTextView;
    private AutoCompleteTextView eAddProbaTextView;

    private AutoCompleteTextView eDesiredProbaTextView;
    private Spinner eDesiredColor;

    public static final String PREFS_NAME = "AddAndDesiredPrefsFile";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.extra_main);

        eDesiredColor = (Spinner) findViewById(R.id.desiredColorSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.colors_array, R.layout.support_simple_spinner_dropdown_item);

        eDesiredColor.setAdapter(adapter);
        eAddWeightTextView = (AutoCompleteTextView) findViewById(R.id.addWeight);
        eAddProbaTextView = (AutoCompleteTextView) findViewById(R.id.addProba);

        eDesiredProbaTextView = (AutoCompleteTextView) findViewById(R.id.desiredProba);


        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, 0);

        float addWeight = preferences.getFloat("addWeight", 6);
        float addProba = preferences.getFloat("addProba", 999.9f);

        float desiredProba = preferences.getFloat("desiredProba", 850);
        String spinnerColor = preferences.getString("desiredColor", "Красный");

        eAddWeightTextView.setText(String.valueOf(addWeight));
        eAddProbaTextView.setText(String.valueOf(addProba));

        eDesiredProbaTextView.setText(String.valueOf(desiredProba));
        eDesiredColor.setSelection(adapter.getPosition(spinnerColor));

    }

    @Override
    protected void onStop() {
        super.onStop();
        getExtraData();
    }

    private void getExtraData() {

        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = preferences.edit();

        float addWeight = Float.valueOf(eAddWeightTextView.getText().toString());
        editor.putFloat("addWeight", addWeight);
        float addProba = Float.valueOf(eAddProbaTextView.getText().toString());
        editor.putFloat("addProba", addProba);
        float desiredProba = Float.valueOf(eDesiredProbaTextView.getText().toString());
        editor.putFloat("desiredProba", desiredProba);

        String spinnerColor = eDesiredColor.getSelectedItem().toString();
        editor.putString("desiredColor", spinnerColor);

        editor.commit();
    }

    public void testResultsLayoutShowing(View view) {
        Intent resultsIntent = new Intent(ExtraActivity.this, ResultsActivity.class);
        getExtraData();
        startActivity(resultsIntent);
    }
}
