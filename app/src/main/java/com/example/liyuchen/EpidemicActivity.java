package com.example.liyuchen;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.liyuchen.Async.AsyncFunctions;
import com.example.liyuchen.Async.EpidemicInfo;
import com.example.liyuchen.Async.EpidemicInfo_Table;
import com.example.liyuchen.Async.Information;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.Iterator;
import java.util.Map;

import static com.raizlabs.android.dbflow.sql.language.Method.min;

public class EpidemicActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO: Add epidemic activity xml

    }

    private void refresh() {
        AsyncFunctions.RequestTextFromURL(Information.epidemicInfoURL, "GET", 10000, (statement, msg, errMsg) -> {
            if(!statement) {
                //TODO: deal with errMsg

            }
            else {
                Map<String, String> params = JSONObject.parseObject(msg, new TypeReference<Map<String, String> >() {});
                for(Map.Entry<String, String> entry: params.entrySet()) {
                    String region = entry.getKey();
                    //获取该地区最晚的信息，更新这之后的信息
                    EpidemicInfo queriedInfo = SQLite.select(min(EpidemicInfo_Table.Days))
                            .from(EpidemicInfo.class)
                            .where(EpidemicInfo_Table.Region.eq(region))
                            .querySingle();
                    int day = 0;
                    if(queriedInfo != null) day = queriedInfo.getDays();
                    //for(int i = 0; i < )
                }
            }
        });
    }
}
