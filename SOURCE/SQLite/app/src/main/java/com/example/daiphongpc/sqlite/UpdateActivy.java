package com.example.daiphongpc.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivy extends AppCompatActivity {

    Intent intent;
    EditText editID,editNhacNho;
    Button btnUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_activy);
        addControls();
    }

    private void addControls() {
        intent=getIntent();
        int id=intent.getIntExtra("id",0);
        String nhacnho=intent.getStringExtra("nhacnho");
        editID=findViewById(R.id.edit_id);
        editNhacNho=findViewById(R.id.edit_NhacNho_up);
        btnUp=findViewById(R.id.btn_up);
        editID.setText(id+"");
        editNhacNho.setText(nhacnho);
        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("id_kq",Integer.parseInt(editID.getText().toString()));
                intent.putExtra("nhacnho_kq",editNhacNho.getText().toString());
                setResult(115,intent);
                finish();
            }
        });

    }
}
