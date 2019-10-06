package com.example.lab1.debug;

import android.os.Build;
import android.os.Bundle;

import com.example.lab1.BuildConfig;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.example.lab1.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView androidVersionView = findViewById(R.id.textAndroid);
        androidVersionView.setText(String.format(Locale.getDefault(), "Android version: %s(%d)", Build.VERSION.RELEASE, Build.VERSION.SDK_INT));

        TextView versionName = findViewById(R.id.textName);
        versionName.setText(String.format(Locale.getDefault(), "Program version: %s", BuildConfig.VERSION_NAME));

        TextView versionCode = findViewById(R.id.textCode);
        versionCode.setText(String.format(Locale.getDefault(), "Code version: %s", BuildConfig.VERSION_CODE));

    }

}
