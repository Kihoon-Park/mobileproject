package com.example.arlec.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class GetData {

    public static boolean isNetworkAvailable() {
        return true;
    }

    static class DownloadTask extends AsyncTask<String, Integer, String> {
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

    }
}
