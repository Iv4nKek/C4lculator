package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.myapplication.Elements.ElementsContainer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Galery extends Fragment implements View.OnClickListener {


    private static final int PICK_PHOTO_FOR_AVATAR = 0;

    View _root;
    MainActivity _activity;
    private HashMap<ImageView,MusicInfo> _musics = new HashMap<ImageView, MusicInfo>();
    private MusicInfo _current;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _root = inflater.inflate(R.layout.activity_galery, container, false);
        _activity = (MainActivity)getActivity();
        _root.findViewById(R.id.add).setOnClickListener(this);
        return _root;

    }


    public void addImage(Bitmap bitmap, ArrayList<Uri> musics)
    {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageBitmap(bitmap);
        _musics.put(imageView,new MusicInfo(musics));
        imageView.setOnClickListener(this);
        LinearLayout relativeLayout = (LinearLayout) _root.findViewById(R.id.imageLayout);
        relativeLayout.addView(imageView,0);
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
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        MusicInfo musicInfo = _musics.get((ImageView)v);
        if(musicInfo!=null && musicInfo.musics.size()!=0)
        {
            System.out.println(musicInfo.musics.get(0));
            if (ContextCompat.checkSelfPermission(
                    getContext(), Manifest.permission.INTERNET) ==
                    PackageManager.PERMISSION_GRANTED)
            {
                System.out.println(musicInfo.musics.get(0));
               // Check(musicInfo.musics.get(0).toString());
                 MediaPlayer mediaPlayer = MediaPlayer.create(_activity.getApplicationContext(),musicInfo.musics.get(0));

                 mediaPlayer.start();
                // MediaPlayer mediaPlayer = MediaPlayer.create(getContext(),R.drawable.flex);
               /* MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioAttributes(
                        new AudioAttributes.Builder()
                                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                .setUsage(AudioAttributes.USAGE_MEDIA)
                                .build()
                );
                try {
                  //  mediaPlayer.setDataSource(_activity.getApplicationContext(), Uri.parse("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"));
                    mediaPlayer.setDataSource(_activity.getApplicationContext(),musicInfo.musics.get(musicInfo.current)) ;
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/

                mediaPlayer.start();
            }

           /* else {
            // You can directly ask for the permission.
            // The registered ActivityResultCallback gets the result of this request.
             requestPermissionLauncher.launch(Manifest.permission.REQUESTED_PERMISSION);*/
        }

        switch (v.getId()) {
            case R.id.add:
                break;
        }
    }
}