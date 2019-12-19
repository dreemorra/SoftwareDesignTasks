package com.example.lab3;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public RecyclerView recyclerView;
    private ArrayList<Note> noteList;
    private ArrayList<String> tagList;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);

        recyclerView = findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager;

        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT)
            layoutManager = new LinearLayoutManager(this);
        else
            layoutManager = new GridLayoutManager(this, 2);

        recyclerView.setLayoutManager(layoutManager);

        tagList = new ArrayList<>();
        noteList = (ArrayList<Note>) Note.listAll(Note.class);
        getTags();

        adapter = new NoteAdapter(noteList,
                (item, pos) -> {
                    Intent intent = new Intent(MainActivity.this, EditNoteActivity.class);
                    long id = item.getId();
                    intent.putExtra("pos", pos);
                    intent.putExtra("ID", id);
                    intent.putExtra("isExist", true);
                    MainActivity.this.startActivityForResult(intent, 1);
                },

                (item, position) -> {
            long id = item.getId();
            Note note;
            note = Note.findById(Note.class, id);
            noteList.remove(position);
            note.delete();
            adapter.notifyItemRemoved(position);
            return true;
        });

        recyclerView.setAdapter(adapter);

    }

    private void getTags() {
        for (Note elem : noteList) {
            List<String> tags = Arrays.asList(elem.Tags.split(", "));
            tagList.addAll(tags);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.search_menu_item));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
              ArrayList<Note> resultList = new ArrayList<>();

                if (!tagList.contains(newText)) {
                    adapter = new NoteAdapter(noteList,
                            (item, pos) -> {
                                Intent intent = new Intent(MainActivity.this, EditNoteActivity.class);
                                long id = item.getId();
                                intent.putExtra("pos", pos);
                                intent.putExtra("ID", id);
                                intent.putExtra("isExist", true);
                                MainActivity.this.startActivityForResult(intent, 1);
                            },
                            (item, position) -> {
                                long id = item.getId();
                                Note note;
                                note = Note.findById(Note.class, id);
                                noteList.remove(position);
                                note.delete();
                                adapter.notifyItemRemoved(position);
                                return true;
                            });
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    return false;
                }
                else {
                    for(Note elem : noteList) {
                        if (elem.Tags.contains(newText)) {
                            resultList.add(elem);
                        }
                    }
                    adapter = new NoteAdapter(resultList,
                            (item, pos) -> {
                                Intent intent = new Intent(MainActivity.this, EditNoteActivity.class);
                                long id = item.getId();
                                intent.putExtra("pos", pos);
                                intent.putExtra("ID", id);
                                intent.putExtra("isExist", true);
                                MainActivity.this.startActivityForResult(intent, 1);
                            },
                            (item, position) -> {
                                long id = item.getId();
                                Note note;
                                note = Note.findById(Note.class, id);
                                noteList.remove(position);
                                note.delete();
                                adapter.notifyItemRemoved(position);
                                return true;
                            });
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    return true;
                }
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.sort_by_title: {
                sortList(true);
                return true;
            }
            case R.id.sort_by_date: {
                sortList(false);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void sortList(boolean isTitle) {
        AlertDialog.Builder mySortAlertDialog = new AlertDialog.Builder(this);
        mySortAlertDialog.setTitle("Sort by");
        String[] r = {"Ascending", "Descending"};
        final String[] selected = {""};
        mySortAlertDialog.setSingleChoiceItems(r, -1,
                (dialog, which) -> selected[0] = r[which]);

        mySortAlertDialog.setPositiveButton("OK", (dialog, which) -> {
            if (isTitle) {
                if (selected[0].equals("Descending"))
                    noteList.sort((note1, note2) -> note2.Title.compareTo(note1.Title));
                else if (selected[0].equals("Ascending"))
                    noteList.sort((note1, note2) -> note1.Title.compareTo(note2.Title));
            }
            else {
                if (selected[0].equals("Descending"))
                    noteList.sort((note1, note2) -> note2.Created.compareTo(note1.Created));
                else if (selected[0].equals("Ascending"))
                    noteList.sort((note1, note2) -> note1.Created.compareTo(note2.Created));
            }

            adapter.notifyDataSetChanged();
        });
        mySortAlertDialog.create().show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab) {
            Intent intent = new Intent(this, EditNoteActivity.class);
            intent.putExtra("isExist", false);
            startActivityForResult(intent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        long noteId = data.getLongExtra("ID", -1);
        int prevPos = data.getIntExtra("pos", -1);
        Note note = Note.findById(Note.class, noteId);

        if (prevPos >= 0) {
            noteList.set(prevPos, note);
            adapter.notifyItemChanged(prevPos);
        }
        else {
            noteList.add(note);
            adapter.notifyItemInserted(noteList.size() - 1);
        }
        getTags();
    }
}

