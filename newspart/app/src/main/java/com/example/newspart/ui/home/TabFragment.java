package com.example.newspart.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.newspart.R;

public class TabFragment extends Fragment{
    private static String tab_number;
    public static TabFragment newInstance(int position)
    {
        TabFragment fragment = new TabFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(tab_number, position);
        fragment.setArguments(bundle);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public void onStart()
    {
        super.onStart();
        TextView textview=getView().findViewById(R.id.text_home);
        textview.setText("a try, this is tab "+tab_number);
    }
}
