package com.example.liyuchen.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

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
        recyclerView.setAdapter(adapter);
//        recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener(){
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                if(Math.abs(scrollX-oldScrollX)>0&&(!recyclerView.canScrollVertically(-1)||!recyclerView.canScrollVertically(1)))
//                    HomeFragment.showprogressbar();
//            }
//        });
        return root;
    }

    private void init(View root)
    {
        news=new ArrayList<>();
        for(int i=0;i<100;i++)
        {
            newslayout apnews=new newslayout("hotspot title "+i,"author "+i,"time "+i,"content "+i,false);
            news.add(apnews);
        }
    }
}
