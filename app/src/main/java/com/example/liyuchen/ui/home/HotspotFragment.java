package com.example.liyuchen.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liyuchen.R;

import java.util.ArrayList;
import java.util.List;

public class HotspotFragment extends Fragment {

    private List<newslayout> news;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home_hotspot, container, false);
        init(root);
        RecyclerView recyclerView=root.findViewById(R.id.recyclerview_hotspot);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        newsadapter adapter=new newsadapter(news,this.getContext());
        Refresh.refresh("news", 1, 20, (newslayouts) -> {
            for(newslayout layout: newslayouts) adapter.addnews(layout);
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });
        });
        recyclerView.setAdapter(adapter);
        return root;
    }

    private void init(View root)
    {
        news=new ArrayList<>();
    }
}
