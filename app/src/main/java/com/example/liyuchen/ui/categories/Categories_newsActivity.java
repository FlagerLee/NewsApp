package com.example.liyuchen.ui.categories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.liyuchen.MainActivity;
import com.example.liyuchen.ui.home.newslayout;

import com.example.liyuchen.R;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        try {
            if (tag.equals("聚类1")) {
                InputStream is = this.getClass().getClassLoader().getResourceAsStream("assets/class1.json");
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                JSONArray array = new JSONArray(reader.readLine());
                for(int i = 0; i < array.length(); i ++) list.add(new newslayout("", array.getString(i), "", "", "", false));
            }
            else if (tag.equals("聚类2")) {
                InputStream is = this.getClass().getClassLoader().getResourceAsStream("assets/class2.json");
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                JSONArray array = new JSONArray(reader.readLine());
                for(int i = 0; i < array.length(); i ++) list.add(new newslayout("", array.getString(i), "", "", "", false));
            }
            else if (tag.equals("聚类3")) {
                InputStream is = this.getClass().getClassLoader().getResourceAsStream("assets/class3.json");
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                JSONArray array = new JSONArray(reader.readLine());
                for(int i = 0; i < array.length(); i ++) list.add(new newslayout("", array.getString(i), "", "", "", false));
            }
            else if (tag.equals("聚类4")) {
                InputStream is = this.getClass().getClassLoader().getResourceAsStream("assets/class4.json");
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                JSONArray array = new JSONArray(reader.readLine());
                for(int i = 0; i < array.length(); i ++) list.add(new newslayout("", array.getString(i), "", "", "", false));
            }
        } catch (Exception e) {
            String err = e.toString();
        }
        adapter=new CategoriesNewsAdapter(list);
        recyclerView.setAdapter(adapter);
    }
}