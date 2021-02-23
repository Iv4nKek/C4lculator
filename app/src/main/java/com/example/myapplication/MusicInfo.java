package com.example.myapplication;

import android.net.Uri;

import java.util.ArrayList;

public class MusicInfo {
    public ArrayList<Uri> musics;
    public Uri music;
    public int current;

    @Override
    public String toString() {
        return "MusicInfo{" +
                "musics=" + musics +
                ", music=" + music +
                ", current=" + current +
                '}';
    }

    public MusicInfo(ArrayList<Uri> musics, Uri music) {
        this.musics = musics;
        this.music = music;
    }

    public MusicInfo(ArrayList<Uri> musics) {
        this.musics = musics;
    }
}