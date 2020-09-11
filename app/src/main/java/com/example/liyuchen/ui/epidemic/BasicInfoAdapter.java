package com.example.liyuchen.ui.epidemic;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liyuchen.R;

public class BasicInfoAdapter extends RecyclerView.Adapter<BasicInfoAdapter.ViewHolder> {

    public Drawable d = null;
    public String Introduction = null;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView entityname;
        private ImageView entitypic;
        private TextView entityintroduction;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            entityname=itemView.findViewById(R.id.textview_entityname);
            entityintroduction=itemView.findViewById(R.id.textView_introduction);
            entitypic=itemView.findViewById(R.id.imageview_entitypic);
        }
    }
    @NonNull
    @Override
    public BasicInfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.entityintroductionlayout,parent,false);
        BasicInfoAdapter.ViewHolder holder = new BasicInfoAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BasicInfoAdapter.ViewHolder holder, int position) {
        holder.entityname.setText(EntityActivity.title);
        holder.entitypic.setImageDrawable(d);
        holder.entityintroduction.setText(Introduction);
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
