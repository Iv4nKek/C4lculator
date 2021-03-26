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

    PostAdapter(Context context, List<Post> posts) {
        this.posts = posts;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public PostHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.post_layout, parent, false);
        return new PostHolder(view, null);
    }

    @Override
    public void onBindViewHolder(PostHolder holder, int position) {
        Post post = posts.get(position);
        holder.setup(post);
        //holder.imageView.setImageBitmap(post.get_image());
       // holder.headerView.setText(post.get_header());
      //  holder.discriptionView.setText(post.get_description());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


}