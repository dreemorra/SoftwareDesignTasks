package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class EditNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_note);
        boolean isExist = getIntent().getExtras().getBoolean("isExist");
        if (isExist) {
            long pos = getIntent().getExtras().getLong("id");
            EditText titleEdit = findViewById(R.id.enter_title);
            EditText contentEdit = findViewById(R.id.enter_content);
            titleEdit.setText("Title " + pos);
            contentEdit.setText("Content " + pos);
        }
    }
}
