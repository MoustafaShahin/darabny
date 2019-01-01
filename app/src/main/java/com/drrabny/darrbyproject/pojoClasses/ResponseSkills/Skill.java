
package com.drrabny.darrbyproject.pojoClasses.ResponseSkills;

import com.google.gson.annotations.SerializedName;


public class Skill {

    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("id")
    private int mId;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("updated_at")
    private Object mUpdatedAt;

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Object getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        mUpdatedAt = updatedAt;
    }

}
