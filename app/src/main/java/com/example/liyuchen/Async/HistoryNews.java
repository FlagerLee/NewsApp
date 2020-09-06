package com.example.liyuchen.Async;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

@Table(database = AppDatabase.class)
public class HistoryNews extends EventDetail {
    //Maximum: 200
    @Column
    private Date LastViewed;

    //Functions used for query
    public Date getLastViewed() {
        return this.LastViewed;
    }

    //Functions used for add/del/update
    public void setLastViewed(Date lastViewed) {
        this.LastViewed = lastViewed;
    }
}
