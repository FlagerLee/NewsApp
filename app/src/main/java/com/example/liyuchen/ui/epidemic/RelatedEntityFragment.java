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

public class RelatedEntityFragment extends Fragment {

    private RecyclerView related;
    private EntityRelationAdapter relationLayout_adapter;
    private List<EntityRelationLayout> list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_entityrelated, container, false);
        init(root);
        return root;
    }

    private void init(View root)
    {
        related=root.findViewById(R.id.recycler_related);
        related.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        related.setLayoutManager(new LinearLayoutManager(root.getContext()));
        list=new ArrayList<>();
        list.add(new EntityRelationLayout("this","that","人"));
        relationLayout_adapter=new EntityRelationAdapter(list);
        related.setAdapter(relationLayout_adapter);
    }

}
