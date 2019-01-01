package com.drrabny.darrbyproject.pojoClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ahmad on 12-Apr-18.
 */

public class UserLogin {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("Response")
    @Expose
    private Response response;

    public String getStatus() {
        return status;
    }

    public Response getResponse() {
        return response;
    }
}
