package com.example.liyuchen.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liyuchen.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class selectadapter extends RecyclerView.Adapter<selectadapter.ViewHolder> {

    List<String> list=new ArrayList<>();
    String tag;

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textview_tag);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String changed=textView.getText().toString();
                    if(changed.equals("all"))
                        return;
                    else
                    {
                        if(tag.equals("top")) {
                            list.remove(changed);
                            SelectActivity.bottomadapter.addchip(changed);
                            if(changed.equals("news"))
                            {
                                SelectActivity.tag_news="-1";
                            }
                            else if(changed.equals("papers"))
                            {
                                SelectActivity.tag_papers="-1";
                            }
                        }
                        else
                        {
                            list.remove(changed);
                            SelectActivity.topadapter.addchip(changed);
                            if(changed.equals("news"))
                            {
                                SelectActivity.tag_news="1";
                            }
                            else if(changed.equals("papers"))
                            {
                                SelectActivity.tag_papers="1";
                            }
                        }
                        SelectActivity.topadapter.notifyDataSetChanged();
                        SelectActivity.bottomadapter.notifyDataSetChanged();
                    }
                }
            });
        }
    }

    public selectadapter(String tb,List<String> list)
    {
        tag=tb;
        this.list=list;
    }

    public void addchip(String chip)
    {
        list.add(chip);
    }

    @NonNull
    @Override
    public selectadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.taglayout,parent,false);
        selectadapter.ViewHolder holder = new selectadapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull selectadapter.ViewHolder holder, int position) {
        String text=list.get(position);
        holder.textView.setText(text);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
