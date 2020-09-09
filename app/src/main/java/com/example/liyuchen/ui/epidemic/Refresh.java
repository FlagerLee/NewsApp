package com.example.liyuchen.ui.epidemic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.liyuchen.Async.AsyncFunctions;
import com.example.liyuchen.Async.EpidemicInfo;
import com.example.liyuchen.Async.EpidemicInfo_Table;
import com.example.liyuchen.Async.Information;
import com.example.liyuchen.Async.RegionInfo;
import com.example.liyuchen.Async.RegionInfo_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.json.JSONArray;
//import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Refresh {
    public static void refresh(AsyncFinish callback) {
        AsyncFunctions.RequestTextFromURL(Information.epidemicInfoURL, "GET", 10000, (statement, msg, errMsg) -> {
            if(!statement) {
                //TODO: deal with connection failed
            }
            else {
                try {
                    Map<String, String> jsonObject = JSONObject.parseObject(msg, new TypeReference<Map<String, String>>() {});
                    //JSONObject jsonObject = new JSONObject(msg);
                    List<RegionInfo> regionInfo = SQLite.select()
                            .from(RegionInfo.class)
                            .queryList();
                    if(regionInfo == null || regionInfo.size() == 0) {
                        SyncGetCSV();
                        File regionCSV = new File("/data/data/com.example.liyuchen/Region.csv");
                        BufferedReader reader = new BufferedReader(new FileReader(regionCSV));
                        reader.readLine(); //skip header
                        String line = reader.readLine();
                        while(line != null) {
                            String[] info = line.split(",");
                            RegionInfo rInfo = new RegionInfo();
                            if(info.length > 0) rInfo.Region = info[0];
                            if(info.length > 1) rInfo.Name_ZH = info[1];
                            if(info.length > 2) rInfo.Name_EN = info[2];
                            rInfo.save();
                            line = reader.readLine();
                        }
                        regionInfo = SQLite.select()
                                .from(RegionInfo.class)
                                .queryList();
                    }
                    for(RegionInfo rInfo: regionInfo) {
                        String region = rInfo.Region;
                        String[] address = region.split("\\|");
                        String regionData = jsonObject.get(region);
                        org.json.JSONObject mid = null;
                        try {
                            mid = new org.json.JSONObject(regionData);
                        }
                        catch (Exception e) {
                            //cannot find Region
                            continue;
                        }
                        String startDate = mid.getString("begin");
                        List<String> data = JSONObject.parseObject(mid.getString("data"), new TypeReference<List<String>>() {});
                        EpidemicInfo oldInfo = SQLite.select()
                                .from(EpidemicInfo.class)
                                .where(EpidemicInfo_Table.Region.eq(region))
                                .orderBy(EpidemicInfo_Table.Days, true)
                                .querySingle();
                        int days = 0;
                        if(oldInfo != null) days = oldInfo.getDays();
                        for(int i = days; i < data.size(); i ++) {
                            EpidemicInfo newInfo = new EpidemicInfo();
                            newInfo.setRegion(region);
                            int regionLength = address.length;
                            newInfo.setCountry(address[0]);
                            if(regionLength > 1) newInfo.setProvince(address[1]);
                            if(regionLength > 2) newInfo.setCounty(address[2]);

                            JSONArray detailData = new JSONArray(data.get(i));
                            SimpleDateFormat format = new SimpleDateFormat("YYYY-mm-dd");
                            newInfo.setStartTime(format.parse(startDate));
                            newInfo.setConfirmed(Long.parseLong(detailData.getString(0)));
                            newInfo.setCured(Long.parseLong(detailData.getString(2)));
                            newInfo.setDead(Long.parseLong(detailData.getString(3)));
                            newInfo.setRisk(Integer.parseInt(detailData.getString(5)));

                            newInfo.setDays(i);

                            AsyncFunctions.DatabaseCURD(newInfo, "SAVE", ((statement1, errMsg1) -> {}));
                        }
                    }

                }
                catch (Exception e) {
                    String s = e.toString();
                    int a = 0;
                }

                callback.finish();
            }
        });
    }

    private static void SyncGetCSV() {
        try {
            String content = null;
            URL newURL = new URL(Information.regionsInfoURL);
            HttpURLConnection connection = (HttpURLConnection) newURL.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10000);
            InputStream inputStream = connection.getInputStream();
            byte[] data = readInputStream(inputStream);
            File file = new File("/data/data/com.example.liyuchen/Region.csv");
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(data);
            outputStream.close();
        }
        catch (Exception e) {
            String s = e.toString();
        }
        return;
    }

    private static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while( (len=inStream.read(buffer)) != -1 ){
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

}
