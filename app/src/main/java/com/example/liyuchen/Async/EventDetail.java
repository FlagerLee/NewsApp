package com.example.liyuchen.Async;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDatabase.class)
public class EventDetail extends BaseModel {
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
    private String GreenWhichTime;

    @Column
    private String Lang;

    @Column
    private long TimeFlag;

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
    public String getGreenWhichTime() {
        return this.GreenWhichTime;
    }
    public String getLang() {
        return this.Lang;
    }
    public long getTimeFlag() {
        return this.TimeFlag;
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
    public void setGreenWhichTime(String greenWhichTime) {
        this.GreenWhichTime = greenWhichTime;
    }
    public void setLang(String lang) {
        this.Lang = lang;
    }
    public void setTimeFlag(long timeFlag) {
        this.TimeFlag = timeFlag;
    }
}
