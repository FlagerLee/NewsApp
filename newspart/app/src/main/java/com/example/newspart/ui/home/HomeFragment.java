package com.example.newspart.ui.home;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.newspart.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private static int[] titles={R.string.home_table_hotspot,R.string.home_table_saved,R.string.home_table_history};
    private ViewPager viewpager;
    private HomeViewPagerAdapter adapter;
    private List<Fragment> fragmentlist=new ArrayList<Fragment>();
    private TabLayout tab;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);

        tab=root.findViewById(R.id.tablayout_home);
        viewpager=root.findViewById(R.id.viewpager_home);
        for(int i=0;i<titles.length;i++)
        {
            tab.addTab(tab.newTab());
            tab.getTabAt(i).setText(this.getString(titles[i]));
            fragmentlist.add(TabFragment.newInstance(i+1));
        }
        tab.setupWithViewPager(viewpager);
        adapter=new HomeViewPagerAdapter(fragmentlist,getParentFragmentManager());
        viewpager.setAdapter(adapter);


        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}