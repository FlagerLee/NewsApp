package com.example.liyuchen.Async;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

@Table(database = AppDatabase.class)
public class HistoryNews extends BaseModel {
    @PrimaryKey
    private String Event_ID;

    @Column
    private String Category;

    @Column
    private String Author;

    @Column
    private String Content;

    @Column
    private String Time;

    @Column
    private String Title;

    @Column
    private String GreenWhichTime;

    @Column
    private String Lang;

    @Column
    private long TimeFlag;

    @Column
    private Date LastViewed;

    //Functions used for query
    public String getEvent_ID() {
        return this.Event_ID;
    }
    public String getCategory() {
        return this.Category;
    }
    public String getAuthor() {
        return this.Author;
    }
    public String getContent() {
        return this.Content;
    }
    public String getTime() {
        return this.Time;
    }
    public String getTitle() {
        return this.Title;
    }
    public String getGreenWhichTime() {
        return this.GreenWhichTime;
    }
    public String getLang() {
        return this.Lang;
    }
    public long getTimeFlag() {
        return this.TimeFlag;
    }
    public Date getLastViewed() {
        return this.LastViewed;
    }

    //Functions used for add/del/update
    public void setEvent_ID(String event_ID) {
        this.Event_ID = event_ID;
    }
    public void setCategory(String category) {
        this.Category = category;
    }
    public void setAuthor(String author) {
        this.Author = author;
    }
    public void setContent(String content) {
        this.Content = content;
    }
    public void setTime(String time) {
        this.Time = time;
    }
    public void setTitle(String title) {
        this.Title = title;
    }
    public void setGreenWhichTime(String greenWhichTime) {
        this.GreenWhichTime = greenWhichTime;
    }
    public void setLang(String lang) {
        this.Lang = lang;
    }
    public void setTimeFlag(long timeFlag) {
        this.TimeFlag = timeFlag;
    }
    public void setLastViewed(Date lastViewed) {
        this.LastViewed = lastViewed;
    }

    public void setHistoryNews(EventDetail detail) {
        this.Event_ID = detail.getEvent_ID();
        this.Category = detail.getCategory();
        this.Author = detail.getAuthor();
        this.Content = detail.getContent();
        this.Time = detail.getTime();
        this.Title = detail.getTitle();
        this.GreenWhichTime = detail.getGreenWhichTime();
        this.Lang = detail.getLang();
        this.TimeFlag = detail.getTimeFlag();
        this.LastViewed = new Date();
    }
}
