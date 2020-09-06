package com.example.liyuchen.Async;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

public class AppDBManager extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }
}
