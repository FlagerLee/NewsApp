package com.example.liyuchen.Async;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDatabase.class)
public class RelatedEvents extends BaseModel {
    @PrimaryKey
    //@ForeignKey(tableClass = EventDetail.class, onDelete = ForeignKeyAction.CASCADE)
    private String Origin_ID;

    @Column
    private String Related_ID;

    @Column
    private double RelatedScore;

    //Functions used for query
    public String getOrigin_ID() {
        return this.Origin_ID;
    }
    public String getRelated_ID() {
        return this.Related_ID;
    }
    public double getRelatedScore() {
        return this.RelatedScore;
    }

    //Functions used for add/del/update
    public void setOrigin_ID(String ID) {
        this.Origin_ID = ID;
    }
    public void setRelated_ID(String ID) {
        this.Related_ID = ID;
    }
    public void setRelatedScore(double score) {
        this.RelatedScore = score;
    }
}
