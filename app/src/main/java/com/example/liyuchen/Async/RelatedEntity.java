package com.example.liyuchen.Async;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDatabase.class)
public class RelatedEntity extends BaseModel {
    @PrimaryKey
    //@ForeignKey(tableClass = EntityInfo.class, onDelete = ForeignKeyAction.CASCADE)
    private String OriginLabel;

    @Column
    private String RelatedLabel;

    @Column
    private String URL;

    @Column
    private String Relation;

    //Functions used for query
    public String getOriginLabel() {
        return this.OriginLabel;
    }
    public String getRelatedLabel() {
        return this.RelatedLabel;
    }
    public String getURL() {
        return this.URL;
    }
    public String getRelation() {
        return this.Relation;
    }

    //Functions used for add/del/update
    public void setOriginLabel(String originLabel) {
        this.OriginLabel = originLabel;
    }
    public void setRelatedLabel(String relatedLabel) {
        this.RelatedLabel = relatedLabel;
    }
    public void setURL(String url) {
        this.URL = url;
    }
    public void setRelation(String relation) {
        this.Relation = relation;
    }
}
