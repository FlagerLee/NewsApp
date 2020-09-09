package com.example.liyuchen.ui.epidemic;

import com.example.liyuchen.Async.EpidemicInfo;
import com.example.liyuchen.Async.EpidemicInfo_Table;
import com.github.mikephil.charting.data.Entry;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.raizlabs.android.dbflow.sql.language.Method;
import com.raizlabs.android.dbflow.sql.language.property.PropertyFactory;

import java.util.ArrayList;
import java.util.List;

public class CovidData {
    public static List<Entry> getCovidData(String type, String Country, String Province, String County) {
        List<Entry> list = new ArrayList<>();

        List<EpidemicInfo> infos;

        if(Country == null) {
            return list; //Do nothing
        }
        else if(Province == null) {
            infos = SQLite.select()
                    .from(EpidemicInfo.class)
                    .where(EpidemicInfo_Table.Country.eq(Country))
                    .and(EpidemicInfo_Table.Province.eq(""))
                    .and(EpidemicInfo_Table.County.eq(""))
                    .orderBy(EpidemicInfo_Table.Days, true)
                    .queryList();
        }
        else if(County == null) {
            infos = SQLite.select()
                    .from(EpidemicInfo.class)
                    .where(EpidemicInfo_Table.Country.eq(Country))
                    .and(EpidemicInfo_Table.Province.eq(Province))
                    .and(EpidemicInfo_Table.County.eq(""))
                    .orderBy(EpidemicInfo_Table.Days, true)
                    .queryList();
        }
        else {
            infos = SQLite.select()
                    .from(EpidemicInfo.class)
                    .where(EpidemicInfo_Table.Country.eq(Country))
                    .and(EpidemicInfo_Table.Province.eq(Province))
                    .and(EpidemicInfo_Table.County.eq(County))
                    .orderBy(EpidemicInfo_Table.Days, true)
                    .queryList();
        }

        switch (type) {
            case "CONFIRMED":
                for(EpidemicInfo info: infos) {
                    list.add(new Entry(info.getStartTime().getTime() + (long)info.getDays() * 24 * 60 * 60 * 1000, info.getConfirmed()));
                }
                break;
            case "CURED":
                for(EpidemicInfo info: infos) {
                    list.add(new Entry(info.getStartTime().getTime() + (long)info.getDays() * 24 * 60 * 60 * 1000, info.getCured()));
                }
                break;
            case "DEAD":
                for(EpidemicInfo info: infos) {
                    list.add(new Entry(info.getStartTime().getTime() + (long)info.getDays() * 24 * 60 * 60 * 1000, info.getDead()));
                }
        }

        return list;
    }

    public static List<String> getRegionInfo() {
        List<EpidemicInfo> infos = SQLite.select()
                .from(EpidemicInfo.class)
                .where(EpidemicInfo_Table.Region.in(
                        PropertyFactory.from(
                                SQLite.select(Method.max(EpidemicInfo_Table.Region))
                                .from(EpidemicInfo.class)
                                .groupBy(EpidemicInfo_Table.Country)
                        )
                )).queryList();
        List<String> result = new ArrayList<>();
        for(EpidemicInfo info: infos) result.add(info.getCountry());
        return result;
    }

    public static List<String> getRegionInfo(String Country) {
        List<EpidemicInfo> infos = SQLite.select()
                .from(EpidemicInfo.class)
                .where(EpidemicInfo_Table.Country.eq(Country))
                .and(EpidemicInfo_Table.Region.in(
                        PropertyFactory.from(
                                SQLite.select(Method.max(EpidemicInfo_Table.Region))
                                        .from(EpidemicInfo.class)
                                        .groupBy(EpidemicInfo_Table.Province)
                        )
                )).queryList();
        List<String> result = new ArrayList<>();
        for(EpidemicInfo info: infos) result.add(info.getCountry());
        return result;
    }

    public static List<String> getRegionInfo(String Country, String Province) {
        List<EpidemicInfo> infos = SQLite.select()
                .from(EpidemicInfo.class)
                .where(EpidemicInfo_Table.Country.eq(Country))
                .and(EpidemicInfo_Table.Province.eq(Province))
                .and(EpidemicInfo_Table.Region.in(
                        PropertyFactory.from(
                                SQLite.select(Method.max(EpidemicInfo_Table.Region))
                                        .from(EpidemicInfo.class)
                                        .groupBy(EpidemicInfo_Table.County)
                        )
                )).queryList();
        List<String> result = new ArrayList<>();
        for(EpidemicInfo info: infos) result.add(info.getCountry());
        return result;
    }
}
