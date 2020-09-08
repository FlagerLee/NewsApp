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

import com.example.liyuchen.MainActivity;
import com.example.liyuchen.R;

import java.util.List;

public class newsadapter extends RecyclerView.Adapter<newsadapter.ViewHolder> {

    private List<newslayout> news;
    private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder {

        String newsID;

        TextView title;
        TextView author;
        TextView time;
        String content;
        Context context;
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
                    Intent intent=new Intent(context,newsActivity.class);
                    intent.putExtra("title",title.getText());
                    intent.putExtra("author",author.getText());
                    intent.putExtra("time",time.getText());
                    intent.putExtra("content",content);
                    title.setTextColor(R.color.black);
                    context.startActivity(intent);
                }
            });
        }
    }

    public newsadapter(List<newslayout> news,Context context)
    {
        this.news=news;
        this.context=context;
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
    public newsadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newslayout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull newsadapter.ViewHolder holder, int position) {
        newslayout  newlayout=news.get(position);
        holder.title.setText(newlayout.getTitle());
        holder.author.setText(newlayout.getAuthor());
        holder.time.setText(newlayout.getTime());
        if(newlayout.isread())
            holder.title.setTextColor(R.color.black);
        else
            holder.title.setTextColor(R.color.yellow);
        holder.content=newlayout.getContent();
        holder.context=context;
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

}
