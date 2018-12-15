package com.example.arlec.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class user_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        GetData.DownloadTask downloadTask = new GetData.DownloadTask();
        downloadTask.execute("http://ec2-54-91-46-126.compute-1.amazonaws.com:3000/product");
    }
}
