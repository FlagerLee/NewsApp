package com.example.liyuchen.ui.epidemic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liyuchen.R;

public class EntityActivity extends AppCompatActivity {

    private String title;
    private TextView entityname;
    private ImageView entitypic;
    private RecyclerView related;
    private RecyclerView info;
    private RecyclerView entitylist;
    private EntityResultAdapter result_adapter;
    private EntityRelationAdapter relationLayout_adapter;
    private EntityInfoAdapter info_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entityactivity);
        title=getIntent().getStringExtra("title");
        entityname=findViewById(R.id.textview_entityname);
        entitypic=findViewById(R.id.imageview_entitypic);
        related=findViewById(R.id.recycler_related);
        info=findViewById(R.id.recycler_info);
        entitylist=findViewById(R.id.recycler_entitylist);
        relationLayout_adapter=new EntityRelationAdapter();
        related.setAdapter(relationLayout_adapter);
        result_adapter=new EntityResultAdapter();//TODO: string list needed
        entitylist.setAdapter(result_adapter);
        info.setAdapter(info_adapter);
    }
}