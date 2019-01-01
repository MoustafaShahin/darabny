package com.drrabny.darrbyproject.pojoClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Yasmeen on 6/14/2018.
 */

public class StudentRegisterResponse {
    //success
    @SerializedName("token")
    @Expose
    private String token;



    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    //fail
    @SerializedName("name")
    @Expose
    private List<String> name = null;
    @SerializedName("lname")
    @Expose
    private List<String> lname = null;
    @SerializedName("gender")
    @Expose
    private List<String> gender = null;
    @SerializedName("email")
    @Expose
    private List<String> email = null;
    @SerializedName("date_of_birth")
    @Expose
    private List<String> dateOfBirth = null;
    @SerializedName("username")
    @Expose
    private List<String> username = null;
    @SerializedName("password")
    @Expose
    private List<String> password = null;
    @SerializedName("type")
    @Expose
    private List<String> type = null;
    @SerializedName("profile_photo")
    @Expose
    private List<String> profilePhoto = null;

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public List<String> getLname() {
        return lname;
    }

    public void setLname(List<String> lname) {
        this.lname = lname;
    }

    public List<String> getGender() {
        return gender;
    }

    public void setGender(List<String> gender) {
        this.gender = gender;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public List<String> getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(List<String> dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<String> getUsername() {
        return username;
    }

    public void setUsername(List<String> username) {
        this.username = username;
    }

    public List<String> getPassword() {
        return password;
    }

    public void setPassword(List<String> password) {
        this.password = password;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public List<String> getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(List<String> profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
}
