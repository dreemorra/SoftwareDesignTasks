package com.example.lab3;

import com.orm.SugarRecord;

public class Tag extends SugarRecord<Tag> {
    String value;

    public Tag(String tag) {
        this.value = tag;
    }

    public Tag() {}
}
