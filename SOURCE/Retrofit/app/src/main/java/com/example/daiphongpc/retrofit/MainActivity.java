package com.example.daiphongpc.retrofit;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txt_creat_acc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        txt_creat_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,DangKyActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        txt_creat_acc=findViewById(R.id.txt_create_acc);
        txt_creat_acc.setPaintFlags(txt_creat_acc.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }
}
