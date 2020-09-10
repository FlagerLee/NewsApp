package com.example.liyuchen.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liyuchen.Async.HistoryNews;
import com.example.liyuchen.R;
import com.example.liyuchen.ui.home.History;
import com.example.liyuchen.ui.home.newsadapter;
import com.example.liyuchen.ui.home.newslayout;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

    private List<newslayout> news;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notification_history, container, false);

        this.news = new ArrayList<>();
        List<HistoryNews> historyNews = History.getHistory();
        for(HistoryNews hNews: historyNews) {
            this.news.add(new newslayout(hNews.getEvent_ID(), hNews.getTitle(), hNews.getAuthor(), hNews.getTime(), hNews.getContent(), false));
        }

        RecyclerView recyclerView=root.findViewById(R.id.recyclerview_history);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        newsadapter adapter=new newsadapter(news,this.getContext());
        recyclerView.setAdapter(adapter);
        return root;
    }
}
