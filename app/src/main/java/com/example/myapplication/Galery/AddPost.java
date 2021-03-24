package com.example.myapplication.Galery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;


public class AddPost extends Fragment implements View.OnClickListener {

    private static final int PICK_PHOTO_FOR_AVATAR = 0;
    private static final int PICK_MUSIC_FOR_AVATAR = 1;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View _root;
    private MainActivity _activity;
    private Bitmap _currentImage;
    private ArrayList<Uri> _musics;
    private Uri _music;
    private Bitmap _default;
    private ImageView _preview;

    public AddPost() {
        // Required empty public constructor
    }

    public void reset()
    {
        //((Button)_root.findViewById(R.id.commit)).setVisibility(View.GONE);
        _preview.setImageBitmap(_default);
        _currentImage = null;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        _root = inflater.inflate(R.layout.fragment_add_post, container, false);
       // _root.findViewById(R.id.addImage).setOnClickListener(this);
      //  _root.findViewById(R.id.commit).setOnClickListener(this);
        _root.findViewById(R.id.addMusic).setOnClickListener(this);
        _preview = (ImageView) _root.findViewById(R.id.preview);
        _activity = (MainActivity)getActivity();
        _musics = new ArrayList<>();

        BitmapDrawable drawable = (BitmapDrawable) _preview.getDrawable();
        _default = drawable.getBitmap();
        return _root;
    }
    @Override
    public void onClick(View v) {
        System.out.println("onClick");
        switch (v.getId()) {
            case R.id.selectImage:
                getImage();
                break;
            case R.id.addMusic:
                getMusic();
                break;
        }
    }
    private void commit()
    {
            if(_currentImage != null)
            {
                Galery galery =  (Galery) getParentFragment();
                galery.addImage(_currentImage,_musics);
                _musics = new ArrayList<>();
            }
    }

    private void getMusic()
    {
        Intent intent = new Intent();
        intent.setType("audio/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Audio "), PICK_MUSIC_FOR_AVATAR);


    }
    public void getImage()
    {
        System.out.println("image");
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_PHOTO_FOR_AVATAR);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_MUSIC_FOR_AVATAR && resultCode == Activity.RESULT_OK)
        {
            Uri uriSound=data.getData();
            System.out.println(uriSound);
            System.out.println(uriSound.getPath());
            _musics.add(uriSound);
          //  play(this, uriSound);
        }
        if (requestCode == PICK_PHOTO_FOR_AVATAR && resultCode == Activity.RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContext().getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                _currentImage = selectedImage;
             //   ((Button)_root.findViewById(R.id.commit)).setVisibility(View.VISIBLE);
                ((ImageView)_root.findViewById(R.id.preview)).setImageBitmap(selectedImage);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                // Toast.makeText(PostImage.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

            Context context;
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(data.getData());
                Bitmap bmp = BitmapFactory.decodeStream(inputStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            //Now you can do whatever you want with your inpustream, save it as file, upload to a server, decode a bitmap...
        }
    }
}