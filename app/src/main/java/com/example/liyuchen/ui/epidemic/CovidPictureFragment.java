package com.example.liyuchen.ui.epidemic;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liyuchen.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CovidPictureFragment extends Fragment {
    private SearchView searchView;
    private RecyclerView result;
    private EntityResultAdapter adapter;
    public static String tosearch;
    private List<String> l=new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_covid_picture, container, false);
        init(root);
        return root;
    }

    private void init(View root)
    {
        if(tosearch != null && !tosearch.equals(""))
        {
            result=root.findViewById(R.id.recycler_entitysearchresult);
            result.addItemDecoration(new DividerItemDecoration(root.getContext(), DividerItemDecoration.VERTICAL));
            result.setLayoutManager(new LinearLayoutManager(root.getContext()));
            EntityQuery.Search(tosearch, list -> {
                if(list != null && list.size() != 0) {
                    String pathPrefix = root.getContext().getFilesDir() + "Entity/";
//                    List<String> l = new ArrayList<>();
                    for(String singleEntity: list) {
                        try {
                            JSONObject entityInfo = new JSONObject(singleEntity);
                            l.add(entityInfo.getString("label"));
                        } catch (Exception e) {
                            String err = e.toString();
                        }
                    }
                    adapter = new EntityResultAdapter(l);
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            result.setAdapter(adapter);
                        }
                    });
                }
            });
            tosearch="";
        }
        searchView=root.findViewById(R.id.search_entity);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                result=root.findViewById(R.id.recycler_entitysearchresult);
                result.addItemDecoration(new DividerItemDecoration(root.getContext(), DividerItemDecoration.VERTICAL));
                result.setLayoutManager(new LinearLayoutManager(root.getContext()));

                EntityQuery.Search(query, list -> {
                    if(list != null && list.size() != 0) {
                        String pathPrefix = root.getContext().getFilesDir() + "Entity/";
//                        List<String> l = new ArrayList<>();
                        for(String singleEntity: list) {
                            try {
                                JSONObject entityInfo = new JSONObject(singleEntity);
                                l.add(entityInfo.getString("label"));
                            } catch (Exception e) {
                                String err = e.toString();
                            }
                        }
                        adapter = new EntityResultAdapter(l);
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                result.setAdapter(adapter);
                            }
                        });
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}
