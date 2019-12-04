package com.example.lab3;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class NoteViewModel extends ViewModel {
    private MutableLiveData<List<Note>> notes;
    public LiveData<List<Note>> getNotes() {
        if (notes == null) {
            notes = new MutableLiveData<List<Note>>();
            loadNotes();
        }
        return notes;
    }

    private void loadNotes() {
        // Do an asynchronous operation to fetch users.
    }


}
