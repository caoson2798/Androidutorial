package com.example.daiphongpc.tintucmoi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.daiphongpc.tintucmoi.Adpater.AdapterRCVHome;
import com.example.daiphongpc.tintucmoi.Model.Item_News;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    ViewFlipper vf;
    RecyclerView rcv;
    Animation in,on;
    ArrayList<String> linkImg=new ArrayList<>();
    AdapterRCVHome adapterRCVHome;
    ArrayList<Item_News> itemNews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        getLinkImg();


    }

    public void getData_RCV(){
        RequestQueue queue=Volley.newRequestQueue(MainActivity.this);


    }
    public void getLinkImg(){
        RequestQueue queue=Volley.newRequestQueue(MainActivity.this);
        String url="https://vnexpress.net/rss/tin-moi-nhat.rss";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                processImg(response);
               //


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);

    }

    private void processImg(String xml) {
        XMLDOMParser parser=new XMLDOMParser();
        Document document=parser.getDocument(xml);
        NodeList nodeListDescrip=document.getElementsByTagName("description");
        for (int i=1;i<5;i++){
            String cdata=nodeListDescrip.item(i).getTextContent();
            Pattern pattern=Pattern.compile("\\< *[img][^\\>]*[src] *= *[\\\"\\']{0,1}([^\\\"\\'\\ >]*)");
            Matcher matcher=pattern.matcher(cdata);
            while (matcher.find()){
                linkImg.add(String.valueOf(matcher.group(1)));
               // Log.d("ktmang",linkImg.size()+"");
            }
        }
        Log.d("TAGG", linkImg.size()+"");
        for (int i=0; i<linkImg.size();i++){
            ImageView img=new ImageView(MainActivity.this);
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Picasso.with(MainActivity.this).load(linkImg.get(i)).into(img);
            vf.addView(img);
        }
        vf.setFlipInterval(3000);
        vf.setAutoStart(true);
//        in=AnimationUtils.loadAnimation(MainActivity.this,R.anim.anim_in);
//        on=AnimationUtils.loadAnimation(MainActivity.this,R.anim.anim_out);
//        vf.setInAnimation(in);
//        vf.setOutAnimation(on);

    }

    private void addControl() {
        vf=findViewById(R.id.vf);
        rcv=findViewById(R.id.rcv_news);

        itemNews=new ArrayList();
        itemNews.add(new Item_News("Thế giới","https://vnexpress.net/rss/the-gioi.rss"));
        itemNews.add(new Item_News("Kinh Doanh","https://vnexpress.net/rss/kinh-doanh.rss"));
        itemNews.add(new Item_News("Khởi nghiệp","https://vnexpress.net/rss/startup.rss"));
        itemNews.add(new Item_News("Giải trí","https://vnexpress.net/rss/giai-tri.rss"));
        itemNews.add(new Item_News("Thể Thao","https://vnexpress.net/rss/the-thao.rss"));
        itemNews.add(new Item_News("Pháp luật","https://vnexpress.net/rss/phap-luat.rss"));
        itemNews.add(new Item_News("Giáo dục","https://vnexpress.net/rss/giao-duc.rss"));
        itemNews.add(new Item_News("Cười","https://vnexpress.net/rss/cuoi.rss"));


        rcv.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(MainActivity.this,2);
        rcv.setLayoutManager(layoutManager);
        adapterRCVHome=new AdapterRCVHome(MainActivity.this,itemNews,R.layout.item_rcv_news);
        adapterRCVHome.setOnClickItem(new AdapterRCVHome.LissnerClickEvent() {
            @Override
            public void onItemClick(int position) {
                Item_News itemNew=itemNews.get(position);
                Intent intent=new Intent(MainActivity.this,ListActivity.class);
                intent.putExtra("item",itemNews.get(position));
              //  Log.d("ktinten", itemNews.get(position).getLink_news());
                startActivity(intent);
            }
        });
        rcv.setAdapter(adapterRCVHome);

    }
}
