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
        /*
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

                System.out.println(s.getName());

        }

        */
    }
}
