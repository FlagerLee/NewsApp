package com.example.liyuchen.ui.epidemic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liyuchen.R;

public class BasicInfoFragment extends Fragment {

    private BasicInfoAdapter adapter;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_entityintroduction, container, false);
        init(root);
        return root;
    }

    private void init(View root)
    {
        adapter=new BasicInfoAdapter();
        recyclerView=root.findViewById(R.id.recycler_entityintroduction);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setAdapter(adapter);
    }
}
