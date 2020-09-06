package com.example.newspart.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.newspart.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {
    private TabLayout tabs;
    private List<String> titles;
    private List<Fragment>pages;
    private CovidViewAdapter adapter;
    private ViewPager viewpager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_covid, container, false);
        init(root);
        return root;
    }

    private void init(View root)
    {
        tabs=root.findViewById(R.id.tablayout_covid);

        titles=new ArrayList<>();
        titles.add("最新数据");
        titles.add("疫情图谱");
        titles.add("知疫学者");

        pages=new ArrayList<>();
        pages.add(new CovidDataFragment());
        pages.add(new CovidPictureFragment());
        pages.add(new CovidScientistFragment());

        adapter=new CovidViewAdapter(getChildFragmentManager(),pages,titles);
        viewpager=root.findViewById(R.id.viewpager_covid);

        tabs.setupWithViewPager(viewpager);
        viewpager.setAdapter(adapter);
    }
}