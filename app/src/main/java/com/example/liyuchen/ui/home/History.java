package com.example.liyuchen.ui.home;

import com.example.liyuchen.Async.AsyncFunctions;
import com.example.liyuchen.Async.EventDetail;
import com.example.liyuchen.Async.EventDetail_Table;
import com.example.liyuchen.Async.HistoryNews;
import com.example.liyuchen.Async.HistoryNews_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.Date;

import static com.raizlabs.android.dbflow.sql.language.Method.min;

public class History {
    static public void setHistory(final String id) {
        Thread thread = new Thread() {
            public void run() {
                try {
                    HistoryNews historyNews = new HistoryNews();
                    historyNews.setHistoryNews(SQLite.select()
                            .from(EventDetail.class)
                            .where(EventDetail_Table.Event_ID.eq(id))
                            .querySingle());
                    AsyncFunctions.DatabaseCURD(historyNews, "SAVE", (statement, errMsg) -> {
                        if(!statement) {
                            //TODO: deal with errMsg
                        }
                        else {
                            long numOfHistories = SQLite.select()
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
                catch (Exception e) {

                }
            }
        };
        thread.start();
    }
    static public boolean inHistory(final String id) {
        HistoryNews historyNews = SQLite.select()
                .from(HistoryNews.class)
                .where(HistoryNews_Table.Event_ID.eq(id))
                .querySingle();
        if(historyNews == null) return false;
        else return true;
    }

}
