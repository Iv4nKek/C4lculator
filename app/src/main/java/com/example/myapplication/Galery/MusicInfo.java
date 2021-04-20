package com.example.myapplication.Galery;

import android.net.Uri;

import java.util.ArrayList;

public class MusicInfo {
    public ArrayList<Uri> musics;
    public Uri music;
    public int current;

    @Override
    public String toString() {
        return "MusicInfo{" +
                "musics=" + music +
                ", current=" + current +
                '}';
    }

    public MusicInfo(Uri musics, Uri music) {
        this.music = musics;
    }

    public MusicInfo(Uri musics) {
        this.music = musics;
    }
}