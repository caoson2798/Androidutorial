package com.example.daiphongpc.rss_tintuc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.DocumentsContract;
import android.sax.Element;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    ArrayList<DocBao> docBaos;
    ListView lv;
    AdpaterNews adpaterNews;

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // getData_RSS();
        addControl();
        addEvents();

    }

    private void addEvents() {
       lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent=new Intent(MainActivity.this,Main2Activity.class);
               intent.putExtra("link",docBaos.get(position).link);
               startActivity(intent);
           }
       });
    }

    private void addControl() {
        progressDialog=new ProgressDialog(MainActivity.this);
        lv=findViewById(R.id.lv_news);
        docBaos=new ArrayList<>();
        ReadStream readStream=new ReadStream();
        readStream.execute("https://vnexpress.net/rss/the-thao.rss");
        adpaterNews=new AdpaterNews(docBaos,MainActivity.this);
        lv.setAdapter(adpaterNews);
    }

    public void getData_RSS(){
        RequestQueue requestQueue=Volley.newRequestQueue(MainActivity.this);
        String url="https://vnexpress.net/rss/the-thao.rss";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("ktrp", "onResponse: "+response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }
    class ReadStream extends AsyncTask<String,Void,String>{

        public String Read_Url(String link){
            String line="";
            StringBuilder content=new StringBuilder();
            try {
                URL url=new URL(link);
                HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                InputStream inputStream=connection.getInputStream();
                BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));

                while ((line=reader.readLine()) != null){
                    content.append(line+"\n");
                }

                inputStream.close();
                reader.close();

            }catch (Exception e){
                Log.d("LoiAsyn", "Error "+e.toString());
            }
            return content.toString();
        }
        @Override
        protected String doInBackground(String... strings) {

            return Read_Url(strings[0]);
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            Log.d("Asyn", "onPostExecute: "+s.toString());
            XMLDOMParser parser=new XMLDOMParser();
            Document document=parser.getDocument(s);

            NodeList nodeList=document.getElementsByTagName("item");
            NodeList nodeListDecripstion=document.getElementsByTagName("description");
            String hinhanh="";


            for (int i=0; i<nodeList.getLength();i++){
                org.w3c.dom.Element element= (org.w3c.dom.Element) nodeList.item(i);
                String title=parser.getValue(element,"title");
                String link=parser.getValue(element,"link");

                Log.d("link", link);
                String cdata= nodeListDecripstion.item(i+1).getTextContent();

                Pattern pattern=Pattern.compile("\\< *[img][^\\>]*[src] *= *[\\\"\\']{0,1}([^\\\"\\'\\ >]*)");
                //\< *[img][^\>]*[src] *= *[\"\']{0,1}([^\"\'\ >]*)
                Matcher matcher=pattern.matcher(cdata);
                if (matcher.find()){
                    hinhanh=matcher.group(1);
                    Log.d("hinhanh",hinhanh+"........"+i);
                    docBaos.add(new DocBao(title,hinhanh,link));
                }

            }
            adpaterNews.notifyDataSetChanged();

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
