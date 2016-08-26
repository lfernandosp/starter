package com.fernandopereira.starterapp.model;

import com.google.gson.annotations.SerializedName;

public class Foo {

    @SerializedName("id")
    private String mId;

    @SerializedName("title")
    private String mTitle;

    public String getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }
}
