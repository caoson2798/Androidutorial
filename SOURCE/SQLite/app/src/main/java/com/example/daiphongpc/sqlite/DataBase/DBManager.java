package com.example.daiphongpc.sqlite.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.daiphongpc.sqlite.Model.Notes;

public class DBManager extends SQLiteOpenHelper {

    private int version=1;
    Context context;

    public DBManager(@Nullable Context context,@Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context=context;


    }
    //truy vấn không trả về kết quả: CREATE, INSERT, DELETE, UPDATE
    public void queryData(String sql){
        SQLiteDatabase database=this.getWritableDatabase();
        database.execSQL(sql);
    }
    //truy vấn có trả kết quả: SELECT
    public Cursor getData(String sql){
        SQLiteDatabase database=this.getReadableDatabase();
        return database.rawQuery(sql,null);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    //insert cách 2
    public void insert_Data(Notes notes,String tableName){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("NhacNho",notes.getNhacNho());
        long kq=database.insert(tableName,null,values);
        if (kq>0){
            Toast.makeText(context,"Insert thành công",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(context,"Insert không thành công",Toast.LENGTH_LONG).show();
        }
        database.close();
    }
    //del
    public void del_Data(String tableName,Notes notes){
        SQLiteDatabase database=this.getWritableDatabase();

        long kq=database.delete(tableName,"id=?",new String[]{String.valueOf(notes.getId())});
        if (kq>0){
            Toast.makeText(context,"xóa thành công",Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(context,"xảy ra lỗi",Toast.LENGTH_LONG).show();
        }
        database.close();
    }
    //up
    public void up_data(String tableName,Notes notes){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("NhacNho",notes.getNhacNho());
        long kq=database.update(tableName,values,"id=?",new String[]{String.valueOf(notes.getId())});
        if (kq>0){
            Toast.makeText(context,"sửa thành công",Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(context,"xảy ra lỗi",Toast.LENGTH_LONG).show();
        }
    }
}
