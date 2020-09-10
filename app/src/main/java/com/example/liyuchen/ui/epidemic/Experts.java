package com.example.liyuchen.ui.epidemic;

import com.example.liyuchen.Async.AsyncFunctions;
import com.example.liyuchen.Async.Information;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Experts {
    public static void GetExpertsInfo(SAsyncFinish callback) {
        List<String> list = new ArrayList<>();
        AsyncFunctions.RequestTextFromURL(Information.expertsURL, "GET", 10000, (statement, msg, errMsg) -> {
            if(!statement) {
                //TODO: connection lost
            }
            else {
                try {
                    JSONObject object = new JSONObject(msg);
                    String data = object.getString("data");
                    JSONArray experts = new JSONArray(data);
                    for(int i = 0; i < experts.length(); i ++) {
                        list.add(experts.getString(i));
                    }
                } catch (Exception e) {

                }
            }
            callback.finish(list);
        });
    }
}
