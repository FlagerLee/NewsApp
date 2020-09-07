package com.example.liyuchen.ui.home;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.liyuchen.Async.AsyncFunctions;
import com.example.liyuchen.Async.EventDetail;
import com.example.liyuchen.Async.Information;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Refresh {
    public static void refresh(String type, int page, int size, SetContent callback) {
        //called when users trying to refresh
        //刷新

        String requestURL = Information.eventsURL + "?type=" + type + "&page=" + String.valueOf(page) + "&size=" + String.valueOf(size);
        //每次请求20条新闻；type是filter，值为新闻(news)或论文(paper)
        AsyncFunctions.RequestTextFromURL(requestURL, "GET", 10000, (statement, msg, errMsg) -> {
            if(!statement) {
                //error occurred
            }
            ArrayList<newslayout> newslayouts = new ArrayList<>();
            try {
                Map<String, String> params = JSONObject.parseObject(msg, new TypeReference<Map<String, String> >(){});
                String datas = params.get("data");
                //dataList stores each message that requested
                List<String> dataList = JSONObject.parseObject(datas, new TypeReference<List<String> >() {});

                //handle these news
                for(int i = 0; i < dataList.size(); i ++) {
                    String singleData = dataList.get(i);
                    params = JSONObject.parseObject(singleData, new TypeReference<Map<String, String> >(){});

                    //split content into several paragraphs through ','
                    String content = params.get("content");
                    StringBuilder builder = new StringBuilder(content);
                    for(int j = 0; j < content.length(); j ++) {
                        if(builder.charAt(j) == ',' && (builder.charAt(j + 1) != ' ' && (!Character.isDigit(builder.charAt(j + 1))) && (!Character.isDigit(builder.charAt(j - 1))) )) builder.replace(j, j + 1, "\n");
                    }
                    content = builder.toString();

                    EventDetail eventDetail = new EventDetail();
                    eventDetail.setEvent_ID(params.get("_id"));
                    eventDetail.setTimeFlag(Long.parseLong(params.get("tflag")));
                    eventDetail.setAuthor(params.get("authors"));
                    eventDetail.setCategory(params.get("category"));
                    eventDetail.setContent(content);
                    eventDetail.setLang(params.get("lang"));
                    eventDetail.setTime(params.get("time"));
                    eventDetail.setGreenWhichTime(params.get("date"));
                    eventDetail.setTitle(params.get("title"));
                    AsyncFunctions.DatabaseCURD(eventDetail, "SAVE", (new_statement, new_errMsg) -> {
                        if(!new_statement) {
                            //TODO: Error Occurred when Inserting data into database
                        }
                    });

                    String title = eventDetail.getTitle();
                    String author = eventDetail.getAuthor();
                    if(author == null) author = "Unknown";
                    else {
                        List<String> authorList = JSONObject.parseObject(author, new TypeReference<List<String>>() {
                        });
                        author = "";
                        for (int j = 0; j < authorList.size(); j++) {
                            Map<String, String> name = JSONObject.parseObject(authorList.get(j), new TypeReference<Map<String, String>>() {
                            });
                            author = author + name.get("name");
                            if (j != authorList.size() - 1) author = author + ", ";
                        }
                    }
                    String time = eventDetail.getTime();
                    boolean isRead = History.inHistory(eventDetail.getEvent_ID());
                    newslayouts.add(new newslayout(title, author, time, content, isRead));
                }

            }
            catch (Exception e) {
                final String error = e.toString();
            }

            callback.Set(newslayouts);
        });
    }
}
