package com.example.arlec.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProductView extends LinearLayout {
    TextView list_id_TextView;
    TextView list_name_TextView;
    Button list_button;
    public ProductView(Context context) {
        super(context);
        inflation_init(context);

        list_id_TextView = (TextView) findViewById(R.id.list_p_id);
        list_name_TextView = (TextView) findViewById(R.id.list_p_name);
        list_button = (Button) findViewById(R.id.list_button3);



    }

    private void inflation_init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item,this,true);
    }

    public void setList_id_TextView(String p_id){
        list_id_TextView.setText(p_id);
    }

    public void setList_name_TextView(String p_name){
        list_name_TextView.setText(p_name);
    }
}
