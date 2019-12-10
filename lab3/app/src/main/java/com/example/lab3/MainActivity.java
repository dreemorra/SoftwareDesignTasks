package com.example.lab3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // TODO: implement delete
    // TODO: implement search
    // TODO: implement tags
    // TODO: implement landscape mode


    public RecyclerView recyclerView;
    private List<Note> noteList;
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
        recyclerView.addOnItemTouchListener(
            new RecyclerItemClickListener(this, (view, position) -> {
            Intent intent = new Intent(MainActivity.this, EditNoteActivity.class);
            long id = position+1;
            intent.putExtra("pos", position);
            intent.putExtra("ID", id);
            intent.putExtra("isExist", true);
            startActivityForResult(intent, 1);
            })
        );

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        noteList = Note.listAll(Note.class);
        adapter = new NoteAdapter(noteList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchView searchView = findViewById(R.id.search_menu_item);
//        searchView.isSubmitButtonEnabled();

//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                List<Tag> tagList = SugarRecord.find(Tag.class, "value = ?", query);
//                if (tagList.isEmpty())
//                {
//                    Toast.makeText(MainActivity.this, "No tag not found", Toast.LENGTH_SHORT).show();
//                    return false;
//
//                }
//                Tag tag = tagList.get(0);
//                long tagId = tag.getId();
//                m2mConnections = SugarRecord.find(Note.class, "tag = ?", String.valueOf(tagId));
//                if (m2mConnections.isEmpty())
//                {
//                    Toast.makeText(MainActivity.this, "No notes with this tag", Toast.LENGTH_SHORT).show();
//                    return false;
//                }
//
//                noteList.clear();
//
//                for (connection in m2mConnections)
//                {
//                    Note note = SugarRecord.findById(Note.class, connection.noteId);
//                    noteList.add(note);
//                }
//                adapter.notifyDataSetChanged();
//                return true;

//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
            {
                Intent intent = new Intent(this, EditNoteActivity.class);
                intent.putExtra("isExist", false);
                startActivityForResult(intent, 1);
                break;
            }
            case R.id.search: {
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        long noteId = data.getLongExtra("ID", -1);
        long prevPos = data.getLongExtra("pos", -1);
        Note note = Note.findById(Note.class, noteId);
        if (prevPos >= 0) {
            noteList.set((int) prevPos, note);
            adapter.notifyItemChanged((int) prevPos);
        }
        else {
            noteList.add(note);
            adapter.notifyItemInserted(noteList.size() - 1);
        }
    }
}

class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

    private OnItemClickListener mListener;
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    GestureDetector mGestureDetector;
    public RecyclerItemClickListener(Context context, OnItemClickListener listener) {
        mListener = listener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }
    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        View childView = view.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}

