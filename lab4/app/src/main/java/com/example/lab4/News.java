package com.example.lab4;

import com.orm.SugarRecord;

public class News extends SugarRecord<News> {
    public String Title;
    public String Date;
    public String Content;
    public String Url;
    public String ImageUrl;

    public News(String title, String date, String content, String url, String imageUrl) {
        this.Title = title;
        this.Date = date;
        this.Content = content;
        this.Url = url;
        this.ImageUrl = imageUrl;
    }

    public News()
    {}
}