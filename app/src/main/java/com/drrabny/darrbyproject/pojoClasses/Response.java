package com.drrabny.darrbyproject.pojoClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ahmad on 22-Apr-18.
 */

public class Response {
    @SerializedName("token")
    @Expose
    private String token;

    public String getToken() {
        return token;
    }
}
