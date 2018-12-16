package com.example.arlec.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import okhttp3.MultipartBody;
import okio.BufferedSink;

public class User_canrent extends AppCompatActivity {
    ListView listView;
    myAdapter2 adapter;
    Product[] products;
    String proudct_db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_canrent);

        GetData.DownloadTask downloadTask = new GetData.DownloadTask();
        try {
            String proudct_db = downloadTask.execute("http://ec2-54-91-46-126.compute-1.amazonaws.com:3000/product/isnotrent").get();
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            products = gson.fromJson(proudct_db, Product[].class);
            //Log.d("프로덕트디비",proudct_db);
            listView = (ListView) findViewById(R.id.ListView);
            adapter = new myAdapter2();
            listView.setAdapter(adapter);




        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class myAdapter2 extends BaseAdapter {

        @Override
        public int getCount() {
            return products.length;
        }

        @Override
        public Object getItem(int position) {
            return products[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ProductView view = new ProductView(getApplicationContext());
            view.setList_name_TextView(products[position].getName());
            view.setList_id_TextView(products[position].getID().toString());

            view.list_button.setOnClickListener(new View.OnClickListener() {
                @Override // 메뉴 버튼 클릭시 isrent * 1
                public void onClick(View v) {
                    Log.d("hhhhhhhhhhhhh", String.valueOf(products[position].getID()));

                    DownloadTask dt = new DownloadTask();
                    dt.execute("http://ec2-54-91-46-126.compute-1.amazonaws.com:3000/product/rent");
                    Intent intent = new Intent(getApplicationContext(),user_main.class);
                    startActivity(intent);
                }
            });

            return view;



        }
    }

    public static boolean isNetworkAvailable() {
        return true;
    }

    static class DownloadTask extends AsyncTask<String, Integer, String> {
        String s = null;
        private MultipartBody rbody;

        @Override
        protected String doInBackground(String... url) {

            try {
                s = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return s;
        }

        private String downloadUrl(String strUrl) throws IOException {
            try {
                Log.d("HHHHHHHHHHHHHHHHHHHHHHHWwwwwdfd",strUrl);
                OkHttpClient client = new OkHttpClient();
                com.squareup.okhttp.RequestBody bdy = new RequestBody() {
                    @Override
                    public MediaType contentType() {
                        return null;
                    }

                    @Override
                    public void writeTo(BufferedSink sink) throws IOException {

                    }
                };


                com.squareup.okhttp.Request request = new com.squareup.okhttp.Request.Builder()
                        .url(strUrl)
                        .build();

                Response response = client.newCall(request).execute();
                return response.body().string();

            } catch (Exception e) {
                Log.d("Exception download", e.toString());
            } finally {

            }
            return "{}";
        }

    }
}
