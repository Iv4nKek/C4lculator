package com.example.myapplication.Galery;

import android.net.Uri;

import java.util.ArrayList;

public class MusicInfo {
    public ArrayList<Uri> musics;
    public int current;

    @Override
    public String toString() {
        return "MusicInfo{" +
                "musics=" + musics +
                ", current=" + current +
                '}';
    }

    public MusicInfo(ArrayList<Uri> musics, Uri music) {
        this.musics = musics;
    }

    public MusicInfo(ArrayList<Uri> musics) {
        this.musics = musics;
    }
}