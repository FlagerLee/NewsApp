package com.example.liyuchen.ui.epidemic;

import com.example.liyuchen.Async.AsyncFunctions;
import com.example.liyuchen.Async.Information;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EntityQuery {
    public static void Search(String entityName, EntityQueryFinish callback) {
        List<String> list = new ArrayList<>();
        String searchURL = Information.entityQueryURL + "?entity=" + entityName;
        AsyncFunctions.RequestTextFromURL(searchURL, "GET", 10000, (statement, msg, errMsg) -> {
            if(!statement) {
                //TODO: deal with connection failed
            }
            else {
                try {
                    JSONObject object = new JSONObject(msg);
                    JSONArray datas = new JSONArray(object.getString("data"));
                    for(int i = 0; i < datas.length(); i ++) {
                        list.add(datas.getString(i));
                    }
                } catch (Exception e) {
                    String err = e.toString();
                }
                callback.finish(list);
            }
        });
    }
}
