package com.drrabny.darrbyproject.pojoClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class StudentRegister {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("Response")
    @Expose
    private StudentRegisterResponse response;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public StudentRegisterResponse getResponse() {
        return response;
    }

    public void setResponse(StudentRegisterResponse response) {
        this.response = response;
    }
}
