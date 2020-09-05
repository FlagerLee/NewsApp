package com.example.newspart.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.newspart.R;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_covid, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        final TextView textView2 = root.findViewById(R.id.text_dashboard2);
        final TextView textView3 = root.findViewById(R.id.text_dashboard3);
        final TextView textView4 = root.findViewById(R.id.text_dashboard4);
        final TextView textView5 = root.findViewById(R.id.text_dashboard5);
        final TextView textView6 = root.findViewById(R.id.text_dashboard6);
        final TextView textView7 = root.findViewById(R.id.text_dashboard7);
        final TextView textView8 = root.findViewById(R.id.text_dashboard8);
        final TextView textView9 = root.findViewById(R.id.text_dashboard9);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s+"\n"+s+"\n");
                textView2.setText(s+"\n"+s+"\n");
                textView3.setText(s+"\n"+s+"\n");
                textView4.setText(s+"\n"+s+"\n");
                textView5.setText(s+"\n"+s+"\n");
                textView6.setText(s+"\n"+s+"\n");
                textView7.setText(s+"\n"+s+"\n");
                textView8.setText(s+"\n"+s+"\n");
                textView9.setText(s+"\n"+s+"\n");
            }
        });
        return root;
    }
}