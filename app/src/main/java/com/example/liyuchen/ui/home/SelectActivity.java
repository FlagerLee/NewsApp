package com.example.liyuchen.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.liyuchen.R;
import com.google.android.material.tabs.TabItem;

public class SelectActivity extends AppCompatActivity {

    private RecyclerView have;
    private RecyclerView nothave;
    private TabItem item_news;
    private TabItem item_paper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectactivity);
        have=findViewById(R.id.select_recycler_have);
        nothave=findViewById(R.id.select_recycler_nothave);
        item_news=findViewById(R.id.tabitem_news);
        item_paper=findViewById(R.id.tabitem_papers);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,3);
        gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        have.setLayoutManager(gridLayoutManager);
        nothave.setLayoutManager(gridLayoutManager);
    }
}