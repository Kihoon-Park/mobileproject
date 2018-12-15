package com.example.arlec.myapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

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

    public boolean login_access(String id,String pw){
        if(u_id == "gh9411" && u_pw == "qwe123")
            return true;
        else
            return false;
    }

}
