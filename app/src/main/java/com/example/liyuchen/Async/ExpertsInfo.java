package com.example.liyuchen.Async;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDatabase.class)
public class ExpertsInfo extends BaseModel {
    @PrimaryKey
    private String ID;

    @Column
    private String Name_ZH;

    @Column
    private String Name_EN;

    @Column
    private String Avatar;

    @Column
    private double Activity;

    @Column
    private int Citations;

    @Column
    private double Diversity;

    @Column
    private int GIndex;

    @Column
    private int HIndex;

    @Column
    private double Sociability;

    //Functions used for query
    public String getID() {
        return this.ID;
    }
    public String getName_ZH() {
        return this.Name_ZH;
    }
    public String getName_EN() {
        return this.Name_EN;
    }
    public double getActivity() {
        return this.Activity;
    }
    public int getCitations() {
        return this.Citations;
    }
    public double getDiversity() {
        return this.Diversity;
    }
    public int getGIndex() {
        return this.GIndex;
    }
    public int getHIndex() {
        return this.HIndex;
    }
    public double getSociability() {
        return this.Sociability;
    }
    public String getAvatar() {
        return this.Avatar; //figure
    }

    //Functions used for add/del/update
    public void setID(String id) {
        this.ID = id;
    }
    public void setName_ZH(String name_zh) {
        this.Name_ZH = name_zh;
    }
    public void setName_EN(String name_en) {
        this.Name_EN = name_en;
    }
    public void setAvatar(String avatar) {
        this.Avatar = avatar;
    }
    public void setActivity(double activity) {
        this.Activity = activity;
    }
    public void setCitations(int citations) {
        this.Citations = citations;
    }
    public void setDiversity(double diversity) {
        this.Diversity = diversity;
    }
    public void setGIndex(int gIndex) {
        this.GIndex = gIndex;
    }
    public void setHIndex(int hIndex) {
        this.HIndex = hIndex;
    }
    public void setSociability(double sociability) {
        this.Sociability = sociability;
    }
}
