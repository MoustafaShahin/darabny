
package com.drrabny.darrbyproject.pojoClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

;

public class Details {

    @SerializedName("AccountInfo")
    @Expose
    private AccountInfo accountInfo;
    @SerializedName("UserRate")
    @Expose
    private Integer userRate;
    @SerializedName("Skills")
    @Expose
    private List<Skill> skills = null;
    @SerializedName("Experiences")
    @Expose
    private List<Experience> experiences = null;
    @SerializedName("languages")
    @Expose
    private List<Language> languages = null;
    @SerializedName("projects")
    @Expose
    private List<Project> projects = null;
    @SerializedName("Following")
    @Expose
    private List<Following> following = null;

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    public Integer getUserRate() {
        return userRate;
    }

    public void setUserRate(Integer userRate) {
        this.userRate = userRate;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Following> getFollowing() {
        return following;
    }

    public void setFollowing(List<Following> following) {
        this.following = following;
    }

}
