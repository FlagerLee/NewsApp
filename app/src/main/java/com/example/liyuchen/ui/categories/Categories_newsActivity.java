package com.example.liyuchen.ui.categories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.liyuchen.Async.cluster;
import com.example.liyuchen.ui.home.newsActivity;
import com.example.liyuchen.ui.home.newsadapter;
import com.example.liyuchen.ui.home.newslayout;

import com.example.liyuchen.R;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class Categories_newsActivity extends AppCompatActivity {

    private String tag;
    private List<newslayout> list;
    private RecyclerView recyclerView;
    private CategoriesNewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_news_activity);
        tag=getIntent().getStringExtra("tag");
        recyclerView=findViewById(R.id.recycler_categoriesnews);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        list=new ArrayList<>();
        list.add(new newslayout("1","123","234","345","456",false));
        try {
            if (tag.equals("聚类1")) {
                JSONArray array = new JSONArray(cluster.json1);
                for(int i = 0; i < array.length(); i ++) list.add(new newslayout("", array.getString(i), "", "", "", false));
            }
            else if (tag.equals("聚类2")) {
                JSONArray array = new JSONArray(cluster.json2);
                for(int i = 0; i < array.length(); i ++) list.add(new newslayout("", array.getString(i), "", "", "", false));
            }
            else if (tag.equals("聚类3")) {
                JSONArray array = new JSONArray(cluster.json3);
                for(int i = 0; i < array.length(); i ++) list.add(new newslayout("", array.getString(i), "", "", "", false));
            }
            else if (tag.equals("聚类4")) {
                JSONArray array = new JSONArray(cluster.json4);
                for(int i = 0; i < array.length(); i ++) list.add(new newslayout("", array.getString(i), "", "", "", false));
            }
        } catch (Exception e) {

        }
        adapter=new CategoriesNewsAdapter(list);
        recyclerView.setAdapter(adapter);
    }
}