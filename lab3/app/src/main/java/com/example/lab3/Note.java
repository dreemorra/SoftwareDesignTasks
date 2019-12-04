package com.example.lab3;

public class Note {
    public final String Id;
    public final String Title;
    public final String Tag;
    public final String Description;

    public Note(String id, String title, String tag, String description) {
        this.Id = id;
        this.Title = title;
        this.Tag = tag;
        this.Description = description;
    }
}
