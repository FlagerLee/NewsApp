package com.example.liyuchen.Async;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

@Table(database = AppDatabase.class)
public class EpidemicInfo extends BaseModel {

    @PrimaryKey
    private String Country;

    @PrimaryKey
    private String Province;

    @PrimaryKey
    private String City;

    @Column
    private Date StartTime;

    @Column
    private Date CurrentDate;

    @Column
    private int Confirmed;

    @Column
    private int Cured;

    @Column
    private int Dead;

    @Column
    private int Risk;

    //Functions used for query

    public String getCountry() {
        return this.Country;
    }
    public String getProvince() {
        return this.Province;
    }
    public String getCity() {
        return this.City;
    }

    public Date getStartTime() {
        return this.StartTime;
    }
    public Date getCurrentDate() {
        return this.CurrentDate;
    }

    public int getConfirmed() {
        return this.Confirmed;
    }
    public int getCured() {
        return this.Cured;
    }
    public int getDead() {
        return this.Dead;
    }
    public int getRisk() {
        return this.Risk;
    }

    //Functions used for add/del/update

    public void setCountry(String country) {
        this.Country = country;
    }
    public void setProvince(String province) {
        this.Province = province;
    }
    public void setCity(String city) {
        this.City = city;
    }
    public void setStartTime(Date startTime) {
        this.StartTime = startTime;
    }
    public void setCurrentDate(Date currentDate) {
        this.CurrentDate = currentDate;
    }
    public void setConfirmed(int confirmed) {
        this.Confirmed = confirmed;
    }
    public void setCured(int cured) {
        this.Cured = cured;
    }
    public void setDead(int dead) {
        this.Dead = dead;
    }
    public void setRisk(int risk) {
        this.Risk = risk;
    }
}
