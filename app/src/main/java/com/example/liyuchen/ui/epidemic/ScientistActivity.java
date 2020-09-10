package com.example.liyuchen.ui.epidemic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liyuchen.R;

public class ScientistActivity extends AppCompatActivity {

    private String name;
    private TextView view_name;
    private String email;
    private TextView view_email;
    private String homepage;
    private TextView view_homepage;
    private String position;
    private TextView view_position;
    private String affilication;
    private TextView view_affilication;
    private String bio;
    private TextView view_bio;
    private String education;
    private TextView view_education;
    private String works;
    private TextView view_works;
    private String notes;
    private TextView view_notes;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scientistactivity);
        name=getIntent().getStringExtra("name");
        view_name=findViewById(R.id.textView_scientist_name);
        view_name.setText(name);
        view_email=findViewById(R.id.textView_scientist_email);
        view_email.setText(email);
        view_homepage=findViewById(R.id.textView_scientist_homepage);
        view_homepage.setText(homepage);
        view_position=findViewById(R.id.textView_scientist_position);
        view_position.setText(position);
        view_affilication=findViewById(R.id.textView_scientist_affilication);
        view_affilication.setText(affilication);
        view_bio=findViewById(R.id.textView_scientist_bio);
        view_bio.setText(bio);
        view_education=findViewById(R.id.textView_scientist_education);
        view_education.setText(education);
        view_works=findViewById(R.id.textView_scientist_work);
        view_works.setText(works);
        view_notes=findViewById(R.id.textView_scientist_note);
        view_notes.setText(notes);
        image=findViewById(R.id.imageView_scientist_avatar);
    }
}