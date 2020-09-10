package com.example.liyuchen.ui.epidemic;

import com.example.liyuchen.Async.AsyncFunctions;
import com.example.liyuchen.Async.EpidemicInfo;
import com.example.liyuchen.Async.EpidemicInfo_Table;
import com.example.liyuchen.Async.Information;
import com.github.mikephil.charting.data.Entry;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.raizlabs.android.dbflow.sql.language.Method;
import com.raizlabs.android.dbflow.sql.language.property.PropertyFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CovidData {
    public static void getCovidData( String Country, String Province, String County, AsyncFinish callback) {
        List<Entry> confirmedList = new ArrayList<>();
        List<Entry> curedList = new ArrayList<>();
        List<Entry> deadList = new ArrayList<>();

        AsyncFunctions.RequestTextFromURL(Information.QueryURL + "?Country=" + Country + "&Province=" + Province + "&County=" + County, "GET", 10000, (statement, msg, errMsg) -> {
            try {
                JSONArray info = new JSONArray(msg);
                for(int i = 0; i < info.length(); i ++) {
                    String dailyInfo = info.getString(i);
                    JSONArray dailyData = new JSONArray(dailyInfo);
                    confirmedList.add(new Entry(i + 1, dailyData.getInt(0)));
                    curedList.add(new Entry(i + 1, dailyData.getInt(1)));
                    deadList.add(new Entry(i + 1, dailyData.getInt(2)));
                }
            } catch (Exception e) {
                String err = e.toString();
            }
            callback.finish(confirmedList, curedList, deadList);
        });

    }

    public static void getRegionInfo(SAsyncFinish callback) {
        List<String> result = new ArrayList<>();

        AsyncFunctions.RequestTextFromURL(Information.getCountryURL, "GET", 10000, ((statement, msg, errMsg) -> {
            try {
                JSONArray Country = new JSONArray(msg);
                for(int i = 0; i < Country.length(); i ++) result.add(Country.getString(i));
            } catch (Exception e) {

            }

            callback.finish(result);
        }));
    }

    public static List<String> getRegionInfo(String Country, SAsyncFinish callback) {
        List<String> result = new ArrayList<>();

        AsyncFunctions.RequestTextFromURL(Information.getProvinceURL + "?Country=" + Country, "GET", 10000, ((statement, msg, errMsg) -> {
            try {
                JSONArray Province = new JSONArray(msg);
                for(int i = 0; i < Province.length(); i ++) result.add(Province.getString(i));
            } catch (Exception e) {

            }

            callback.finish(result);
        }));

        return result;
    }

    public static List<String> getRegionInfo(String Country, String Province, SAsyncFinish callback) {
        List<String> result = new ArrayList<>();

        AsyncFunctions.RequestTextFromURL(Information.getCountyURL + "?Country=" + Country + "&Province=" + Province, "GET", 10000, ((statement, msg, errMsg) -> {
            try {
                JSONArray County = new JSONArray(msg);
                for(int i = 0; i < County.length(); i ++) result.add(County.getString(i));
            } catch (Exception e) {

            }

            callback.finish(result);
        }));

        return result;
    }
}
