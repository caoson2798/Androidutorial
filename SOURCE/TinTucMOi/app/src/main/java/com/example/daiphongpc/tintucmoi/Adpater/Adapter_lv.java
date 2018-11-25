package com.example.daiphongpc.tintucmoi.Adpater;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daiphongpc.tintucmoi.Model.ListNews;
import com.example.daiphongpc.tintucmoi.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapter_lv extends ArrayAdapter<ListNews> {
    Context context;
    int layout;
    ArrayList<ListNews> listNews;
    TextView txt_title_list;
    ImageView img_list;

    public Adapter_lv(@NonNull Context context, int resource,@NonNull ArrayList<ListNews> objects) {
        super(context, resource, objects);
        this.context=context;
        this.layout=resource;
        this.listNews=objects;
    }


    class ViewholerList{

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        try {
           // if (convertView==null){
              //  viewholerList=new ViewholerList();
                LayoutInflater inflater=LayoutInflater.from(context);
                convertView=inflater.inflate(layout,parent,false);
                img_list=convertView.findViewById(R.id.img_list);
                txt_title_list=convertView.findViewById(R.id.txt_title_list);
          //      convertView.setTag(convertView);
            //}else {
              //  viewholerList= (ViewholerList) convertView.getTag();
            //}
            ListNews listNews1=listNews.get(position);
            txt_title_list.setText(listNews1.getTitle());
            Picasso.with(context).load(listNews1.getImg()).into(img_list);
        }catch (Exception e){
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
        }
        return convertView;

    }
}
