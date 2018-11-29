package com.example.daiphongpc.btrcv_chat;

import android.os.Message;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.daiphongpc.btrcv_chat.Adapter.ChatAdapterRecycler;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    RecyclerView rcvChat;
    ArrayList<com.example.daiphongpc.btrcv_chat.Model.Messenger> arrData=new ArrayList<>();
    ChatAdapterRecycler adapterRecycler =new ChatAdapterRecycler(this,arrData);
    Button btnSend;
    EditText editContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvets();

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true);
        rcvChat.setLayoutManager(linearLayoutManager);
       // rcvChat.setOnScrollChangeListener();

        rcvChat.setAdapter(adapterRecycler);


    }

    private void addEvets() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepareData();
                adapterRecycler.notifyDataSetChanged();
            }
        });

    }

    private void prepareData() {
        String content=editContent.getText().toString();
        Random rd=new Random();
        int isgoing=rd.nextInt(2);
        Log.d("ktt", "prepareData: "+isgoing);
        arrData.add(0,new com.example.daiphongpc.btrcv_chat.Model.Messenger(content,isgoing,""));

        adapterRecycler.notifyDataSetChanged();
    }

    private void addControls() {
        rcvChat=findViewById(R.id.rcv_Chat);
        editContent=findViewById(R.id.edit_content);
        btnSend=findViewById(R.id.btn_send);

    }
}
