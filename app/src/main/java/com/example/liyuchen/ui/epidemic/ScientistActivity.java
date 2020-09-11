package com.example.liyuchen.ui.epidemic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liyuchen.R;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScientistActivity extends AppCompatActivity {

    private String name = "";
    private TextView view_name;
    private TextView view_email;
    private TextView view_homepage;
    private TextView view_position;
    private TextView view_affilication;
    private TextView view_bio;
    private TextView view_education;
    private TextView view_works;
    private TextView view_notes;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scientistactivity);
        view_email=findViewById(R.id.textView_scientist_email);
        view_homepage=findViewById(R.id.textView_scientist_homepage);
        view_position=findViewById(R.id.textView_scientist_position);
        view_affilication=findViewById(R.id.textView_scientist_affilication);
        view_bio=findViewById(R.id.textView_scientist_bio);
        view_education=findViewById(R.id.textView_scientist_education);
        view_works=findViewById(R.id.textView_scientist_work);
        view_notes=findViewById(R.id.textView_scientist_note);
        image=findViewById(R.id.imageView_scientist_avatar);


        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("name");
        String bundleString = bundle.toString();
        String regex = "name=(.*)\\}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(bundleString);
        m.find();
        name = m.group(1);
        view_name=findViewById(R.id.textView_scientist_name);
        view_name.setText(name);

        Experts.GetExpertsInfo(list -> {
            if(list != null && list.size() != 0) {

                for(String expert: list) {
                    try {
                        JSONObject expertInfo = new JSONObject(expert);
                        String expertName = expertInfo.getString("name_zh");
                        if(expertName.equals("")) expertName = expertInfo.getString("name");
                        if(!expertName.equals(name)) continue;

                        String avatar = expertInfo.getString("avatar");

                        JSONObject profile = new JSONObject(expertInfo.getString("profile"));

                        String email = profile.getString("email");
                        String homepage = profile.getString("homepage");
                        String position = profile.getString("position");
                        String affiliation = profile.getString("affiliation");
                        String bio = profile.getString("bio");
                        String education = profile.getString("edu");
                        String work = profile.getString("work");
                        String notes = profile.getString("note");

                        InputStream input = (InputStream) new URL(avatar).getContent();

                        Drawable d = Drawable.createFromStream(input, name);
                        int width = d.getIntrinsicWidth(), height = d.getIntrinsicHeight();
                        Bitmap.Config config = d.getOpacity() != PixelFormat.OPAQUE
                                ? Bitmap.Config.ARGB_8888
                                : Bitmap.Config.RGB_565;
                        Bitmap bitmap = Bitmap.createBitmap(width, height, config);
                        Canvas canvas = new Canvas(bitmap);
                        d.setBounds(0, 0, width, height);
                        d.draw(canvas);
                        Bitmap newbmp = Bitmap.createScaledBitmap(bitmap, 450, 600, true);

                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                view_email.setText(email);
                                view_homepage.setText(homepage);
                                view_position.setText(position);
                                view_affilication.setText(affiliation);
                                view_bio.setText(bio);
                                view_education.setText(education);
                                view_works.setText(work);
                                view_notes.setText(notes);

                                image.setImageBitmap(newbmp);
                            }
                        });

                    } catch (Exception e) {
                        String err = e.toString();
                    }
                }


            }
        });
    }
}