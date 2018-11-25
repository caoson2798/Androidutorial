package com.example.daiphongpc.tintucmoi.Adpater;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daiphongpc.tintucmoi.Model.Item_News;
import com.example.daiphongpc.tintucmoi.R;

import java.util.ArrayList;

public class AdapterRCVHome extends RecyclerView.Adapter<AdapterRCVHome.ViewHoder> {
    Context context;
    ArrayList<Item_News> itemNews;
    int layout;

   public LissnerClickEvent onclick;
    public AdapterRCVHome(Context context, ArrayList<Item_News> itemNews, int layout) {
        this.context = context;
        this.itemNews = itemNews;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(layout,viewGroup,false);
        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder viewHoder, int i) {
        Item_News item=itemNews.get(i);
        viewHoder.txt_title.setText(item.getTitle());
       // Picasso.with(context).load(item.getImg()).into(viewHoder.img_news_home);

    }

    @Override
    public int getItemCount() {
        return itemNews.size();
    }


    public class ViewHoder extends RecyclerView.ViewHolder {
        TextView txt_title;
        ImageView img_news_home;

        public ViewHoder(@NonNull final View itemView) {
            super(itemView);

            txt_title = itemView.findViewById(R.id.txt_title_home);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onclick.onItemClick(getPosition());
                }
            });


        }


    }
    public void setOnClickItem(LissnerClickEvent event) {
        this.onclick=event;
    }
    public interface LissnerClickEvent{
        void onItemClick(int position);
    }
}
