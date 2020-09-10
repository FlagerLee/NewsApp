package com.example.liyuchen.ui.epidemic;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liyuchen.R;

import java.util.List;

public class ScientistAdapter extends RecyclerView.Adapter<ScientistAdapter.ViewHolder> {

    private List<Scientistlayout> scientists;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView isalive;
        private ImageView image;
        private LinearLayout line;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            line=itemView.findViewById(R.id.linelayout_scientist);
            name=itemView.findViewById(R.id.textView_scientistname_name);
            isalive=itemView.findViewById(R.id.textView_scientistname_isalive);
            image=itemView.findViewById(R.id.imageView_scientistname);
            line.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(v.getContext(),ScientistActivity.class);
                    intent.putExtra("name",name.getText());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    public ScientistAdapter(List<Scientistlayout> scientists)
    {
        this.scientists=scientists;
    }

    @NonNull
    @Override
    public ScientistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scientistnamelayout,parent,false);
        ScientistAdapter.ViewHolder holder = new ScientistAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScientistAdapter.ViewHolder holder, int position) {
        Scientistlayout sc=scientists.get(position);
        holder.name.setText(sc.name);
        holder.isalive.setText(sc.isalive);
        //TODO: holder.image
    }

    @Override
    public int getItemCount() {
        return scientists.size();
    }
}
