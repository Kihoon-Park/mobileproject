package com.example.arlec.mop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText u_idInput, u_pwInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText u_idInput  = (EditText) findViewById(R.id.editText);
        EditText u_pwInput = (EditText) findViewById(R.id.editText2);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener((v) -> {
            Intent intent = new Intent(getApplicationContext(), s1.class);
            startActivityForResult(intent,201);
        });

    }

}
