package com.example.liyuchen.Async;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDatabase.class)
public class EntityInfo extends BaseModel {
    //Store data only when someone searched for entities
    @PrimaryKey
    private String Label;

    @Column
    private double Hot;

    @Column
    private String URL;

    @Column
    private String EnWiki;

    @Column
    private String ZhWiki;

    @Column
    private String Baidu;

    @Column
    private String ImgUrl;

    //Functions used for query
    public String getLabel() {
        return this.Label;
    }
    public double getHot() {
        return this.Hot;
    }
    public String getURL() {
        return this.URL;
    }
    public String getEnWiki() {
        return this.EnWiki;
    }
    public String getZhWiki() {
        return this.ZhWiki;
    }
    public String getBaidu() {
        return this.Baidu;
    }
    public String getImgUrl() {
        return this.ImgUrl;
    }

    //Functions used for add/del/update
    public void setLabel(String label) {
        this.Label = label;
    }
    public void setHot(double hot) {
        this.Hot = hot;
    }
    public void setURL(String url) {
        this.URL = url;
    }
    public void setEnWiki(String enWiki) {
        this.EnWiki = enWiki;
    }
    public void setZhWiki(String zhWiki) {
        this.ZhWiki = zhWiki;
    }
    public void setBaidu(String baidu) {
        this.Baidu = baidu;
    }
    public void setImgUrl(String imgUrl) {
        this.ImgUrl = imgUrl;
    }
}
