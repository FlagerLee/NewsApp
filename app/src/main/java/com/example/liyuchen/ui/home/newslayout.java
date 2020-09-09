package com.example.liyuchen.ui.home;

public class newslayout {

    private String newsID;
    private String title;
    private String author;
    private String time;
    private String content;
    private boolean isRead=false;

    public newslayout(String newsID, String title, String author, String time, String content, boolean isRead)
    {
        this.newsID = newsID;
        this.title=title;
        this.author=author;
        this.time=time;
        this.content=content;
        this.isRead=isRead;
    }

    public boolean isRead()
    {
        return isRead;
    }

    public void read() { isRead=true; }

    public String getNewsID() {
        return this.newsID;
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
