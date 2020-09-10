package com.example.liyuchen.ui.epidemic;

import com.example.liyuchen.Async.AsyncFunctions;
import com.example.liyuchen.Async.Information;

public class EntityQuery {
    public static void Search(String entityName, AsyncFinish callback) {
        String searchURL = Information.entityQueryURL + "?entity=" + entityName;
        AsyncFunctions.RequestTextFromURL(searchURL, "GET", 10000, (statement, msg, errMsg) -> {
            if(!statement) {
                //TODO: deal with connection failed
            }
            else {
                try {

                } catch (Exception e) {

                }
            }
        });
    }
}
