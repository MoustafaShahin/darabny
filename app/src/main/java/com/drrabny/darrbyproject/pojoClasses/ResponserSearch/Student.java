
package com.drrabny.darrbyproject.pojoClasses.ResponserSearch;

import com.google.gson.annotations.SerializedName;

public class Student {

    @SerializedName("address")
    private String mAddress;
    @SerializedName("CP ")
    private String mCP;
    @SerializedName("DOB")
    private String mDOB;
    @SerializedName("first_name")
    private String mFirstName;
    @SerializedName("gender")
    private String mGender;
    @SerializedName("id")
    private Long mId;
    @SerializedName("last_name")
    private String mLastName;
    @SerializedName("PP")
    private String mPP;
    @SerializedName("phone")
    private String mPhone;

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getCP() {
        return mCP;
    }

    public void setCP(String cP) {
        mCP = cP;
    }

    public String getDOB() {
        return mDOB;
    }

    public void setDOB(String dOB) {
        mDOB = dOB;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        mGender = gender;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getPP() {
        return mPP;
    }

    public void setPP(String pP) {
        mPP = pP;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

}
