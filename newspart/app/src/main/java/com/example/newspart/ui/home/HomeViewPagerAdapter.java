package com.example.newspart.ui.home;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.newspart.R;

import java.util.List;

public class HomeViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> context;
    private static int[] titles={R.string.home_table_hotspot,R.string.home_table_saved,R.string.home_table_history};

    public HomeViewPagerAdapter(List<Fragment> context,FragmentManager fm) {
        super(fm);
        this.context=context;
    }

    @Nullable
    @Override
    public String getPageTitle(int position) {
        return titles[position]+"";
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return TabFragment.newInstance(position+1);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
