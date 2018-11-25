package com.example.daiphongpc.tintucmoi;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DetailActivity extends AppCompatActivity {
    SwipeRefreshLayout swip;
    WebView wv;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        addControls();

        swip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadWeb();
            }
        });
        loadWeb();
    }

    private void loadWeb() {
        intent=getIntent();
        String link=intent.getStringExtra("link");
        wv.loadUrl(link);
        swip.setRefreshing(true);
        wv.setWebViewClient(new WebViewClient());
        WebSettings webSettings=wv.getSettings();
        webSettings.setBuiltInZoomControls(true);


        webSettings.setJavaScriptEnabled(true);

    }

    private void addControls() {
        swip=findViewById(R.id.swip);

        wv=findViewById(R.id.wv);

    }
}
