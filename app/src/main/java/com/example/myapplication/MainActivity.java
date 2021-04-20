package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.Calculator.Calculator;
import com.example.myapplication.Galery.AddPost;
import com.example.myapplication.Galery.Galery;
import com.example.myapplication.Galery.Post;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements Runnable {


    private AppBarConfiguration mAppBarConfiguration;
    public Fragment _galery;
    private Post _post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);


    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public void OpenGalery(Post post)
    {

        _post = post;
        if(_galery == null)
        {
            _galery = new Galery();
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, _galery);
        transaction.commit();
        transaction.runOnCommit(this);

    }
    public void OpenPostAdding()
    {
        Fragment newFragment = new AddPost();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, newFragment);
        transaction.commit();
    }
    public void OpenGalery()
    {
        if(_galery == null)
        {
            _galery = new Galery();
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, _galery);
        transaction.commit();
        transaction.runOnCommit(this);

    }
    public void OpenSignUp()
    {
        Fragment newFragment = new SignUp();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, newFragment);
        transaction.commit();
    }
    public void OpenAuth()
    {
        Fragment newFragment = new Auth();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, newFragment);
        transaction.commit();
    }
    public void OpenCalculator()
    {
        Fragment newFragment = new Calculator();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, newFragment);
        transaction.commit();
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
       // _textView = findViewById(R.id.textView);
    }


    public void rickAstley()
    {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=dQw4w9WgXcQ&ab_channel=RickAstleyVEVO"));
        startActivity(browserIntent);
    }


    @Override
    public void run() {
        ((Galery)_galery).AddPost(_post);
    }
}