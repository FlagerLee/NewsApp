package com.example.liyuchen.ui.epidemic;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liyuchen.R;

public class CovidPictureFragment extends Fragment {
    private SearchView searchView;
    private RecyclerView result;
    private EntityResultAdapter adapter;
    public static String tosearch;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_covid_picture, container, false);
        init(root);
        return root;
    }

    private void init(View root)
    {
        if(!tosearch.equals(""))
        {
            result=root.findViewById(R.id.recycler_entitysearchresult);
            adapter=new EntityResultAdapter();//TODO: add a List<String> as parameter
            result.setAdapter(adapter);
            tosearch="";
        }
        searchView=root.findViewById(R.id.search_entity);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                result=root.findViewById(R.id.recycler_entitysearchresult);
                adapter=new EntityResultAdapter();//TODO: add a List<String> as parameter
                result.setAdapter(adapter);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}
