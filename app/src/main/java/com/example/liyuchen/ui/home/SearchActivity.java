package com.example.liyuchen.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.liyuchen.R;

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
        textView=findViewById(R.id.textview_search);
        tosearch=getIntent().getStringExtra("search");
        textView.setText("tosearch: "+tosearch);
    }
}