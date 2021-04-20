package com.example.myapplication.Galery;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Galery extends Fragment implements View.OnClickListener {


    private static final int PICK_PHOTO_FOR_AVATAR = 0;

    View _root;
    MainActivity _activity;
    private HashMap<ImageView,MusicInfo> _musics = new HashMap<ImageView, MusicInfo>();
    private MusicInfo _current;
    private Bitmap _default;
    private ImageView _imageView;
    private View _fragmentView;
    private AddPost _addImage;
    private FloatingActionButton _add;
    private MediaPlayer _currentPlayer;
    private boolean _isPlaying;
    private RecyclerView _recycleView;
    private ArrayList<Post> _posts = new ArrayList<>();
    private  PostAdapter _adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("Galery opned");
        _root = inflater.inflate(R.layout.activity_galery, container, false);
        _activity = (MainActivity)getActivity();
        _activity._galery = this;
        _add =  _root.findViewById(R.id.fabAdd);
        _add.setOnClickListener(this);


        _fragmentView =_root.findViewById(R.id.fragment);
        _imageView = _fragmentView.findViewById(R.id.preview);

        _recycleView = _root.findViewById(R.id.RecycleView);
        generatePosts();
        _adapter= new PostAdapter(getContext(), _posts,this);
        _recycleView.setAdapter(_adapter);

        _fragmentView.setVisibility(View.GONE);
        _default = _imageView.getDrawingCache(false);
        BitmapDrawable drawable = (BitmapDrawable) _imageView.getDrawable();
        _default = drawable.getBitmap();
        _addImage = (AddPost)getChildFragmentManager().getFragments().get(0);

        return _root;

    }
    private void generatePosts()
    {
        if(_posts.size() == 2)
            _posts.clear();
        Post ricardoPost = new Post(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.ricardo),"Подарок на 8 марта","На 8 марта один из пацанов 824401 решил подарить девушкам очень необыкновеннй танец. Такого не было целоый год.", _current,true);
        _posts.add(new Post(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.hitler),"Новый ученик в группе 420401","Ряды группы 824401 пополнились новым студентом. Он увлекается живописью и любит пму.", _current,false));
        _posts.add(ricardoPost);


    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(_currentPlayer != null)
        {
            _currentPlayer.stop();
        }

    }
    public void AddPost(Post post)
    {
        if(post == null)
            return;
        _posts.add(post);
        _adapter.notifyDataSetChanged();
    }

    private void Check(String uri)
    {
        ContentResolver cr = _activity.getContentResolver();
        String[] projection = {MediaStore.MediaColumns.DATA};
        Cursor cur = cr.query(Uri.parse(uri), projection, null, null, null);
        if (cur != null) {
            if (cur.moveToFirst()) {
                String filePath = cur.getString(0);

                if (new File(filePath).exists()) {
                    System.out.println("4");
                    // do something if it exists
                } else {
                    System.out.println("3");
                    // File was not found
                }
            } else {
                System.out.println("2");
                // Uri was ok but no entry found.
            }
            cur.close();
        } else {
            System.out.println("1");
            // content Uri was invalid or some other error occurred
        }
    }
    private boolean visible;
    private void changeVisibility()
    {
        _activity.OpenPostAdding();
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        if(v.getClass() == ImageView.class)
        {
           /* MusicInfo musicInfo = _musics.get((ImageView)v);
            if(musicInfo!=null && musicInfo.musics.size()!=0)
            {
                playNext(musicInfo);

            }*/
        }

        switch (v.getId()) {
            case R.id.fabAdd:
                changeVisibility();
        }
    }
    private void ChangeStopStatus()
    {
        if(_currentPlayer!= null)
        {
            if(_isPlaying)
            {
                _currentPlayer.pause();
            }
            else
            {
                _currentPlayer.start();
            }
            _isPlaying = !_isPlaying;
        }
    }
    private void resume()
    {
        if(_currentPlayer!= null) {

            if(!_isPlaying)
            {
                _currentPlayer.start();
                _isPlaying = true;
            }
        }
    }

    private void pause()
    {
        if(_currentPlayer!= null) {

            if(_isPlaying)
            {
                _currentPlayer.pause();
                _isPlaying = false;
            }
        }
    }
    public void changeMusicStatus(MusicInfo musicInfo)
    {
        Uri uri = musicInfo.music;
        if(uri == null)
            return;

    }
    public void playNext(MusicInfo musicInfo) {
        if(musicInfo == null || musicInfo.music == null)
            return;
        if(_current == musicInfo)
        {
            if(_isPlaying)
            {
                _currentPlayer.pause();
            }
            else
            {
                _currentPlayer.start();
            }
            _isPlaying = !_isPlaying;
        }
        else
        {
            if(_currentPlayer != null)
            {
                _currentPlayer.stop();
                _currentPlayer.reset();
            }
            if (ContextCompat.checkSelfPermission(
                    getContext(), Manifest.permission.INTERNET) ==
                    PackageManager.PERMISSION_GRANTED) {
                _currentPlayer = MediaPlayer.create(_activity.getApplicationContext(), musicInfo.music);
                _currentPlayer.start();
                _isPlaying = true;
                _current = musicInfo;
            }
        }


    }

}