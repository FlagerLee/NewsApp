package com.example.newspart.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newspart.R;

import java.util.ArrayList;
import java.util.List;

public class SavedFragment extends Fragment {

    private List<newslayout> news;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home_saved, container, false);
        init(root);
        RecyclerView recyclerView=root.findViewById(R.id.recyclerview_saved);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        newsadapter adapter=new newsadapter(news);
        recyclerView.setAdapter(adapter);
        return root;
    }

    private void init(View root)
    {
        news=new ArrayList<>();
        for(int i=0;i<100;i++)
        {
            newslayout apnews=new newslayout("saved title "+i,"author "+i,"time "+i,"content "+i);
            news.add(apnews);
        }
    }
}
