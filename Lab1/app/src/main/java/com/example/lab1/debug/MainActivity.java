package com.example.lab1.debug;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.example.lab1.BuildConfig;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.example.lab1.R;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.provider.Settings.Secure;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView androidVersionView = findViewById(R.id.textAndroid);
        TextView versionName = findViewById(R.id.textName);
        TextView versionCode = findViewById(R.id.textCode);
        TextView deviceID = findViewById(R.id.deviceID);

        checkForPermission(deviceID);

        androidVersionView.setText(String.format(Locale.getDefault(), "Android version: %s(%d)", Build.VERSION.RELEASE, Build.VERSION.SDK_INT));
        versionName.setText(String.format(Locale.getDefault(), "Program version: %s", BuildConfig.VERSION_NAME));
        versionCode.setText(String.format(Locale.getDefault(), "Code version: %s", BuildConfig.VERSION_CODE));


    }

    private void checkForPermission(TextView view) {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE)) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                alertBuilder.setCancelable(true);
                alertBuilder.setTitle("This permission is necessary");
                alertBuilder.setMessage("If you wanna see your device ID, please, click Allow");
                alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.READ_PHONE_STATE},
                                1);
                    }
                });

                AlertDialog alert = alertBuilder.create();
                alert.show();
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_PHONE_STATE},
                        1);
            }
        }
        else view.setText(String.format(Locale.getDefault(), "Device ID: %s", Secure.getString(getContentResolver(), "android_id")));
    }
}
