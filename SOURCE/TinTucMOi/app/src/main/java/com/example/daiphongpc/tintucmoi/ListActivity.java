package com.example.daiphongpc.tintucmoi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.daiphongpc.tintucmoi.Adpater.Adapter_lv;
import com.example.daiphongpc.tintucmoi.Model.Item_News;
import com.example.daiphongpc.tintucmoi.Model.ListNews;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListActivity extends AppCompatActivity {
    Item_News itemNew;
    Intent intent;
    ProgressDialog dialog;
    //String title=intent.getStringExtra("item");

    TextView txt_title;
    ListView lv_list;
    ArrayList<ListNews> listNews;
    Adapter_lv adapter_lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        addControl();



    }

    private void addControl() {
        dialog=new ProgressDialog(ListActivity.this);
        txt_title=findViewById(R.id.txt_title);
        lv_list=findViewById(R.id.lv_news);
        intent =getIntent();
        itemNew= (Item_News) intent.getSerializableExtra("item");
        txt_title.setText(itemNew.getTitle());
        listNews=new ArrayList<>();
        getDataList();
        adapter_lv=new Adapter_lv(ListActivity.this,R.layout.item_lv_news,listNews);
        lv_list.setAdapter(adapter_lv);
        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(ListActivity.this,DetailActivity.class);
                intent.putExtra("link",listNews.get(position).getLink());
                startActivity(intent);
            }
        });
    }

    void getDataList(){
        RequestQueue requestQueue=Volley.newRequestQueue(ListActivity.this);
        final String url=itemNew.getLink_news();
        Log.d("TAGG", url);
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response==""){
                    dialog.show();
                }else {
                    dialog.dismiss();
                    XMLDOMParser parser=new XMLDOMParser();
                    Document document=parser.getDocument(response);
                    NodeList nodeList=document.getElementsByTagName("item");
                    NodeList nodeListDecripstion=document.getElementsByTagName("description");

                    for (int i=0; i<nodeList.getLength();i++){
                        Element element= (Element) nodeList.item(i);
                        String hinhanh="";
                        String title=parser.getValue(element,"title");
                        String link=parser.getValue(element,"link");
                        String cdata= nodeListDecripstion.item(i+1).getTextContent();

                        Pattern pattern=Pattern.compile("\\< *[img][^\\>]*[src] *= *[\\\"\\']{0,1}([^\\\"\\'\\ >]*)");
                        Matcher matcher=pattern.matcher(cdata);
                        if (matcher.find()){
                            hinhanh=matcher.group(1);
                            Log.d("link",link+"");
                            listNews.add(new ListNews(hinhanh,title,link));
                        }
                    }
                    adapter_lv.notifyDataSetChanged();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ListActivity.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(stringRequest);
    }
}
