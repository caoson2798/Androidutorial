package com.example.daiphongpc.retrofit;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daiphongpc.retrofit.Retrofit.APIUtils;
import com.example.daiphongpc.retrofit.Retrofit.DataClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView txt_creat_acc;
    EditText editUser,editPass;
    Button btnLogin;


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
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taikhoa=editUser.getText().toString();
                String matkhau=editPass.getText().toString();
                if (taikhoa.length()>0 && matkhau.length()>0){
                    DataClient dataClient=APIUtils.getData();
                    Call<List<SinhVien>> listCall=dataClient.loginData(taikhoa,matkhau);
                    listCall.enqueue(new Callback<List<SinhVien>>() {
                        @Override
                        public void onResponse(Call<List<SinhVien>> call, Response<List<SinhVien>> response) {
                            ArrayList<SinhVien> sinhViens= (ArrayList<SinhVien>) response.body();
                            if (sinhViens.size()>0){
                                Intent intent=new Intent(MainActivity.this,THongTinSinhVienActivity.class);
                                intent.putExtra("taikhoan",sinhViens.get(0).getTaikhoan());
                                intent.putExtra("matkhau",sinhViens.get(0).getMatkhau());
                                intent.putExtra("hinhanh",sinhViens.get(0).getHinhanh());
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<List<SinhVien>> call, Throwable t) {
                            Toast.makeText(MainActivity.this,"error login: "+t.toString(),Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
        });
    }

    private void addControls() {
        btnLogin=findViewById(R.id.btn_login);
        editPass=findViewById(R.id.edit_pas);
        editUser=findViewById(R.id.edit_user);
        txt_creat_acc=findViewById(R.id.txt_create_acc);
        txt_creat_acc.setPaintFlags(txt_creat_acc.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }
}
