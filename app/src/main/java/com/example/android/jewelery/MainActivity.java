package com.example.android.jewelery;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.jewelery.data.MainData;
import com.example.android.jewelery.history.HistoryActivity;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView mAvaWeightTextView;
    private AutoCompleteTextView mAvaProbaTextView;
    private Spinner mColor;
    private Button mButton;

    public static final String PREF_AVA = "PREF_AVAILABLE_DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);

        mColor = (Spinner) findViewById(R.id.spinner_ava_color);

        // Create an ArrayAdapter using string array and default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.colors_array,
                R.layout.support_simple_spinner_dropdown_item);

        mColor.setAdapter(adapter);

        mAvaWeightTextView = (AutoCompleteTextView) findViewById(R.id.text_ava_weight);
        mAvaProbaTextView = (AutoCompleteTextView) findViewById(R.id.text_ava_proba);
        mButton = (Button) findViewById(R.id.button);


        SharedPreferences preferences = getSharedPreferences(PREF_AVA, 0);

        float avaWeight = preferences.getFloat("avaWeight", 6.0f);
        float avaProba = preferences.getFloat("avaProba", 750.0f);
        String spinnerColor = preferences.getString("avaColor", "Красный");

        mAvaWeightTextView.setText(String.valueOf(avaWeight));
        mAvaProbaTextView.setText(String.valueOf(avaProba));
        mColor.setSelection(adapter.getPosition(spinnerColor));

    }

    /** This method inflate menu options
     * @param menu
     * @return true if successfull
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        for (int i = 0; i < menu.size(); i++) {
            Drawable drawable = menu.getItem(i).getIcon();
            if (drawable != null) {
                drawable.mutate();
                drawable.setColorFilter(getResources().getColor(R.color.secondary_text), PorterDuff.Mode.SRC_ATOP);
            }
        }
        return true;
    }

    /** This method handel item selection in the menu
     * @param item - item selected
     * @return
     */
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

    private boolean getMainData() {
        SharedPreferences preferences = getSharedPreferences(PREF_AVA, 0);
        SharedPreferences.Editor editor = preferences.edit();
        try {
            float avaWeight = Float.valueOf(mAvaWeightTextView.getText().toString());
            editor.putFloat("avaWeight", avaWeight);
        } catch (NumberFormatException ex) {
            Toast.makeText(this, "Недопустимое значение в поле вес", Toast.LENGTH_SHORT).show();
            editor.commit();
            return false;
        }

        try {
            float avaProba = Float.valueOf(mAvaProbaTextView.getText().toString());
            editor.putFloat("avaProba", avaProba);
        } catch (NumberFormatException ex) {
            Toast.makeText(this, "Недопустимое значение в поле проба", Toast.LENGTH_SHORT).show();
            editor.commit();
            return false;
        }
        String spinnerColor = mColor.getSelectedItem().toString();
        editor.putString("avaColor", spinnerColor);

        editor.commit();
        return true;
    }

    public void startExtraActivity(View view) {
        if (getMainData()) {
            Intent extraIntent = new Intent(MainActivity.this, ExtraActivity.class);
            startActivity(extraIntent);
        }
    }
}
