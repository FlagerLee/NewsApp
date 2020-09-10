package com.example.liyuchen.ui.epidemic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liyuchen.R;

import java.util.List;

public class EntityInfoAdapter extends RecyclerView.Adapter<EntityInfoAdapter.ViewHolder> {

    private List<EntityInfoLayout> infolist;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView content;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.textView_entityinfoname);
            content=itemView.findViewById(R.id.textView_entityinfocontent);
        }
    }

    public EntityInfoAdapter(List<EntityInfoLayout> list) {
        infolist = list;
    }

    @NonNull
    @Override
    public EntityInfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.entityinfolayout,parent,false);
        EntityInfoAdapter.ViewHolder holder = new EntityInfoAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EntityInfoAdapter.ViewHolder holder, int position) {
        holder.name.setText(infolist.get(position).name);
        holder.content.setText(infolist.get(position).content);
    }

    @Override
    public int getItemCount() {
        return infolist.size();
    }
}
