package com.example.liyuchen.ui.epidemic;

import com.example.liyuchen.Async.AsyncFunctions;
import com.example.liyuchen.Async.Information;

public class Refresh {
    public static void refresh() {
        AsyncFunctions.RequestTextFromURL(Information.refreshURL, "GET", 10000, ((statement, msg, errMsg) -> {
            //do nothing
        }));
    }

}
