package com.example.lab3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class EditNoteActivity extends AppCompatActivity {

    long id;
    int pos;
    boolean isExist;
    Note note;
    EditText titleEdit, contentEdit, tagsEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_note);

        isExist = getIntent().getExtras().getBoolean("isExist");
        id = getIntent().getLongExtra("ID", -1);
        pos = getIntent().getIntExtra("pos", -1);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        titleEdit = findViewById(R.id.enter_title);
        contentEdit = findViewById(R.id.enter_content);
        tagsEdit = findViewById(R.id.enter_tags);

        if (isExist) {
            note = Note.findById(Note.class, id);
        }
        else {
            note = new Note(titleEdit.getText().toString(), null, contentEdit.getText().toString());
        }
        note.save();
        id = note.getId();
        titleEdit.setText(note.Title);
        contentEdit.setText(note.Description);
        tagsEdit.setText(note.Tags);

        Intent returnIntent = new Intent(this, MainActivity.class);
        returnIntent.putExtra("ID", id);
        if (pos >= 0)
            returnIntent.putExtra("pos", pos);
        setResult(Activity.RESULT_OK, returnIntent);
    }

    public void saveData() {
        Note note;
        titleEdit = findViewById(R.id.enter_title);
        contentEdit = findViewById(R.id.enter_content);
        tagsEdit = findViewById(R.id.enter_tags);

        note = Note.findById(Note.class, id);
        note.Title = titleEdit.getText().toString();
        note.Description = contentEdit.getText().toString();
        note.Tags = tagsEdit.getText().toString();
        note.save();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home: {
                saveData();
                onBackPressed();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveData();
    }

}
