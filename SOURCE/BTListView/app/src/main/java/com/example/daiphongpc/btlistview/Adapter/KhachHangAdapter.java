package com.example.daiphongpc.btlistview.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daiphongpc.btlistview.R;
import com.example.daiphongpc.btlistview.model.KhachHang;

import java.util.ArrayList;

public class KhachHangAdapter extends BaseAdapter {
    ArrayList<KhachHang> khachHangs;
    Context context;
    int layout;

    public KhachHangAdapter(ArrayList<KhachHang> khachHangs, Context context, int layout) {
        this.khachHangs = khachHangs;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return khachHangs.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodelr viewHodelr;
        if (convertView==null){
            viewHodelr=new ViewHodelr();
            LayoutInflater inflater=LayoutInflater.from(context);
            convertView=inflater.inflate(layout,parent,false);
            viewHodelr.img_sex=convertView.findViewById(R.id.img_sex);
            viewHodelr.txt_adress=convertView.findViewById(R.id.txt_adress_item);
            viewHodelr.txt_age=convertView.findViewById(R.id.txt_age_item);
            viewHodelr.txt_name=convertView.findViewById(R.id.txt_name_item);
            convertView.setTag(viewHodelr);
        }else {
            viewHodelr= (ViewHodelr) convertView.getTag();
        }
        KhachHang khachHang=khachHangs.get(position);
        viewHodelr.txt_name.setText("Tên: "+khachHang.getmName());
        viewHodelr.txt_age.setText("Tuổi: "+khachHang.getmAge()+"");
        viewHodelr.txt_adress.setText("Đc: "+khachHang.getmAdress());
        if (khachHang.ismSex()){
            viewHodelr.img_sex.setImageResource(R.drawable.male);
        }else {
            viewHodelr.img_sex.setImageResource(R.drawable.female);
        }
        return convertView;
    }
    class ViewHodelr{
        ImageView img_sex;
        TextView txt_name,txt_age,txt_adress;
    }
}
