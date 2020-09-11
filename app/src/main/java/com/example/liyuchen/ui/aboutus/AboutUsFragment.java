package com.example.liyuchen.ui.aboutus;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.liyuchen.R;

public class AboutUsFragment extends Fragment {

    private ImageView image;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.aboutusfragment, container, false);
        image=root.findViewById(R.id.imageView2);
        Glide.with(root.getContext()).load(R.drawable.aboutus).into(image);
        return root;
    }
}
