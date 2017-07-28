package com.example.android.jewelery;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.jewelery.data.MainData;
import com.example.android.jewelery.history.HistoryActivity;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView mAvaWeightTextView;
    private AutoCompleteTextView mAvaProbaTextView;
    private Spinner mColor;

    public static final String PREFS_NAME = "AvaPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mColor = (Spinner) findViewById(R.id.avaColorspinner);
        // Create an ArrayAdapter using string array and default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.colors_array, R.layout.support_simple_spinner_dropdown_item);

        mColor.setAdapter(adapter);

        mAvaWeightTextView = (AutoCompleteTextView) findViewById(R.id.avaWeight);
        mAvaProbaTextView = (AutoCompleteTextView) findViewById(R.id.avaProba);


        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, 0);

        float avaWeight = preferences.getFloat("avaWeight", 6.0f);
        float avaProba = preferences.getFloat("avaProba", 750.0f);
        String spinnerColor = preferences.getString("avaColor", "Красный");

        mAvaWeightTextView.setText(String.valueOf(avaWeight));
        mAvaProbaTextView.setText(String.valueOf(avaProba));
        mColor.setSelection(adapter.getPosition(spinnerColor));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemId = item.getItemId();
        if (menuItemId == R.id.action_history) {

            Intent historyIntent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(historyIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        getMainData();
    }

    private void getMainData() {

        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = preferences.edit();
        float avaWeight = Float.valueOf(mAvaWeightTextView.getText().toString());
        editor.putFloat("avaWeight", avaWeight);

        float avaProba = Float.valueOf(mAvaProbaTextView.getText().toString());
        editor.putFloat("avaProba", avaProba);
        String spinnerColor = mColor.getSelectedItem().toString();
        editor.putString("avaColor", spinnerColor);

        editor.commit();
    }


    public void testExtraLayoutShowing(View view) {
        getMainData();
        Intent extraIntent = new Intent(MainActivity.this, ExtraActivity.class);
        startActivity(extraIntent);
    }
}
