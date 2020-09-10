package com.example.liyuchen.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liyuchen.R;

import java.util.List;

public class Searchadapter extends RecyclerView.Adapter<Searchadapter.ViewHolder> {
    private List<newslayout> news;

    static class ViewHolder extends RecyclerView.ViewHolder {

        String newsID;

        TextView title;
        TextView author;
        TextView time;
        String content;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View view) {
            super(view);
            title=view.findViewById(R.id.newslayout_title);
            author=view.findViewById(R.id.newslayout_author);
            time=view.findViewById(R.id.newslayout_time);
            linearLayout=view.findViewById(R.id.newslayout_all);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {
                    History.setHistory(newsID);
                    Intent intent=new Intent(view.getContext(),newsActivity.class);
                    intent.putExtra("title",title.getText());
                    intent.putExtra("author",author.getText());
                    intent.putExtra("time",time.getText());
                    intent.putExtra("content",content);
                    title.setTextColor(R.color.black);
                    view.getContext().startActivity(intent);
                }
            });
        }
    }

    public Searchadapter(List<newslayout> news)
    {
        this.news=news;
    }

    public void addNews(newslayout news)
    {
        this.news.add(news);
    }
    public void delNews() {
        this.news.clear();
    }

    @NonNull
    @Override
    public Searchadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newslayout,parent,false);
        Searchadapter.ViewHolder holder = new Searchadapter.ViewHolder(view);
        return holder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull Searchadapter.ViewHolder holder, int position) {
        newslayout  newlayout=news.get(position);
        holder.newsID = newlayout.getNewsID();
        holder.title.setText(newlayout.getTitle());
        holder.author.setText(newlayout.getAuthor());
        holder.time.setText(newlayout.getTime());
        if(newlayout.isRead())
            holder.title.setTextColor(R.color.black);
        else
            holder.title.setTextColor(R.color.yellow);
        holder.content=newlayout.getContent();
    }

    @Override
    public int getItemCount() {
        return news.size();
    }
}
