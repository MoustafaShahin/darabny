
package com.drrabny.darrbyproject.pojoClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Company {

    @SerializedName("Company")
    @Expose
    private Company_info company;
    @SerializedName("FollowStatus")
    @Expose
    private String followStatus;
    @SerializedName("NumberOfFollowers")
    @Expose
    private Integer numberOfFollowers;
    @SerializedName("Posts")
    @Expose
    private List<Post> posts = null;

    public Company_info getCompany() {
        return company;
    }

    public void setCompany(Company_info company) {
        this.company = company;
    }

    public String getFollowStatus() {
        return followStatus;
    }

    public void setFollowStatus(String followStatus) {
        this.followStatus = followStatus;
    }

    public Integer getNumberOfFollowers() {
        return numberOfFollowers;
    }

    public void setNumberOfFollowers(Integer numberOfFollowers) {
        this.numberOfFollowers = numberOfFollowers;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

}
