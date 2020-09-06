package com.example.liyuchen.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liyuchen.R;

import java.util.List;

public class newsadapter extends RecyclerView.Adapter<newsadapter.ViewHolder> {

    private List<newslayout> news;

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView author;
        TextView time;

        public ViewHolder(@NonNull View view) {
            super(view);
            title=view.findViewById(R.id.newslayout_title);
            author=view.findViewById(R.id.newslayout_author);
            time=view.findViewById(R.id.newslayout_time);
        }
    }

    public newsadapter(List<newslayout> news)
    {
        this.news=news;
    }

    public void addnews(newslayout news)
    {
        this.news.add(news);
    }

    @NonNull
    @Override
    public newsadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newslayout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull newsadapter.ViewHolder holder, int position) {
        newslayout  newlayout=news.get(position);
        holder.title.setText(newlayout.getTitle());
        holder.author.setText(newlayout.getAuthor());
        holder.time.setText(newlayout.getTime());
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

}
