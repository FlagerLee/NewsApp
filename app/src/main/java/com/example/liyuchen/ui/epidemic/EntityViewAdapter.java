package com.example.liyuchen.ui.epidemic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class EntityViewAdapter extends FragmentPagerAdapter{
    List<Fragment> pages;
    List<String> titles;

    public EntityViewAdapter(@NonNull FragmentManager fm, List<Fragment> pages, List<String> titles) {
        super(fm);
        this.pages=pages;
        this.titles=titles;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return pages.get(position);
    }

    @Override
    public int getCount() {
        return pages.size();
    }
}