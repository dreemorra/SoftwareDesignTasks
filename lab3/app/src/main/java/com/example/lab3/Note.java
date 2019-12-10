package com.example.lab3;

import com.orm.SugarRecord;

public class Note extends SugarRecord<Note> {
    public String Title;
    public String Description;

    public Note(String title, String description) {
        this.Title = title;
        this.Description = description;
    }

    public Note()
    {}
}
