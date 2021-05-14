package com.example.myapplication.Galery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;

public class Post {
    private boolean _collapsed = true;

    private Bitmap _image;
    private String _imagePath;
    private Uri _imageUri;

    private String _header;
    private String _description;
    private MusicInfo _musicInfo;

    public Bitmap get_image() {
        return _image;
    }

    public void set_image(Bitmap _image) {
        this._image = _image;
    }

    public String get_header() {
        return _header;
    }

    public void set_header(String _header) {
        this._header = _header;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

  /*  public Bitmap getImageBitmap(Context context)
    {
        InputStream imageStream = null;
        try {
            imageStream = context.getContentResolver().openInputStream(_image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
        return selectedImage;
    }
*/    public Post(Bitmap _image, String _header, String _description, MusicInfo musicInfo, boolean collapsed) {
        this._collapsed = collapsed;
        this._image = _image;
        this._header = _header;
        this._description = _description;
        _musicInfo = musicInfo;
    }
    public Post(Uri imageUri, String _header, String _description, MusicInfo musicInfo, boolean collapsed) {
        this._collapsed = collapsed;
        this._imageUri = imageUri;
        this._header = _header;
        this._description = _description;
        _musicInfo = musicInfo;
    }
    public Post(Bitmap _image, String _header, String _description, Uri musicUri) {
        this._image = _image;
        this._header = _header;
        this._description = _description;
        _musicInfo = new MusicInfo(musicUri);
    }
    public Post(String _imagePath, String _header, String _description, Uri musicUri) {
        this._imagePath = _imagePath;
        this._header = _header;
        this._description = _description;
        _musicInfo = new MusicInfo(musicUri);
    }
    public Post(Uri imageUri, String _header, String _description, Uri musicUri) {
        this._imageUri = imageUri;
        this._header = _header;
        this._description = _description;
        _musicInfo = new MusicInfo(musicUri);
    }
    public Post(String _imagePath,Uri imageUri, String _header, String _description, Uri musicUri) {
        this._imagePath = _imagePath;
        this._imageUri = imageUri;
        this._header = _header;
        this._description = _description;
        _musicInfo = new MusicInfo(musicUri);
    }

    public MusicInfo get_musicInfo() {
        return _musicInfo;
    }

    public void set_musicInfo(MusicInfo _musicInfo) {
        this._musicInfo = _musicInfo;
    }

    public boolean is_collapsed() {
        return _collapsed;
    }

    public void set_collapsed(boolean _collapsed) {
        this._collapsed = _collapsed;
    }

    public String get_imagePath() {
        return _imagePath;
    }

    public void set_imagePath(String _imagePath) {
        this._imagePath = _imagePath;
    }

    public Uri get_imageUri() {
        return _imageUri;
    }

    public void set_imageUri(Uri _imageUri) {
        this._imageUri = _imageUri;
    }
}
