package com.example.liyuchen.ui.epidemic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.TypeReference;
import com.example.liyuchen.R;

import java.io.InputStream;
import java.net.URL;
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

    public void setScientists(List<Scientistlayout> list) {
        scientists = list;
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
        //holder.name.setText(sc.name);
        //holder.isalive.setText(sc.isalive);
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    InputStream input = (InputStream) new URL(sc.avatarURL).getContent();
                    Drawable d = Drawable.createFromStream(input, sc.name);
                    int width = d.getIntrinsicWidth(), height = d.getIntrinsicHeight();
                    Bitmap.Config config = d.getOpacity() != PixelFormat.OPAQUE
                            ? Bitmap.Config.ARGB_8888
                            : Bitmap.Config.RGB_565;
                    Bitmap bitmap = Bitmap.createBitmap(width, height, config);
                    Canvas canvas = new Canvas(bitmap);
                    d.setBounds(0, 0, width, height);
                    d.draw(canvas);
                    Bitmap newbmp = Bitmap.createScaledBitmap(bitmap, 450, 600, true);
                    holder.image.setImageBitmap(newbmp);

                } catch (Exception e) {
                    String err = e.toString();
                }
            }
        };
        thread.start();

        SpannableString name = new SpannableString(sc.name);
        name.setSpan(new TypefaceSpan("default-bold"), 0, sc.name.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.name.setText(name);

        SpannableString isalive = new SpannableString(sc.isalive);
        isalive.setSpan(new ForegroundColorSpan(Color.RED), 0, isalive.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.isalive.setText(isalive);

        //TODO: holder.image
    }

    @Override
    public int getItemCount() {
        return scientists.size();
    }
}
