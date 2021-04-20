package com.example.myapplication.Galery;

import android.graphics.Bitmap;

public class Post {
    private boolean _collapsed = true;
    private Bitmap _image;
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

    public Post(Bitmap _image, String _header, String _description, MusicInfo musicInfo, boolean collapsed) {
        this._collapsed = collapsed;
        this._image = _image;
        this._header = _header;
        this._description = _description;
        _musicInfo = musicInfo;
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
}
