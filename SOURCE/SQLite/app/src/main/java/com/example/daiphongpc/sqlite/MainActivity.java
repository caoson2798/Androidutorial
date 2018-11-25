
package com.example.daiphongpc.sqlite;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.daiphongpc.sqlite.Adapter.Adapter_lv;
import com.example.daiphongpc.sqlite.DataBase.DBManager;
import com.example.daiphongpc.sqlite.Model.Notes;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public  static String namDatabse="Notes.sqlite";
    ArrayList<Notes> notesArrayList;
    Adapter_lv adapter_lv;
    ListView lv;
    EditText edit_LoiNhan;
    Button btn_add;
    public  static DBManager dbManager;
    String tableName="GhiChi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager=new DBManager(MainActivity.this,namDatabse,null,1);

        //tạo bảng
        dbManager.queryData("CREATE TABLE IF NOT EXISTS GhiChi(id INTEGER PRIMARY KEY AUTOINCREMENT, NhacNho NVARCHAR(200))");
        addControls();
        addEvents();
        //insert




    }

    private void addEvents() {
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loiNhan=edit_LoiNhan.getText().toString();
                if (loiNhan.isEmpty()){
                    Toast.makeText(MainActivity.this,"nhập lời nhắc nhở",Toast.LENGTH_LONG).show();
                }else {
                    Notes notes=new Notes();
                    notes.setNhacNho(loiNhan);
                    dbManager.insert_Data(notes,tableName);
                    getAllData();
                    edit_LoiNhan.setText("");
                }

            }
        });
      adapter_lv.setOnClickIMG_Del(new Adapter_lv.mListenerOnBack() {
          @Override
          public void onClick(final int position) {
              final AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
              builder.setTitle("Cảnh báo");
              builder.setMessage("Bạn có chắc muốn xóa không!");
              builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      dbManager.del_Data(tableName,notesArrayList.get(position));
                      getAllData();
                  }
              });
              builder.setNegativeButton("không", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      dialog.dismiss();
                  }
              });
            builder.show();
          }
      });
      adapter_lv.setOnClickImgEdit(new Adapter_lv.mListenerOnBack() {
          @Override
          public void onClick(int position) {
              Intent intent=new Intent(MainActivity.this,UpdateActivy.class);
              intent.putExtra("id",notesArrayList.get(position).getId());
              intent.putExtra("nhacnho",notesArrayList.get(position).getNhacNho());
              startActivityForResult(intent,113);
          }
      });
    }
    private void getAllData(){
        Cursor cursor=dbManager.getData("SELECT * FROM "+tableName);
        notesArrayList.clear();
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String loiNhan=cursor.getString(1);
            Notes notes=new Notes(id,loiNhan);
            notesArrayList.add(notes);
        }
       adapter_lv.notifyDataSetChanged();
        cursor.close();
    }
    private void addControls() {
        lv=findViewById(R.id.lv_Notes);
        edit_LoiNhan=findViewById(R.id.edit_nhacnho);
        btn_add=findViewById(R.id.btn_add);
        notesArrayList=new ArrayList<>();

        adapter_lv=new Adapter_lv(MainActivity.this,R.layout.item_lv,notesArrayList);
        getAllData();
        lv.setAdapter(adapter_lv);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==113 && resultCode==115){
            int id=data.getIntExtra("id_kq",0);
            String NhacNho=data.getStringExtra("nhacnho_kq");
            Notes notes=new Notes(id,NhacNho);
            dbManager.up_data(tableName,notes);
            getAllData();
        }
    }
}
