package com.example.liyuchen.ui.home;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liyuchen.R;

import java.util.ArrayList;
import java.util.List;

public class selectadapter extends RecyclerView.Adapter<selectadapter.ViewHolder> {

    List<String> top=new ArrayList<>();
    List<String> bottom=new ArrayList<>();


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textview_tag);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String changed=v.getContext().toString();
                    if(changed.equals("all"))
                        return;
                    else
                    {
                        if(top.contains(changed))
                        {
                            top.remove(changed);
                            bottom.add(changed);
                        }
                    }
                }
            });
        }
    }

    public selectadapter()
    {
        top.add("all");
        top.add("news");
        top.add("papers");
    }

    @NonNull
    @Override
    public selectadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull selectadapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
