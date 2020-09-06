package com.example.liyuchen.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.liyuchen.R;

public class newsActivity extends AppCompatActivity {

    private TextView title;
    private TextView author;
    private TextView time;
    private TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        title=findViewById(R.id.news_title);
        author=findViewById(R.id.news_author);
        time=findViewById(R.id.news_time);
        content=findViewById(R.id.news_content);
        title.setText(getIntent().getStringExtra("title"));
        author.setText(getIntent().getStringExtra("author"));
        time.setText(getIntent().getStringExtra("time"));
        content.setText(getIntent().getStringExtra("content"));
    }
}