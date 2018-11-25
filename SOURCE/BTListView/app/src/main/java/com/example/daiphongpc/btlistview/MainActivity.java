package com.example.daiphongpc.btlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.daiphongpc.btlistview.Adapter.KhachHangAdapter;
import com.example.daiphongpc.btlistview.model.KhachHang;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<KhachHang> arrKH;
    ListView lv_kh;
    RadioButton rdbtn_male,rdbtn_female;
    Button btn_save;
    EditText edit_name,edit_age,edit_adress;
    KhachHangAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addList();
            }
        });
    }

    private void addList() {
        boolean sex;
        if (rdbtn_male.isChecked()){
            sex=true;
        }else {
            sex=false;

        }
        String name=edit_name.getText().toString();
        String age=edit_age.getText().toString();
        String adress=edit_adress.getText().toString();
        if (name.isEmpty() || age.isEmpty() || adress.isEmpty()){
            Toast.makeText(MainActivity.this,"Làm ơn nhập đày đủ thông tin!!",Toast.LENGTH_LONG).show();
        }else {

            arrKH.add(new KhachHang(name,Integer.parseInt(age),adress,sex));
            adapter.notifyDataSetChanged();
        }
    }

    private void addControls() {
        edit_adress=findViewById(R.id.edit_address);
        edit_age=findViewById(R.id.edit_age);
        edit_name=findViewById(R.id.edit_name);
        lv_kh=findViewById(R.id.lv_info);
        btn_save=findViewById(R.id.btn_save);
        rdbtn_female=findViewById(R.id.rdbt_female);
        rdbtn_male=findViewById(R.id.rdbtn_male);

        arrKH=new ArrayList<>();
        adapter=new KhachHangAdapter(arrKH,this,R.layout.item_lv);
        lv_kh.setAdapter(adapter);

    }
}
