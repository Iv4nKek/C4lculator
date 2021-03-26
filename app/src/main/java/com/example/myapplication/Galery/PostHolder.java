package com.example.myapplication.Galery;

import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.Util;

public class PostHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private TextView headerView, discriptionView,musicName;
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
    public void setup(Post post)
    {
        imageView.setImageBitmap(post.get_image());
        headerView.setText(post.get_header());
        discriptionView.setText(post.get_header());
        Uri musicUri = post.get_musicInfo().musics.get(0);
        if(musicUri != null)
        {
            String music = Util.getFileName(musicUri, _galery.getActivity());
            musicName.setText(music);
        }

    }
}
