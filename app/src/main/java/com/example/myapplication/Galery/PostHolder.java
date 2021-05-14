package com.example.myapplication.Galery;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.Util;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class PostHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ImageView imageView;
    private TextView headerView, discriptionView,musicName;
    private Button postButton;
    private Button play;
    private Button stop;
    private ImageView imageView2;
    private TextView headerView2, discriptionView2,musicName2;
    private Button postButton2;
    private Button play2;
    private Button stop2;
    private Uri music;
    private Galery _galery;
    private LayoutInflater inflater;
    View view;
    ViewGroup parent;
    private boolean _collapsed = true;
    PostAdapter adapter;
    ConstraintLayout expanded;
    ConstraintLayout collapsed;
    Post post;

    PostHolder(View view, Galery galery, LayoutInflater inflater,  ViewGroup parent, PostAdapter adapter){
        super(view);

        this.adapter = adapter;
        this.view = view;
        _galery = galery;
        this.inflater = inflater;
        this.parent = parent;
        view.findViewById(R.id.main).setOnClickListener(this);
        view.findViewById(R.id.musicLayout2).setOnClickListener(this);
        imageView = (ImageView)view.findViewById(R.id.post_image);
        headerView = (TextView) view.findViewById(R.id.post_name);
        discriptionView = (TextView) view.findViewById(R.id.post_description);
        musicName = (TextView) view.findViewById(R.id.post_sound);

        imageView2 = (ImageView)view.findViewById(R.id.post_image2);
        headerView2 = (TextView) view.findViewById(R.id.post_name2);
        discriptionView2 = (TextView) view.findViewById(R.id.post_description2);
        musicName2 = (TextView) view.findViewById(R.id.post_sound2);

        expanded = (ConstraintLayout)view.findViewById(R.id.extended);
        collapsed = (ConstraintLayout)view.findViewById(R.id.collapsed);

        discriptionView2.setMovementMethod(new ScrollingMovementMethod());
        discriptionView2.setMovementMethod(LinkMovementMethod.getInstance());
        discriptionView2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //  Disallow the touch request for parent scroll on touch of child view
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                } else if (event.getAction() == MotionEvent.ACTION_UP ||event.getAction() == MotionEvent.ACTION_CANCEL) {
                    // Re-allows parent events
                    v.getParent().requestDisallowInterceptTouchEvent(false);
                }
                return false;
            }


        });
        Collapse();
    }
    private void Extend()
    {
        System.out.println("3");
        expanded.setVisibility(View.VISIBLE);
        collapsed.setVisibility(View.GONE);

    }
    private void Collapse()
    {
        System.out.println("4");
        expanded.setVisibility(View.GONE);
        collapsed.setVisibility(View.VISIBLE);
    }
    public void setup(Post post)
    {
        this.post = post;
        InputStream imageStream = null;
        try {
            imageStream = _galery.getContext().getContentResolver().openInputStream(post.get_imageUri());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
        imageView.setImageBitmap(selectedImage);
        headerView.setText(post.get_header());
        discriptionView.setText(post.get_description());
        imageView2.setImageBitmap(selectedImage);
        headerView2.setText(post.get_header());
        discriptionView2.setText(post.get_description());
        if(post.get_musicInfo() != null)
        {
            Uri musicUri = post.get_musicInfo().music;
            if(musicUri != null)
            {
                String music = Util.getFileName(musicUri, (Activity) itemView.getContext());
                musicName.setText(music);
                musicName2.setText(music);
            }
            else
            {
                musicName.setText("");
                musicName2.setText("");
            }
        }
        else {
            musicName.setText("");
            musicName2.setText("");
        }


    }
    private void handleMusic()
    {
        _galery.playNext(post.get_musicInfo());
    }
    @Override
    public void onClick(View v) {
        if(v.getClass() == ConstraintLayout.class)
        {
           if(_collapsed)
           {
               Extend();
           }
           else {
               Collapse();
           }
           _collapsed = !_collapsed;
        }
        switch (v.getId()) {
            case R.id.musicLayout2:
                handleMusic();
        }
    }
}
