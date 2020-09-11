package com.example.liyuchen.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.liyuchen.R;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Searchadapter adapter;
    private String tosearch;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_select);
        recyclerView=findViewById(R.id.recycler_search);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        textView=findViewById(R.id.textview_search);
        tosearch=getIntent().getStringExtra("search");
        adapter = new Searchadapter(new ArrayList<newslayout>());
        recyclerView.setAdapter(adapter);
        SearchNews.search(tosearch, param -> {
            if(param != null || param.size() != 0) {
                for(newslayout nlo: param) adapter.addNews(nlo);
            }
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });
        });
        textView.setText(" 搜索文本: "+tosearch);

    }
}