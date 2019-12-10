package com.example.lab3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

public class EditNoteActivity extends AppCompatActivity {

    long pos;
    long id;
    boolean isExist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_note);
        Note note;

        isExist = getIntent().getExtras().getBoolean("isExist");
        id = getIntent().getLongExtra("ID", -1);
        pos = getIntent().getIntExtra("pos", -1);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        EditText titleEdit = findViewById(R.id.enter_title);
        EditText contentEdit = findViewById(R.id.enter_content);

        if (isExist) {
            note = Note.findById(Note.class, id);
        }
        else {
            note = new Note(titleEdit.getText().toString(), contentEdit.getText().toString());
        }
        note.save();
        id = note.getId();
        titleEdit.setText(note.Title);
        contentEdit.setText(note.Description);

        Intent returnIntent = new Intent(this, MainActivity.class);
        returnIntent.putExtra("ID", id);
        if (pos >= 0)
        {
            returnIntent.putExtra("pos", pos);
        }
        setResult(Activity.RESULT_OK, returnIntent);
    }

    public void saveData() {
        Note note;
        EditText titleEdit = findViewById(R.id.enter_title);
        EditText contentEdit = findViewById(R.id.enter_content);

        note = Note.findById(Note.class, id);
        note.Title = titleEdit.getText().toString();
        note.Description = contentEdit.getText().toString();
        note.save();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                saveData();
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveData();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveData();
        outState.putLong("pos", pos);
    }
}
