
package com.drrabny.darrbyproject.pojoClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

;

public class Error {

    @SerializedName("title")
    @Expose
    private List<String> title = null;
    @SerializedName("description")
    @Expose
    private List<String> description = null;
    @SerializedName("start")
    @Expose
    private List<String> start = null;
    @SerializedName("end")
    @Expose
    private List<String> end = null;
    @SerializedName("ExperienceImg")
    @Expose
    private List<String> experienceImg = null;

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public List<String> getStart() {
        return start;
    }

    public void setStart(List<String> start) {
        this.start = start;
    }

    public List<String> getEnd() {
        return end;
    }

    public void setEnd(List<String> end) {
        this.end = end;
    }

    public List<String> getExperienceImg() {
        return experienceImg;
    }

    public void setExperienceImg(List<String> experienceImg) {
        this.experienceImg = experienceImg;
    }

}
