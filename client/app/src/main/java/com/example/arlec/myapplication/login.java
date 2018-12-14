package com.example.arlec.myapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

class User {

    @SerializedName("u_id")
    @Expose
    private String u_id;
    @SerializedName("u_pw")
    @Expose
    private String u_pw;
    @SerializedName("u_name")
    @Expose
    private String u_name;
    @SerializedName("u_phone")
    @Expose
    private String u_phone;
    @SerializedName("u_email")
    @Expose
    private String u_email;
    @SerializedName("u_type")
    @Expose
    private Integer u_type;

    //name
    public String getName() {
        return u_id;
    }
    public void setName(String name) {
        this.u_id = name;
    }

    public String getPassword() {
        return u_pw;
    }
    public void setPassword(String password) {
        this.u_pw = u_pw;
    }


}

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login_button = (Button) findViewById(R.id.login_button);
        //
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkAvailable()) {
                    //TextView url = (TextView) findViewById(R.id.textView);
                    DownloadTask downloadTask = new DownloadTask();
                    //downloadTask.execute(url.getText().toString());
                    downloadTask.execute("http://ec2-54-91-46-126.compute-1.amazonaws.com:3000/user");

                } else {
                    Toast.makeText(getBaseContext(),
                            "Network is not Available", Toast.LENGTH_SHORT)
                            .show();
                }
            }

        });


    }


    private boolean isNetworkAvailable() {
        boolean available = false;
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable())
            available = true;

        return available;
    }

    private String downloadUrl(String strUrl) throws IOException {
        try {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
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

    private class DownloadTask extends AsyncTask<String, Integer, String> {
        String s = null;

        @Override
        protected String doInBackground(String... url) {

            try {
                s = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return s;
        }

        @Override
        protected void onPostExecute(String result) {
            TextView tView = (TextView) findViewById(R.id.textView);
            tView.setText(result);
            Toast.makeText(getBaseContext(),
                    "Web page downloaded successfully", Toast.LENGTH_SHORT)
                    .show();

            Log.i("RESPONSE", result);
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            //Student [] studes = gson.fromJson("[{\"name\": \"kim\", \"email\": \"kim@gmail.com\", \"phone\": \"010-1234-0001\", \"age\": 10}]", Student[].class);
            User [] users = gson.fromJson(result, User[].class);
            for(User s: users) {
                Log.i("U_ID", s.getName());
                Log.i("U_PASSWORD", s.getPassword());
            }


        }
    }
}


