package com.example.liyuchen.Async;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

@Table(database = AppDatabase.class)
public class EpidemicInfo extends BaseModel {

    @PrimaryKey
    private String Region;

    @Column
    private Date StartTime;

    @Column
    private int Days;

    @Column
    private long Confirmed;

    @Column
    private long Cured;

    @Column
    private long Dead;

    @Column
    private int Risk;
    //从开始到现在过了多少天

    //Functions used for query

    public String getRegion() {
        return this.Region;
    }
    public Date getStartTime() {
        return this.StartTime;
    }
    public int getDays() {
        return this.Days;
    }
    public long getConfirmed() {
        return this.Confirmed;
    }
    public long getCured() {
        return this.Cured;
    }
    public long getDead() {
        return this.Dead;
    }
    public int getRisk() {
        return this.Risk;
    }

    //Functions used for add/del/update

    public void setRegion(String region) {
        this.Region = region;
    }
    public void setStartTime(Date startTime) {
        this.StartTime = startTime;
    }
    public void setDays(int days) {
        this.Days = days;
    }
    public void setConfirmed(long confirmed) {
        this.Confirmed = confirmed;
    }
    public void setCured(long cured) {
        this.Cured = cured;
    }
    public void setDead(long dead) {
        this.Dead = dead;
    }
    public void setRisk(int risk) {
        this.Risk = risk;
    }
}
