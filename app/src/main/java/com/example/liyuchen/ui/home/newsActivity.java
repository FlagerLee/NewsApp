package com.example.liyuchen.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import com.example.liyuchen.R;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

public class newsActivity extends Activity {

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

        TextView share = findViewById(R.id.share);
        String shareContent = "分享";
        SpannableString text = new SpannableString(shareContent);
        text.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Weibo.getAccessToken(s -> {
                    String ss = s;
                });
            }
        }, 0, shareContent.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        share.setText(text);
        share.setMovementMethod(LinkMovementMethod.getInstance());


//        WbSdk.install(this, new AuthInfo(this, AppKey, RedirectURL, ""));

//        mSsoHandler = new SsoHandler(newsActivity.this);
    }

    private SsoHandler mSsoHandler = null;

    private String AppKey = "4073260475";
    private String AppSecret = "c23f863f31c44824afcabf764a503e77";
    private String RedirectURL = "https://www.flagerlee.com";
}