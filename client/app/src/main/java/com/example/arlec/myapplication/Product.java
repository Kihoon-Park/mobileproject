package com.example.arlec.myapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Product {


    @SerializedName("p_id")
    @Expose
    private Integer p_id;
    @SerializedName("p_type")
    @Expose
    private Integer p_type;
    @SerializedName("p_name")
    @Expose
    private String p_name;
    @SerializedName("p_lender_id")
    @Expose
    private String p_lender_id;
    @SerializedName("p_borrower")
    @Expose
    private String p_borrower;
    @SerializedName("p_isrent")
    @Expose
    private Integer p_isrnet;
    @SerializedName("p_on_start")
    @Expose
    private Date p_on_start;
    @SerializedName("p_on_end")
    @Expose
    private Date p_on_end;

    //name
    public Integer getID() {
        return p_id;
    }

    public String getName(){
        return p_name;
    }


}
