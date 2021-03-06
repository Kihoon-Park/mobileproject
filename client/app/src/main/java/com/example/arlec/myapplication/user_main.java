package com.example.arlec.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.ExecutionException;

public class user_main extends AppCompatActivity {

    ListView listView;
    myAdapter adapter;
    Product[] products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        Button gotoList = (Button) findViewById(R.id.gotoList);

        gotoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        getApplicationContext(), User_canrent.class); // define next class
                startActivity(intent); // change next view
            }
        });

        GetData.DownloadTask downloadTask = new GetData.DownloadTask();
        try {
            String proudct_db = downloadTask.execute("http://ec2-54-91-46-126.compute-1.amazonaws.com:3000/product/isrent").get();
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            products = gson.fromJson(proudct_db, Product[].class);
            listView = (ListView) findViewById(R.id.ListView);
            adapter = new myAdapter();
            listView.setAdapter(adapter);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class myAdapter extends BaseAdapter{

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
        public View getView(int position, View convertView, ViewGroup parent) {
            ProductView view = new ProductView(getApplicationContext());
            view.setList_name_TextView(products[position].getName());
            view.setList_id_TextView(products[position].getID().toString());
            return view;
        }
    }
}
