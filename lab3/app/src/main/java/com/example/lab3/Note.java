package com.example.lab3;

import com.orm.SugarRecord;

import java.util.List;

public class Note extends SugarRecord<Note> {
    public String Title;
    public String Tags;
    public String Description;

    public Note(String title, String tags, String description) {
        this.Title = title;
        this.Tags = tags;
        this.Description = description;
    }

    public Note()
    {}
}
