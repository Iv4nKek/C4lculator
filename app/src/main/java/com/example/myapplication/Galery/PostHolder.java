package com.example.myapplication.Galery;

import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class PostHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private TextView headerView, discriptionView;
    private Button postButton;
    private Button play;
    private Button stop;
    private Uri music;
    private Galery _galery;
    PostHolder(View view, Galery galery){
        super(view);
        _galery = galery;
        imageView = (ImageView)view.findViewById(R.id.post_image);
        headerView = (TextView) view.findViewById(R.id.post_name);
        discriptionView = (TextView) view.findViewById(R.id.post_description);
    }
}
