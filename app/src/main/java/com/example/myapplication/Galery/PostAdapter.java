package com.example.myapplication.Galery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class PostAdapter  extends RecyclerView.Adapter<PostHolder>{

    private final LayoutInflater inflater;
    private final List<Post> posts;
    Galery galery;

    PostAdapter(Context context, List<Post> posts, Galery galery) {
        this.galery = galery;
        this.posts = posts;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public PostHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        System.out.println("onCreateViewHolder");
        View view = inflater.inflate(R.layout.post_layout, parent, false);

       // View view2 = inflater.inflate(R.layout.post_layout, parent, false);
        return new PostHolder(view, galery, inflater, parent,this);
    }

    @Override
    public void onBindViewHolder(PostHolder holder, int position) {
        System.out.println("onBindViewHolder");
        Post post = posts.get(position);
        holder.setup(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


}