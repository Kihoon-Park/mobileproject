package com.example.arlec.myapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Product {


    @SerializedName("p_id")
    @Expose
    private String p_id;
    @SerializedName("p_type")
    @Expose
    private Integer p_type;
    @SerializedName("p_name")
    @Expose
    private String u_name;
    @SerializedName("p_lender")
    @Expose
    private String p_lender;
    @SerializedName("p_user")
    @Expose
    private String p_user;
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
    public String getName() {
        return p_id;
    }
    public void setName(String name) {
        this.p_id = name;
    }


}
