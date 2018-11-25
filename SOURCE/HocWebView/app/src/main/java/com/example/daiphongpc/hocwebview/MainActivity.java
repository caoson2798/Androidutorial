package com.example.daiphongpc.hocwebview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView imgBack,imgNext,imgReload;
    EditText editURL;
    Button btn_Search;
    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();

        addEvent();
    }

    private void addEvent() {
        wv.loadUrl("https://www.google.com/");
        btn_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=editURL.getText().toString().trim();
                wv.loadUrl("http://"+url);
                editURL.setText(wv.getUrl());
            }
        });
        wv.setWebViewClient(new WebViewClient());
        imgReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wv.reload();
            }
        });
        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wv.canGoForward()){
                    wv.goForward();
                    editURL.setText(wv.getUrl());
                }else {
                    Toast.makeText(MainActivity.this,"Không có trang tiếp theo",Toast.LENGTH_LONG).show();
                }
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wv.canGoBack()){
                    wv.goBack();
                    editURL.setText(wv.getUrl());
                }else {
                    Toast.makeText(MainActivity.this,"Không có trang sau",Toast.LENGTH_LONG).show();
                }
            }
        });
        WebSettings webSettings=wv.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAllowContentAccess(true);

        wv.addJavascriptInterface(new WebInterface(this), "Android");

    }

    private void addControl() {
        editURL=findViewById(R.id.edit_url);
        imgBack=findViewById(R.id.img_back);
        imgNext=findViewById(R.id.img_next);
        imgReload=findViewById(R.id.img_reload);
        btn_Search=findViewById(R.id.btn_seaech);
        wv=findViewById(R.id.wv);
    }
    public class WebInterface{
        Context mContext;

        WebInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public void playSound(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public void pauseSound(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }
    }
}
