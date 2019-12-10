package com.example.lab3;

import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class NoteViewModel extends ViewModel {
    public MutableLiveData<List<Note>> notes;

    public MutableLiveData<List<Note>> getNotes() {
        if (notes == null) {
            notes = new MutableLiveData<>();
        }
        loadNotes();
        return notes;
    }

    public void loadNotes()
    {
        List<Note> noteList = Note.listAll(Note.class);
        notes.setValue(noteList);
    }

//    private void loadNotes() {
//        new AsyncTask<Void, Void, List<Note>>() {
//            protected List<Note> doInBackground(Void... params) {
//                List<Note> noteList = Note.listAll(Note.class);
//                return noteList;
//            }
//
//            @Override
//            protected void onPostExecute(List<Note> data) {
//                notes.setValue(data);
//            }
//        }.execute();
//    }
}

//    public void updateNotes(Note note, boolean isExist, long pos)
//    {
//        if (!isExist) {
//            Note newnote = note;
//            note.save();
//
//
//        }
//        else {
//            Note prevnote = Note.findById(Note.class, pos+1);
//            prevnote.Title = note.Title;
//            prevnote.Description = note.Description;
//            prevnote.save();
//        }
//        loadNotes();
//    }