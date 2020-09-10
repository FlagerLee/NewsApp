package com.example.liyuchen.ui.epidemic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liyuchen.R;

import java.util.ArrayList;
import java.util.List;

public class CovidScientistFragment extends Fragment {

    private RecyclerView recyclerView;
    private ScientistAdapter adapter;
    private List<Scientistlayout> scientists;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_covid_scientist, container, false);
        init(root);
        return root;
    }

    private void init(View root)
    {
        scientists=new ArrayList<>();
        recyclerView=root.findViewById(R.id.recycler_scientist);
        adapter=new ScientistAdapter(scientists);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setAdapter(adapter);
    }
}
