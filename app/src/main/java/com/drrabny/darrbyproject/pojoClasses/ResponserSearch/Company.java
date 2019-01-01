
package com.drrabny.darrbyproject.pojoClasses.ResponserSearch;

import com.google.gson.annotations.SerializedName;

public class Company {

    @SerializedName("CP ")
    private String mCP;
    @SerializedName("city")
    private String mCity;
    @SerializedName("CompanyName")
    private String mCompanyName;
    @SerializedName("country")
    private String mCountry;
    @SerializedName("id")
    private Long mId;
    @SerializedName("PP")
    private String mPP;

    public String getCP() {
        return mCP;
    }

    public void setCP(String cP) {
        mCP = cP;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getCompanyName() {
        return mCompanyName;
    }

    public void setCompanyName(String companyName) {
        mCompanyName = companyName;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getPP() {
        return mPP;
    }

    public void setPP(String pP) {
        mPP = pP;
    }

}
