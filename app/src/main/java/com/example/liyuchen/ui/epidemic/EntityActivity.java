package com.example.liyuchen.ui.epidemic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.liyuchen.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EntityActivity extends AppCompatActivity {

    private String title;
    private TextView entityname;
    private ImageView entitypic;
    private TextView entityintroduction;
    private RecyclerView related;
    private RecyclerView info;
    private EntityRelationAdapter relationLayout_adapter;
    private EntityInfoAdapter info_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entityactivity);
        title=getIntent().getStringExtra("title");
        entityname=findViewById(R.id.textview_entityname);
        entityname.setText(title);
        entityintroduction=findViewById(R.id.textView_introduction);
        entitypic=findViewById(R.id.imageview_entitypic);
        related=findViewById(R.id.recycler_related);
        related.setLayoutManager(new LinearLayoutManager(this));
        info=findViewById(R.id.recycler_info);
        info.setLayoutManager(new LinearLayoutManager(this));

        EntityQuery.Search(title, list -> {
            String Introduction = null;
            List<EntityInfoLayout> infoLayouts = new ArrayList<>();
            List<EntityRelationLayout> relationLayouts = new ArrayList<>();
            String ImgURL = null;
            try {
                for(String entity: list) {
                    JSONObject Entity = new JSONObject(entity);
                    String label = Entity.getString("label");
                    if (!label.equals(title)) continue;
                    else {
                        JSONObject abstractInfo = new JSONObject(Entity.getString("abstractInfo"));
                        Introduction = "Wiki: " + abstractInfo.getString("enwiki") + "\nWiki(zh): " + abstractInfo.getString("zhwiki") + "\n百度: " + abstractInfo.getString("baidu");

                        JSONObject COVID = new JSONObject(abstractInfo.getString("COVID"));

                        String properties = COVID.getString("properties");
                        String regex = "\"([^\"]+)\"\\s*:\\s*\"([^\"]+)\"";
                        Pattern p = Pattern.compile(regex);
                        Matcher m = p.matcher(properties);

                        while (m.find()) {
                            infoLayouts.add(new EntityInfoLayout(m.group(0), m.group(1)));
                        }

                        JSONArray relations = new JSONArray(COVID.getString("relations"));
                        for (int i = 0; i < relations.length(); i++) {
                            String forward = null;
                            JSONObject relation = new JSONObject(relations.getString(i));
                            if (relation.getBoolean("forward")) forward = "-->";
                            else forward = "<--";

                            relationLayouts.add(new EntityRelationLayout(relation.getString("relation"), forward, relation.getString("label")));
                        }

                        ImgURL = Entity.getString("img");

                    }
                }


            } catch (Exception e) {

            }
            final String introduction = Introduction;
            final String imgURL = ImgURL;
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    entityintroduction.setText(introduction);
                    relationLayout_adapter = new EntityRelationAdapter(relationLayouts);
                    info_adapter = new EntityInfoAdapter(infoLayouts);

                    if(imgURL != null && imgURL != "") {
                        entitypic.setImageDrawable(LoadImageFromWebOperations(imgURL, title));
                    }


                    related.setAdapter(relationLayout_adapter);
                    info.setAdapter(info_adapter);
                }

                private Drawable LoadImageFromWebOperations(String url, String label) {
                    try {
                        InputStream input = (InputStream) new URL(url).getContent();
                        Drawable d = Drawable.createFromStream(input, label);
                        return d;
                    } catch (Exception e) {
                        return null;
                    }
                }

            });
        });

    }
}