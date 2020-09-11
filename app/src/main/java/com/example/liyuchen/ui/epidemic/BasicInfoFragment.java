package com.example.liyuchen.ui.epidemic;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.liyuchen.R;

import java.io.InputStream;
import java.net.URL;

public class BasicInfoFragment extends Fragment {

    private TextView entityname;
    private ImageView entitypic;
    private TextView entityintroduction;

    private String ImgURL;
    private String Introduction;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_entityintroduction, container, false);
        init(root);
        return root;
    }

    public BasicInfoFragment(String Introduction, String ImgURL) {
        this.Introduction = Introduction;
        this.ImgURL = ImgURL;
    }

    private void init(View root)
    {
        entityname=root.findViewById(R.id.textview_entityname);
        entityname.setText(EntityActivity.title);
        entityintroduction=root.findViewById(R.id.textView_introduction);
        entitypic=root.findViewById(R.id.imageview_entitypic);

        entityintroduction.setText(this.Introduction);
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    InputStream input = (InputStream) new URL(ImgURL).getContent();

                    Drawable d = Drawable.createFromStream(input, (String)entityname.getText());

                    entitypic.setImageDrawable(d);

                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            entitypic.setImageDrawable(d);
                            entitypic.invalidate();
                        }
                    });
                } catch (Exception e) {

                }
            }
        };
        thread.start();
    }
}
