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
    private Button play;
    private Button pause;
    private MediaPlayer _currentPlayer;
    private boolean _isPlaying;
    private RecyclerView _recycleView;
    private ArrayList<Post> _posts = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _root = inflater.inflate(R.layout.activity_galery, container, false);
        _activity = (MainActivity)getActivity();

        _add =  _root.findViewById(R.id.fabAdd);
        _add.setOnClickListener(this);

        play = _root.findViewById(R.id.play_but);
        play.setOnClickListener(this);
        play.setVisibility(View.INVISIBLE);

        pause = _root.findViewById(R.id.pause_but);
        pause.setOnClickListener(this);
        pause.setVisibility(View.INVISIBLE);

        _fragmentView =_root.findViewById(R.id.fragment);
        _imageView = _fragmentView.findViewById(R.id.preview);

        _recycleView = _root.findViewById(R.id.RecycleView);
        generatePosts();
        PostAdapter adapter = new PostAdapter(getContext(), _posts);
        _recycleView.setAdapter(adapter);

        _fragmentView.setVisibility(View.GONE);
        _default = _imageView.getDrawingCache(false);
        BitmapDrawable drawable = (BitmapDrawable) _imageView.getDrawable();
        _default = drawable.getBitmap();
        _addImage = (AddPost)getChildFragmentManager().getFragments().get(0);

        return _root;

    }
    private void generatePosts()
    {
        Post ricardoPost = new Post(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.ricardo),"Подарок на 8 марта","На 8 марта один из пацанов 824401 решил подарить девушкам очень необыкновеннй танец.");
        _posts.add(new Post(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.hitler),"Новый ученик в группе 420401","Ряды группы 824401 пополнились новым студентов. Он увлекается живописью и любит детей."));
        _posts.add(ricardoPost);

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _currentPlayer.stop();
    }

    public void addImage(Bitmap bitmap, ArrayList<Uri> musics)
    {

        ImageView imageView = new ImageView(getContext());
        imageView.setImageBitmap(bitmap);
        _musics.put(imageView,new MusicInfo(musics));
        imageView.setOnClickListener(this);

       // LinearLayout relativeLayout = (LinearLayout) _root.findViewById(R.id.imageLayout);
      //  relativeLayout.addView(imageView,0);
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
        System.out.println("llllllllllllllllllllllll");
        if(visible)
        {
            _fragmentView.setVisibility(View.GONE);
        }
        else
        {
            _fragmentView.setVisibility(View.VISIBLE);
            _addImage.reset();
        }
        visible = !visible;
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        if(v.getClass() == ImageView.class)
        {
            MusicInfo musicInfo = _musics.get((ImageView)v);
            if(musicInfo!=null && musicInfo.musics.size()!=0)
            {
                playNext(musicInfo);

            }
        }

        switch (v.getId()) {
            case R.id.fabAdd:
                changeVisibility();
                break;
            case   R.id.pause_but:
                pause();
                break;
            case   R.id.play_but:
                resume();
                break;
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
                handleButtonsVisibility();
            }
        }
    }
    private void handleButtonsVisibility()
    {
        if(_isPlaying)
        {
            play.setVisibility(View.INVISIBLE);
            pause.setVisibility(View.VISIBLE);
        }
        else
        {
            play.setVisibility(View.VISIBLE);
            pause.setVisibility(View.INVISIBLE);
        }
    }
    private void pause()
    {
        if(_currentPlayer!= null) {

            if(_isPlaying)
            {
                _currentPlayer.pause();
                _isPlaying = false;
                handleButtonsVisibility();
            }
        }
    }
    public void playNext(MusicInfo musicInfo) {
        if(musicInfo.musics.size() == 0)
            return;
        if(_currentPlayer != null)
        {
            _currentPlayer.stop();
            _currentPlayer.reset();
        }
        musicInfo.current++;
        if(musicInfo.current>=musicInfo.musics.size())
        {
            musicInfo.current = 0;
        }
        if (ContextCompat.checkSelfPermission(
                getContext(), Manifest.permission.INTERNET) ==
                PackageManager.PERMISSION_GRANTED) {
            _currentPlayer = MediaPlayer.create(_activity.getApplicationContext(), musicInfo.musics.get(musicInfo.current));
            _currentPlayer.start();
            _isPlaying = true;
            handleButtonsVisibility();
        }

    }

}