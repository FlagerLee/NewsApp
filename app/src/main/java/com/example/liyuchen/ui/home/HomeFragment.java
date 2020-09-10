package com.example.liyuchen.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.liyuchen.R;
import com.google.android.material.tabs.TabItem;
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
    private TabItem tab_news;
    private TabItem tab_papers;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        init(root);
        return root;
    }

    private void init(View root)
    {
        tabs=root.findViewById(R.id.tablayout_home);
//        tab_news=root.findViewById(R.id.tabitem_news);
//        tab_papers=root.findViewById(R.id.tabitem_papers);
        searchview=root.findViewById(R.id.home_searchview);
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent=new Intent(root.getContext(),SearchActivity.class);
                intent.putExtra("search",query);
                searchview.setFocusable(false);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchview.setFocusable(true);
                return false;
            }
        });
        imageview=root.findViewById(R.id.home_imageview_chip);
        imageview.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),SelectActivity.class);
                if(titles.contains("news"))
                    intent.putExtra("news","1");
                else
                    intent.putExtra("news","-1");
                if(titles.contains("papers"))
                    intent.putExtra("papers","1");
                else
                    intent.putExtra("papers","-1");
                startActivityForResult(intent,1);
            }
        });

        titles=new ArrayList<>();
        titles.add("all");
        titles.add("news");
        titles.add("papers");
        for(int i=0;i<titles.size();i++)
        {
            tabs.addTab(tabs.newTab().setText(titles.get(i).toUpperCase()));
        }

        pages=new ArrayList<>();
        pages.add(new HotspotFragment());
        pages.add(new NewsFragment());
        pages.add(new PaperFragement());

        adapter=new HomeViewAdapter(getChildFragmentManager(),pages,titles);
        viewpager=root.findViewById(R.id.viewpager_home);

        tabs.setupWithViewPager(viewpager);
        viewpager.setAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&resultCode==1)
        {
            List<String> temp=new ArrayList<>();
            List<Fragment> temppages=new ArrayList<>();
            temp.add("all");
            temppages.add(new HotspotFragment());
            if(data.getStringExtra("news").equals("1"))
            {
                temp.add("news");
                temppages.add(new NewsFragment());
            }
            if(data.getStringExtra("papers").equals("1"))
            {
                temp.add("papers");
                temppages.add(new PaperFragement());
            }
            titles=temp;
            pages=temppages;
            adapter = new HomeViewAdapter(getChildFragmentManager(), pages, titles);
            viewpager.setAdapter(adapter);
            tabs.removeAllTabs();
            for(int i=0;i<titles.size();i++)
            {
                tabs.addTab(tabs.newTab().setText(titles.get(i)));
            }
            tabs.setupWithViewPager(viewpager);
        }
    }
}