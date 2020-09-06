package com.example.liyuchen.Async;

/*
    调用URL接口，从api获取新闻及其他内容
 */
public class Information {
    //singleton
    static private class InfoSingleton {
        static Information info = new Information();
    }
    private Information() {}
    protected Information getInstance() {return InfoSingleton.info;}

    public static final String epidemicInfoURL = "https://covid-dashboard.aminer.cn/api/dist/epidemic.json";
    public static final String eventsURL = "https://covid-dashboard.aminer.cn/api/events/list";
    public static final String eventDetailURL = "https://covid-dashboard-api.aminer.cn/event/";
    public static final String entityQueryURL = "https://innovaapi.aminer.cn/covid/api/v1/pneumonia/entityquery";
    public static final String expertsURL = "https://innovaapi.aminer.cn/predictor/api/v1/valhalla/highlight/get_ncov_expers_list?v=2";
}