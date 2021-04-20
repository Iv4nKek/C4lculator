package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class WebViewActivity extends AppCompatActivity {

    WebView webView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        if( savedInstanceState == null ) {
            String url =
                    getIntent().getDataString().replace("myscheme://", "http://");
            // do something with this URL.
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl("https://vk.com/zabroshkiborika");
        }
    }
}