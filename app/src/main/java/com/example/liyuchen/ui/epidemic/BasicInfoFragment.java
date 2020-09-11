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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liyuchen.R;

import java.io.InputStream;
import java.net.URL;

public class BasicInfoFragment extends Fragment {

    private BasicInfoAdapter adapter;
    private RecyclerView recyclerView;

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
        adapter=new BasicInfoAdapter();
        recyclerView=root.findViewById(R.id.recycler_entityintroduction);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setAdapter(adapter);

        adapter.Introduction = this.Introduction;

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    InputStream input = (InputStream) new URL(ImgURL).getContent();

                    Drawable d = Drawable.createFromStream(input, " ");

                    adapter.d = d;

                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });
                } catch (Exception e) {

                }
            }
        };
        thread.start();

    }
}
