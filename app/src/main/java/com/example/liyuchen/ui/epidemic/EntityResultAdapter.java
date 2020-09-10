package com.example.liyuchen.ui.epidemic;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liyuchen.R;
import com.example.liyuchen.ui.home.newsadapter;

import java.util.List;


public class EntityResultAdapter extends RecyclerView.Adapter<EntityResultAdapter.ViewHolder> {

    List<String> namelist;

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textview_entitytitle);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(itemView.getContext(),EntityActivity.class);
                    intent.putExtra("title",textView.getText());
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }

    public EntityResultAdapter(List<String> list)
    {
        this.namelist=list;
    }

    @NonNull
    @Override
    public EntityResultAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.entitytitlelayout,parent,false);
        EntityResultAdapter.ViewHolder holder = new EntityResultAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EntityResultAdapter.ViewHolder holder, int position) {
        holder.textView.setText(namelist.get(position));
    }

    @Override
    public int getItemCount() {
        return namelist.size();
    }

}
