package com.example.yu_notification;

public class Contents {
    String no, title, author, date, view, url;

    public Contents(String no, String title, String author, String date, String view, String url) {
        this.no = no;
        this.title = title;
        this.author = author;
        this.date = date;
        this.view = view;
        this.url = url;
    }

    public String getUrl() {return url;}

    public Contents(String title) {
        this.title = title;
    }

    public String getNo() {
        return no;
    }

    public String getTitle() {return title;}

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public String getView() {
        return view;
    }
}
