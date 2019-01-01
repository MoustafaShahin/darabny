
package com.drrabny.darrbyproject.pojoClasses.ResponseRegister;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ResponseRegister {

    @SerializedName("Response")
    private Response mResponse;
    @SerializedName("status")
    private String mStatus;

    public Response getResponse() {
        return mResponse;
    }

    public void setResponse(Response response) {
        mResponse = response;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

}
