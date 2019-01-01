package com.drrabny.darrbyproject.pojoClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Yasmeen on 6/14/2018.
 */

public class ErrorChangeStudentPassword {
    @SerializedName("current-password")
    @Expose
    private List<String> currentPassword = null;
    @SerializedName("new-password")
    @Expose
    private List<String> newPassword = null;

    public List<String> getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(List<String> currentPassword) {
        this.currentPassword = currentPassword;
    }

    public List<String> getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(List<String> newPassword) {
        this.newPassword = newPassword;
    }


}
