package com.example.liyuchen.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.liyuchen.R;
import com.example.liyuchen.ui.notifications.NotificationsFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private TabLayout tabs;
    private List<String>titles;
    private List<Fragment>pages;
    private HomeViewAdapter adapter;
    private ViewPager viewpager;
    private SearchView searchview;
    private ImageView imageview;
    private String tosearch;
    private int chips_total;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        init(root);
        return root;
    }

    private void init(View root)
    {
        tabs=root.findViewById(R.id.tablayout_home);
        searchview=root.findViewById(R.id.home_searchview);
        imageview=root.findViewById(R.id.home_imageview_chip);
        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(root.getContext(),SelectActivity.class);
                startActivity(intent);
            }
        });

        titles=new ArrayList<>();
        titles.add("ALL");
        titles.add("NEWS");
        titles.add("PAPERS");

        pages=new ArrayList<>();
        pages.add(new HotspotFragment());
        pages.add(new NewsFragment());
        pages.add(new PaperFragement());

        adapter=new HomeViewAdapter(getChildFragmentManager(),pages,titles);
        viewpager=root.findViewById(R.id.viewpager_home);

        tabs.setupWithViewPager(viewpager);
        viewpager.setAdapter(adapter);
    }


}