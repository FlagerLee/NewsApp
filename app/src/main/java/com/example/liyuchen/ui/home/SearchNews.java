package com.example.liyuchen.ui.home;

import com.example.liyuchen.Async.AsyncFunctions;
import com.example.liyuchen.Async.Information;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchNews {
    public void search(final String content, SetContent callback) {
        //search in latest 500 pieces of news
        ArrayList<newslayout> newsList = new ArrayList<>();

        AsyncFunctions.RequestTextFromURL(Information.eventsURL + "?page=1&size=500", "GET", 10000, ((statement, msg, errMsg) -> {
            try {
                JSONObject news = new JSONObject(msg);
                JSONArray newsArray = new JSONArray(news.getString("data"));
                for(int i = 0; i < newsArray.length(); i ++) {
                    JSONObject newsContent = new JSONObject(newsArray.getString(i));
                    if(newsContent.getString("title").contains(content)) {
                        newsList.add(new newslayout(newsContent.getString("_id"), newsContent.getString("title"), newsContent.getString("author"), newsContent.getString("time"), newsContent.getString("content"), false));
                    }
                }
            } catch (Exception e) {

            }

            callback.Set(newsList);
        }));
    }
}
