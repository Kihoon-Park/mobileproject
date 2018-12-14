package kr.co.company.webdownload;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
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

class Product {

	@SerializedName("p_id")
	@Expose
	private String p_id;
	@SerializedName("p_name")
	@Expose
	private String p_name;
	@SerializedName("p_isb")
	@Expose
	private String p_isb;
	@SerializedName("on_u_id")
	@Expose
	private Integer on_u_id;
	@SerializedName("on_start")
	@Expose
	private Integer on_start;
	@SerializedName("on_end")
	@Expose
	private Integer on_end;

	public String getName() {
		return p_id;
	}

	public void setName(String name) {
		this.p_id = name;
	}

	public String getEmail() {
		return p_name;
	}

}

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button btnDownload = (Button) findViewById(R.id.download);
		OnClickListener downloadListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (isNetworkAvailable()) {
					EditText url = (EditText) findViewById(R.id.url);
					DownloadTask downloadTask = new DownloadTask();
					//downloadTask.execute(url.getText().toString());
					downloadTask.execute("http://10.0.2.2:9001/api/product");

				} else {
					Toast.makeText(getBaseContext(),
							"Network is not Available", Toast.LENGTH_SHORT)
							.show();
				}
			}

		};
		btnDownload.setOnClickListener(downloadListener);
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
			TextView tView = (TextView) findViewById(R.id.text);
			tView.setText(result);
			Toast.makeText(getBaseContext(),
					"Web page downloaded successfully", Toast.LENGTH_SHORT)
					.show();

			Log.i("RESPONSE", result);
			Gson gson = new GsonBuilder()
					.setLenient()
					.create();
			//Student [] students = gson.fromJson("[{\"name\": \"kim\", \"email\": \"kim@gmail.com\", \"phone\": \"010-1234-0001\", \"age\": 10}]", Student[].class);
			Product [] students = gson.fromJson(result, Product[].class);
			for(Product s: students) {
				Log.i("P_ID", s.getName());
				Log.i("P_NAME", s.getEmail());
			}


		}
	}
}