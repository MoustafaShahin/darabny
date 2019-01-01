
package com.drrabny.darrbyproject.pojoClasses;

import com.google.gson.annotations.SerializedName;

public class ResponseFollow {

    @SerializedName("status")
    private String mStatus;
    @SerializedName("success")
    private String mSuccess;

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getSuccess() {
        return mSuccess;
    }

    public void setSuccess(String success) {
        mSuccess = success;
    }

}
