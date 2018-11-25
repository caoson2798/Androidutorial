package com.example.daiphongpc.rss_tintuc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdpaterNews extends BaseAdapter {
    ArrayList<DocBao> docBaos;
    Context context;

    public AdpaterNews(ArrayList<DocBao> docBaos, Context context) {
        this.docBaos = docBaos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return docBaos.size();
    }

    @Override
    public Object getItem(int position) {
        return docBaos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    class ViewHolder{
        TextView txt_news;
        ImageView img_news;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView ==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater=LayoutInflater.from(context);
            convertView=inflater.inflate(R.layout.item_lv,null);
            viewHolder.txt_news=convertView.findViewById(R.id.txt_news);
            viewHolder.img_news=convertView.findViewById(R.id.img_news);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        DocBao docBao=docBaos.get(position);
        viewHolder.txt_news.setText(docBao.getTitle());
        Picasso.with(context).load(docBao.img).into(viewHolder.img_news);
        return convertView;
    }
}
