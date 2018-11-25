package com.example.daiphongpc.sqlite.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daiphongpc.sqlite.Model.Notes;
import com.example.daiphongpc.sqlite.R;

import java.util.ArrayList;

public class Adapter_lv extends ArrayAdapter<Notes> {
    Context context;
    int resource;
    ArrayList<Notes> objects;
    ImageView img_edt,img_del;
    TextView txt_id,txt_loiNhacNho;
    public mListenerOnBack del,edit;
    public Adapter_lv(@NonNull Context context, int resource, @NonNull ArrayList<Notes> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(context);
        convertView=inflater.inflate(resource,parent,false);
        img_edt=convertView.findViewById(R.id.img_edit);
        img_del=convertView.findViewById(R.id.img_del);
        txt_id=convertView.findViewById(R.id.txt_id);
        txt_loiNhacNho=convertView.findViewById(R.id.txt_loiNhacNho_list);

        Notes notes=objects.get(position);
        txt_id.setText(String.valueOf(notes.getId()));
        txt_loiNhacNho.setText(notes.getNhacNho());
        img_edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.onClick(position);
            }
        });
        img_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                del.onClick(position);
            }
        });
        return convertView;
    }
    public void setOnClickImgEdit(mListenerOnBack edit){
        this.edit=edit;
    }
    public void setOnClickIMG_Del(mListenerOnBack del){
        this.del=del;
    }
    public interface mListenerOnBack{
        void onClick(int position);
    }
}
