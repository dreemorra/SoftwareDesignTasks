package com.example.lab3;

import com.orm.SugarRecord;

import java.util.Date;
import java.util.List;

public class Note extends SugarRecord<Note> {
    public String Title;
    public String Tags;
    public String Description;
    public Date Created;

    public Note(String title, String tags, String description, Date created) {
        this.Title = title;
        this.Tags = tags;
        this.Description = description;
        this.Created = created;
    }

    public Note()
    {}
}
