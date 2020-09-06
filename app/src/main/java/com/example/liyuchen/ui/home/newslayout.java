package com.example.liyuchen.ui.home;

public class newslayout {

    private String title;
    private String author;
    private String time;
    private String content;
    private boolean isread=false;

    public newslayout(String title, String author, String time, String content, boolean isread)
    {
        this.title=title;
        this.author=author;
        this.time=time;
        this.content=content;
        this.isread=isread;
    }

    public boolean isread()
    {
        return isread;
    }

    public void read() { isread=true; }

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
