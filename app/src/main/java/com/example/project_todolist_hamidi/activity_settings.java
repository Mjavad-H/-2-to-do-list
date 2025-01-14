package com.example.project_todolist_hamidi;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class activity_settings extends AppCompatActivity {

    private SeekBar fontSizeSeekBar;
    private Spinner languageSpinner;
    private TextView fontSizeLabel, languageLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        fontSizeSeekBar = findViewById(R.id.fontSizeSeekBar);
        languageSpinner = findViewById(R.id.languageSpinner);
        fontSizeLabel = findViewById(R.id.fontSizeLabel);
        languageLabel = findViewById(R.id.languageLabel);

        // تغییر اندازه فونت
        fontSizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float newSize = 14 + progress; // اندازه فونت پیش‌فرض: 14 + مقدار SeekBar
                fontSizeLabel.setTextSize(newSize);
                languageLabel.setTextSize(newSize);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // تغییر زبان
        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedLanguage = parent.getItemAtPosition(position).toString();
                changeLanguage(selectedLanguage);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void changeLanguage(String language) {
        Locale locale;
        if (language.equals("English")) {
            locale = new Locale("en");
        } else {
            locale = new Locale("fa");
        }

        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());

        // ری‌استارت activity برای اعمال تغییرات
        Intent intent = new Intent(this, activity_settings.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}