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
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.Util;

import java.io.FileNotFoundException;
import java.io.InputStream;


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
    private Uri _music;
    private Bitmap _default;
    private ImageView _preview;
    private LinearLayout _musicLayout;
    private EditText _header;
    private EditText _description;
   private TextView _musicView;

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
        _root.findViewById(R.id.selectImage).setOnClickListener(this);
        _root.findViewById(R.id.createPost).setOnClickListener(this);
        _root.findViewById(R.id.addMusic).setOnClickListener(this);
        _root.findViewById(R.id.Back).setOnClickListener(this);
        _header = _root.findViewById(R.id.header);
        _description = _root.findViewById(R.id.description);
        _preview = (ImageView) _root.findViewById(R.id.preview);
        _musicLayout = _root.findViewById(R.id.musicLayout2);
        _musicView = _root.findViewById(R.id.musicField);
        _activity = (MainActivity)getActivity();
       // _musics = new ArrayList<>();

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
            case R.id.createPost:
                commit();
                break;
            case R.id.Back:
                _activity.OpenGalery();
        }
    }
    private void commit()
    {
        String header = _header.getText().toString();
        String description = _description.getText().toString();
        if(_currentImage != null && description.length() != 0 && header.length() != 0)
        {
            hideKeyboard(_activity);
            _activity.OpenGalery(new Post(_currentImage,header,description,new MusicInfo(_music),true));
            Toast.makeText( getContext(), "Post created", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText( getContext(), "not full data", Toast.LENGTH_SHORT).show();
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
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_MUSIC_FOR_AVATAR && resultCode == Activity.RESULT_OK)
        {
            Uri uriSound=data.getData();
            _music = uriSound;
            String name= Util.getFileName(uriSound,_activity);
            _musicView.setText(name);
            Toast.makeText( getContext(), name, Toast.LENGTH_SHORT).show();
        }
        if (requestCode == PICK_PHOTO_FOR_AVATAR && resultCode == Activity.RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContext().getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                _currentImage = selectedImage;
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