package com.example.liyuchen.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.liyuchen.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private TabLayout tabs;
    private List<String>titles;
    private List<Fragment>pages;
    private HomeViewAdapter adapter;
    private ViewPager viewpager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        init(root);
        return root;
    }

    private void init(View root)
    {
        tabs=root.findViewById(R.id.tablayout_home);

        titles=new ArrayList<>();
        titles.add("时事热点");
        titles.add("已存新闻");
        titles.add("浏览历史");

        pages=new ArrayList<>();
        pages.add(new HotspotFragment());
        pages.add(new SavedFragment());
        pages.add(new HistoryFragment());

        adapter=new HomeViewAdapter(getChildFragmentManager(),pages,titles);
        viewpager=root.findViewById(R.id.viewpager_home);

        tabs.setupWithViewPager(viewpager);
        viewpager.setAdapter(adapter);
    }
}