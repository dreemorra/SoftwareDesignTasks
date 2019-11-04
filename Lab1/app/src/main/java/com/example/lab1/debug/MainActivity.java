package com.example.lab1.debug;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.example.lab1.BuildConfig;
import com.example.lab1.R;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.provider.Settings.Secure;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    TextView deviceID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkForPermission();

        TextView androidVersionView = findViewById(R.id.textAndroid);
        TextView versionName = findViewById(R.id.textName);
        TextView versionCode = findViewById(R.id.textCode);
        deviceID = findViewById(R.id.deviceID);

        androidVersionView.setText(String.format(Locale.getDefault(), "Android version: %s(%d)", Build.VERSION.RELEASE, Build.VERSION.SDK_INT));
        versionName.setText(String.format(Locale.getDefault(), "Program version: %s", BuildConfig.VERSION_NAME));
        versionCode.setText(String.format(Locale.getDefault(), "Code version: %s", BuildConfig.VERSION_CODE));

        // Device ID is an unique for each user 64-bit hex string

    }

    private void checkForPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            // Call dialog with explanation why we need this permission
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_PHONE_STATE)) {
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
            }
            else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_PHONE_STATE},
                        1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        boolean flag;
        flag = true;
        if(requestCode == 1) {
            for (int i:grantResults) {
                if( i == PackageManager.PERMISSION_DENIED)
                    flag = false;
            }
            if (flag) {
                // An intent is an abstract description of an operation to be performed
                deviceID.setText(String.format(Locale.getDefault(), "Device ID: %s", Secure.getString(getContentResolver(), "android_id")));
            }
        }
    }

}
