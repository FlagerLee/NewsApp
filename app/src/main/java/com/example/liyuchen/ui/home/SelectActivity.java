package com.example.liyuchen.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import com.example.liyuchen.MainActivity;
import com.example.liyuchen.R;
import com.google.android.material.tabs.TabItem;
import com.raizlabs.android.dbflow.sql.language.Operator;

import java.util.ArrayList;
import java.util.List;

public class SelectActivity extends AppCompatActivity {

    public static String tag_news="1";
    public static String tag_papers="1";
    private RecyclerView have;
    private RecyclerView nothave;
    public static selectadapter topadapter;
    public static selectadapter bottomadapter;
    private List<String> list_top=new ArrayList<>();
    private List<String> list_bottom=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectactivity);
        tag_news=getIntent().getStringExtra("news");
        tag_papers=getIntent().getStringExtra("papers");
        have = findViewById(R.id.select_recycler_have);
        nothave = findViewById(R.id.select_recycler_nothave);
        GridLayoutManager topgridLayoutManager = new GridLayoutManager(this, 3);
        GridLayoutManager bottomgridLayoutManager = new GridLayoutManager(this, 3);
        topgridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        have.setLayoutManager(topgridLayoutManager);
        bottomgridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        nothave.setLayoutManager(bottomgridLayoutManager);
        list_top.add("all");
        if(tag_news.equals("1"))
            list_top.add("news");
        else
            list_bottom.add("news");
        if(tag_papers.equals("1"))
            list_top.add("papers");
        else
            list_bottom.add("papers");
        topadapter = new selectadapter("top",list_top);
        bottomadapter = new selectadapter("bottom",list_bottom);
        have.setAdapter(topadapter);
        nothave.setAdapter(bottomadapter);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent();
            intent.putExtra("news",tag_news);
            intent.putExtra("papers",tag_papers);
            setResult(1, intent);
            finish();
            return true;
        } else
            return super.onKeyDown(keyCode, event);
    }
}