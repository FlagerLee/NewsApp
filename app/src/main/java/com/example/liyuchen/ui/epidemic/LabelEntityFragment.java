package com.example.liyuchen.ui.epidemic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liyuchen.R;

import java.util.ArrayList;
import java.util.List;

public class LabelEntityFragment extends Fragment {

    private RecyclerView info;
    private EntityInfoAdapter info_adapter;
    private List<EntityInfoLayout> list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_entityinfo, container, false);
        init(root);
        return root;
    }

    public LabelEntityFragment(List<EntityInfoLayout> list) {
        this.list = list;
    }

    private void init(View root)
    {
        info=root.findViewById(R.id.recycler_info);
        info.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        info.setLayoutManager(new LinearLayoutManager(root.getContext()));
        info_adapter=new EntityInfoAdapter(list);
        info.setAdapter(info_adapter);
    }
}
