package com.example.arlec.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.ExecutionException;


public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button login_button = (Button) findViewById(R.id.login_button);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetData.DownloadTask downloadTask = new GetData.DownloadTask();

                try {
                    String user_db = downloadTask.execute("http://ec2-54-91-46-126.compute-1.amazonaws.com:3000/user").get();
                    Log.d("user_db",user_db);
                    Gson gson = new GsonBuilder()
                                .setLenient()
                                .create();
                    User[] users = gson.fromJson(user_db, User[].class);

                    EditText u_id_text = (EditText) findViewById(R.id.u_id_text);
                    EditText u_pw_text = (EditText)findViewById(R.id.u_pw_text);
                    String id = u_id_text.getText().toString();
                    String pw = u_pw_text.getText().toString();
                    int check = 0;

                    for(int x = 0;x< users.length;x++) {
                        if (users[x].getName().equals(id) && users[x].getPassword().equals(pw)) {
                            check = 1;
                            break;
                        }
                    }
                    if(check == 1) {
                        Intent intent = new Intent(
                                getApplicationContext(), user_main.class); // define next class
                        startActivity(intent); // change next view
                    }
                    else {
                        Toast.makeText(getBaseContext(),
                                "ID와 비밀번호를 다시 확인해 주십시오.", Toast.LENGTH_SHORT)
                                .show();
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
