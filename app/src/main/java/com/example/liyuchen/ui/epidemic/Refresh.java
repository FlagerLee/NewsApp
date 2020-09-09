package com.example.liyuchen.ui.epidemic;

import com.example.liyuchen.Async.AsyncFunctions;
import com.example.liyuchen.Async.EpidemicInfo;
import com.example.liyuchen.Async.EpidemicInfo_Table;
import com.example.liyuchen.Async.Information;
import com.example.liyuchen.Async.RegionInfo;
import com.example.liyuchen.Async.RegionInfo_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

public class Refresh {
    public static void refresh(AsyncFinish callback) {
        AsyncFunctions.RequestTextFromURL(Information.epidemicInfoURL, "GET", 10000, (statement, msg, errMsg) -> {
            if(!statement) {
                //TODO: deal with connection failed
            }
            else {
                try {
                    JSONObject jsonObject = new JSONObject(msg);
                    List<RegionInfo> regionInfo = SQLite.select(RegionInfo_Table.Region)
                            .from(RegionInfo.class)
                            .queryList();
                    for(RegionInfo rInfo: regionInfo) {
                        String region = rInfo.Region;
                        String[] address = region.split("\\|");
                        String regionData = jsonObject.getString(region);
                        JSONObject mid = null;
                        try {
                            mid = new JSONObject(regionData);
                        }
                        catch (Exception e) {
                            //cannot find Region
                            continue;
                        }
                        String startDate = mid.getString("begin");
                        JSONArray data = new JSONArray(mid.getString("data"));
                        EpidemicInfo oldInfo = SQLite.select()
                                .from(EpidemicInfo.class)
                                .where(EpidemicInfo_Table.Region.eq(region))
                                .orderBy(EpidemicInfo_Table.Days, true)
                                .querySingle();
                        int days = 0;
                        if(oldInfo != null) days = oldInfo.getDays();
                        for(int i = days + 1; i <= data.length(); i ++) {
                            EpidemicInfo newInfo = new EpidemicInfo();
                            newInfo.setRegion(region);
                            int regionLength = address.length;
                            newInfo.setCountry(address[0]);
                            if(regionLength > 1) newInfo.setProvince(address[1]);
                            if(regionLength > 2) newInfo.setCounty(address[2]);

                            JSONArray detailData = new JSONArray(data.getString(i));
                            newInfo.setStartTime(new Date(startDate));
                            newInfo.setConfirmed(Long.parseLong(detailData.getString(0)));
                            newInfo.setCured(Long.parseLong(detailData.getString(3)));
                            newInfo.setDead(Long.parseLong(detailData.getString(4)));
                            newInfo.setRisk(Integer.parseInt(detailData.getString(5)));

                            newInfo.setDays(i);

                            AsyncFunctions.DatabaseCURD(newInfo, "SAVE", (new_statement, new_errMsg) -> {
                                //TODO: deal with errMsg
                            });
                        }
                    }

                }
                catch (Exception e) {

                }

                callback.finish();
            }
        });
    }

}
