
package com.drrabny.darrbyproject.pojoClasses;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrainingPost {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("companyId")
    @Expose
    private Integer companyId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("coverLetter")
    @Expose
    private String coverLetter;
    @SerializedName("examState")
    @Expose
    private Integer examState;
    @SerializedName("governorate")
    @Expose
    private String governorate;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("paidFor")
    @Expose
    private Object paidFor;
    @SerializedName("paidTo")
    @Expose
    private Object paidTo;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("start")
    @Expose
    private String start;
    @SerializedName("end")
    @Expose
    private String end;
    @SerializedName("salary")
    @Expose
    private Integer salary;
    @SerializedName("coachName")
    @Expose
    private String coachName;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;
    @SerializedName("ExamId")
    @Expose
    private Integer examId;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("score")
    @Expose
    private Double score;
    @SerializedName("companies")
    @Expose
    private Companies companies;

    public String getTime_ago() {
        return time_ago;
    }

    @SerializedName("time_ago")
    @Expose
private String time_ago;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

    public Integer getExamState() {
        return examState;
    }

    public void setExamState(Integer examState) {
        this.examState = examState;
    }

    public String getGovernorate() {
        return governorate;
    }

    public void setGovernorate(String governorate) {
        this.governorate = governorate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Object getPaidFor() {
        return paidFor;
    }

    public void setPaidFor(Object paidFor) {
        this.paidFor = paidFor;
    }

    public Object getPaidTo() {
        return paidTo;
    }

    public void setPaidTo(Object paidTo) {
        this.paidTo = paidTo;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Companies getCompanies() {
        return companies;
    }

    public void setCompanies(Companies companies) {
        this.companies = companies;
    }

}
