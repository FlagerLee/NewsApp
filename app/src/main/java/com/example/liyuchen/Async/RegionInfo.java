package com.example.liyuchen.Async;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDatabase.class)
public class RegionInfo extends BaseModel {
    @PrimaryKey
    public String Region;

    @Column
    public String Name_ZH;

    @Column
    public String Name_EN;
}
