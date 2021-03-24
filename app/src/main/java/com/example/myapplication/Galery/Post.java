package com.example.myapplication.Galery;

import android.graphics.Bitmap;
import android.net.Uri;

public class Post {
    private Bitmap _image;
    private String _header;
    private String _description;

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

    public Post(Bitmap _image, String _header, String _description) {
        this._image = _image;
        this._header = _header;
        this._description = _description;
    }
}
