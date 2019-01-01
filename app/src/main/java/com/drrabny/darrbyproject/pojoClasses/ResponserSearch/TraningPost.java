
package com.drrabny.darrbyproject.pojoClasses.ResponserSearch;

import com.google.gson.annotations.SerializedName;

public class TraningPost {

    @SerializedName("city")
    private String mCity;
    @SerializedName("coachName")
    private Long mCoachName;
    @SerializedName("companyId")
    private Long mCompanyId;
    @SerializedName("coverLetter")
    private String mCoverLetter;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("duration")
    private String mDuration;
    @SerializedName("end")
    private String mEnd;
    @SerializedName("examState")
    private String mExamState;
    @SerializedName("governorate")
    private String mGovernorate;
    @SerializedName("id")
    private Long mId;
    @SerializedName("img")
    private String mImg;
    @SerializedName("paidFor")
    private Object mPaidFor;
    @SerializedName("paidTo")
    private Object mPaidTo;
    @SerializedName("salary")
    private Long mSalary;
    @SerializedName("start")
    private String mStart;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("type")
    private Long mType;
    @SerializedName("updated_at")
    private String mUpdatedAt;

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public Long getCoachName() {
        return mCoachName;
    }

    public void setCoachName(Long coachName) {
        mCoachName = coachName;
    }

    public Long getCompanyId() {
        return mCompanyId;
    }

    public void setCompanyId(Long companyId) {
        mCompanyId = companyId;
    }

    public String getCoverLetter() {
        return mCoverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        mCoverLetter = coverLetter;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public String getDuration() {
        return mDuration;
    }

    public void setDuration(String duration) {
        mDuration = duration;
    }

    public String getEnd() {
        return mEnd;
    }

    public void setEnd(String end) {
        mEnd = end;
    }

    public String getExamState() {
        return mExamState;
    }

    public void setExamState(String examState) {
        mExamState = examState;
    }

    public String getGovernorate() {
        return mGovernorate;
    }

    public void setGovernorate(String governorate) {
        mGovernorate = governorate;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getImg() {
        return mImg;
    }

    public void setImg(String img) {
        mImg = img;
    }

    public Object getPaidFor() {
        return mPaidFor;
    }

    public void setPaidFor(Object paidFor) {
        mPaidFor = paidFor;
    }

    public Object getPaidTo() {
        return mPaidTo;
    }

    public void setPaidTo(Object paidTo) {
        mPaidTo = paidTo;
    }

    public Long getSalary() {
        return mSalary;
    }

    public void setSalary(Long salary) {
        mSalary = salary;
    }

    public String getStart() {
        return mStart;
    }

    public void setStart(String start) {
        mStart = start;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Long getType() {
        return mType;
    }

    public void setType(Long type) {
        mType = type;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

}
