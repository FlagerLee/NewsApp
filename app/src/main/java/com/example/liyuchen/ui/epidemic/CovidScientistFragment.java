package com.example.liyuchen.ui.epidemic;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liyuchen.R;

import org.json.JSONObject;

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
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        adapter=new ScientistAdapter(scientists, this.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setAdapter(adapter);

        Experts.GetExpertsInfo(list -> {
            if(list != null && list.size() != 0) {
                List<Scientistlayout> scientistList = new ArrayList<>();
                for(String expert: list) {
                    try {
                        JSONObject object = new JSONObject(expert);
                        String avatar = object.getString("avatar");
                        String name = object.getString("name_zh");
                        if(name.equals("")) name = object.getString("name");
                        boolean isPassedAway = object.getBoolean("is_passedaway");
                        String isAlive;
                        if(isPassedAway) isAlive = "追忆学者";
                        else isAlive = "";
                        scientistList.add(new Scientistlayout(name, isAlive, avatar));
                    }
                    catch (Exception e) {

                    }
                }

                adapter.setScientists(scientistList);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
}
