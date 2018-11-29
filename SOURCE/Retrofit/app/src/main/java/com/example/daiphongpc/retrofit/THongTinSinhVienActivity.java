package com.example.daiphongpc.retrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class THongTinSinhVienActivity extends AppCompatActivity {
    Intent intent;
    TextView txtUser;
    ImageView imgUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_sinh_vien);
        addControls();
    }

    private void addControls() {
        txtUser=findViewById(R.id.txt_user_login);
        imgUser=findViewById(R.id.img_user);
        intent=getIntent();
        String tk=intent.getStringExtra("taikhoan");
        String hinhanh=intent.getStringExtra("hinhanh");
        Log.d("hinhanh", hinhanh);
        txtUser.setText(tk);
        Picasso.get().load(hinhanh).into(imgUser);
    }
}
