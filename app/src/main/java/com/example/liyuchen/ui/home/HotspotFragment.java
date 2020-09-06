package com.example.liyuchen.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.liyuchen.Async.AsyncFunctions;
import com.example.liyuchen.Async.EventDetail;
import com.example.liyuchen.Async.Information;
import com.example.liyuchen.MainActivity;
import com.example.liyuchen.R;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.raizlabs.android.dbflow.sql.language.Method.count;

public class HotspotFragment extends Fragment {

    private List<newslayout> news;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home_hotspot, container, false);
        init(root);
        RecyclerView recyclerView=root.findViewById(R.id.recyclerview_hotspot);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        newsadapter adapter=new newsadapter(news);
        Refresh.refresh("news", 1, 20, (newslayouts) -> {
            for(newslayout layout: newslayouts) adapter.addnews(layout);
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });
        });
        recyclerView.setAdapter(adapter);
        return root;
    }

    private void init(View root)
    {
        news=new ArrayList<>();
        //newslayout apnews=new newslayout("hotspot title ","author ","time ","content ");
        // news.add(apnews);
    }

}

