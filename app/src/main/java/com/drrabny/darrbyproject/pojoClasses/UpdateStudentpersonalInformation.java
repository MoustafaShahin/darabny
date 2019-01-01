package com.drrabny.darrbyproject.pojoClasses;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ahmad on 28-Apr-18.
 */

public class UpdateStudentpersonalInformation {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("Response")
    @Expose
    private JsonObject Response;

    public JsonObject getResponse() {
        return Response;
    }

    public void setResponse(JsonObject response) {
        Response = response;
    }

    @SerializedName("success")
    @Expose
    private String success;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
