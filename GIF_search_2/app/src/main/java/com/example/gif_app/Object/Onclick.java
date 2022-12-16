package com.example.gif_app.Object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Onclick {

    @SerializedName("url")
    @Expose
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Onclick withUrl(String url) {
        this.url = url;
        return this;
    }

}