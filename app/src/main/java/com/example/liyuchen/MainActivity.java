package com.example.liyuchen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.liyuchen.Async.*;
import com.raizlabs.android.dbflow.sql.language.From;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.raizlabs.android.dbflow.sql.language.Method.count;
import static com.raizlabs.android.dbflow.sql.language.Method.min;


public class MainActivity extends AppCompatActivity {
    //MainActivity contains news list
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CurrentLoadedPage = 1;
        refresh();
    }

    static final private int NewsPerPage = 20;
    static private int CurrentLoadedPage = 0;

    static private String CurrentTopNewsID = null;
    static private String CurrentFilter = null; //should be news or paper

    private void refresh() {
        //called when users trying to refresh
        //刷新

        String requestURL = null;
        //每次请求20条新闻；type是filter，值为新闻(news)或论文(paper)
        if(CurrentFilter == null) requestURL = Information.eventsURL + "?type=news&page=1&size=20";
        else requestURL = Information.eventsURL + "?type=" + MainActivity.CurrentFilter + "&page=1&size=20";
        AsyncFunctions.RequestTextFromURL(requestURL, "GET", 10000, (statement, msg, errMsg) -> {
            if(!statement) {
                //error occurred
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        TextView textView = findViewById(R.id.textView2);
                        textView.setText(errMsg);
                    }
                });
            }
            try {
                Map<String, String> params = JSONObject.parseObject(msg, new TypeReference<Map<String, String> >(){});
                String datas = params.get("data");
                //dataList stores each message that requested
                List<String> dataList = JSONObject.parseObject(datas, new TypeReference<List<String> >() {});

                //handle these news
                for(int i = 0; i < dataList.size(); i ++) {
                    String singleData = dataList.get(i);
                    params = JSONObject.parseObject(singleData, new TypeReference<Map<String, String> >(){});
                    EventDetail eventDetail = new EventDetail();
                    eventDetail.setEvent_ID(params.get("_id"));
                    eventDetail.setTimeFlag(Long.parseLong(params.get("tflag")));
                    eventDetail.setAuthor(params.get("authors"));
                    eventDetail.setCategory(params.get("category"));
                    eventDetail.setContent(params.get("content"));
                    eventDetail.setLang(params.get("lang"));
                    eventDetail.setTime(params.get("time"));
                    eventDetail.setGreenWhichTime(params.get("date"));
                    AsyncFunctions.DatabaseCURD(eventDetail, "SAVE", (new_statement, new_errMsg) -> {
                        if(!new_statement) {
                            //TODO: Error Occurred when Inserting data into database
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    TextView textView = findViewById(R.id.textView2);
                                    textView.setText(errMsg);
                                }
                            });
                        }
                    });
                }

                long details = SQLite.select(count())
                        .from(EventDetail.class).count();
                //EventDetail eventDetail = details.get(1);
                //final String text = eventDetail.getContent() + "\n" + eventDetail.getGreenWhichTime();
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        TextView textView = findViewById(R.id.textView2);
                        textView.setText(String.valueOf(details));
                    }
                });

            }
            catch (Exception e) {
                final String error = e.toString();
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        TextView textView = findViewById(R.id.textView2);
                        textView.setText(error);
                    }
                });
            }

        });
    }

    private void show() {

    }

    private void getNewPage() {

    }

    private void addHistory(EventDetail detail) {
        HistoryNews historyNews = (HistoryNews) detail;
        Date date = new Date();
        historyNews.setLastViewed(date);
        AsyncFunctions.DatabaseCURD(historyNews, "SAVE", (statement, errMsg) -> {
            if(!statement) {
                //TODO: deal with errMsg
            }
            else {
                long numOfHistories = SQLite.select(count())
                        .from(HistoryNews.class).count();
                if(numOfHistories > 200) {
                    HistoryNews newsToBeDeleted = SQLite.select(min(HistoryNews_Table.LastViewed))
                            .from(HistoryNews.class).querySingle();
                    AsyncFunctions.DatabaseCURD(newsToBeDeleted, "DELETE", (new_statement, new_errMsg) -> {
                        if(!statement) {
                            //TODO: deal with errMsg
                        }
                    });
                }
            }
        });

    }

    private List<HistoryNews> getHistory() {
        return SQLite.select()
                .from(HistoryNews.class)
                .orderBy(HistoryNews_Table.LastViewed, true)
                .queryList();
    }
}