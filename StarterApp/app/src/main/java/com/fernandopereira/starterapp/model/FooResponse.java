package com.fernandopereira.starterapp.model;

import com.google.gson.annotations.SerializedName;

public class FooResponse {

    @SerializedName("fooId")
    private String mWhatever;

    public String getWhatever() {
        return mWhatever;
    }
}
