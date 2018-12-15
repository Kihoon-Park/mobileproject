package com.example.arlec.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.ExecutionException;

public class user_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        GetData.DownloadTask downloadTask = new GetData.DownloadTask();
        try {
            String proudct_db = downloadTask.execute("http://ec2-54-91-46-126.compute-1.amazonaws.com:3000/product").get();
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            Product[] products = gson.fromJson(proudct_db, Product[].class);
            //Log.d("프로덕트디비",proudct_db);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
