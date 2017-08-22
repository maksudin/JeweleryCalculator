package com.example.android.jewelery;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * Created by milju on 7/19/2017.
 */

public class ExtraActivity extends AppCompatActivity{

    private AutoCompleteTextView mAddWeightTextView;
    private AutoCompleteTextView mAddProbaTextView;

    private AutoCompleteTextView mDesiredProbaTextView;
    private Spinner mDesiredColor;

    public static final String PREF_ADD_AND_DESIRED_DATA = "PREF_ADD_AND_DESIRED";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mDesiredColor = (Spinner) findViewById(R.id.spinner_desired_color);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.colors_array,
                R.layout.support_simple_spinner_dropdown_item);

        mDesiredColor.setAdapter(adapter);
        mAddWeightTextView = (AutoCompleteTextView) findViewById(R.id.text_add_weight);
        mAddProbaTextView = (AutoCompleteTextView) findViewById(R.id.text_add_proba);

        mDesiredProbaTextView = (AutoCompleteTextView) findViewById(R.id.text_desired_proba);


        SharedPreferences preferences = getSharedPreferences(PREF_ADD_AND_DESIRED_DATA, 0);

        float addWeight = preferences.getFloat("addWeight", 6);
        float addProba = preferences.getFloat("addProba", 999.9f);

        float desiredProba = preferences.getFloat("desiredProba", 850);
        String spinnerColor = preferences.getString("desiredColor", "Красный");

        mAddWeightTextView.setText(String.valueOf(addWeight));
        mAddProbaTextView.setText(String.valueOf(addProba));

        mDesiredProbaTextView.setText(String.valueOf(desiredProba));
        mDesiredColor.setSelection(adapter.getPosition(spinnerColor));

        // Display go back arrow in action bar.


    }

    @Override
    protected void onStop() {
        super.onStop();
        getExtraData();
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

    private boolean getExtraData() {

        SharedPreferences preferences = getSharedPreferences(PREF_ADD_AND_DESIRED_DATA, 0);
        SharedPreferences.Editor editor = preferences.edit();

        try {
            float addWeight = Float.valueOf(mAddWeightTextView.getText().toString());
            editor.putFloat("addWeight", addWeight);
        } catch (NumberFormatException ex) {
            Toast.makeText(this, "Недопустимое значение в поле вес", Toast.LENGTH_SHORT).show();
            editor.apply();
            return false;
        }
        try {
            float addProba = Float.valueOf(mAddProbaTextView.getText().toString());
            editor.putFloat("addProba", addProba);
        } catch (NumberFormatException ex) {
            Toast.makeText(this, "Недопустимое значение в поле имещиеся проба", Toast.LENGTH_SHORT).show();
            editor.commit();
            return false;
        }
        try {
            float desiredProba = Float.valueOf(mDesiredProbaTextView.getText().toString());
            editor.putFloat("desiredProba", desiredProba);
        } catch (NumberFormatException ex) {
            Toast.makeText(this, "Недопустимое значение в поле желаемая проба", Toast.LENGTH_SHORT).show();
            editor.commit();
            return false;
        }

        String spinnerColor = mDesiredColor.getSelectedItem().toString();
        editor.putString("desiredColor", spinnerColor);

        editor.commit();
        return true;
    }

    public void testResultsLayoutShowing(View view) {
        if (!getExtraData()) return;
        Intent resultsIntent = new Intent(ExtraActivity.this, ResultsActivity.class);
        startActivity(resultsIntent);
    }
}
