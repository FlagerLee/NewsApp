package com.example.newspart.ui.home;

public class newslayout {

    private String title;
    private String author;
    private String time;
    private String content;

    public newslayout(String title, String author, String time, String content)
    {
        this.title=title;
        this.author=author;
        this.time=time;
        this.content=content;
    }

    public String getTime() {
        return time;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }
}
