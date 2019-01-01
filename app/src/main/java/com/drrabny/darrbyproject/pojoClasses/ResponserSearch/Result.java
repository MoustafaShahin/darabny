
package com.drrabny.darrbyproject.pojoClasses.ResponserSearch;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("Company")
    private List<Company> mCompany;
    @SerializedName("Faculty")
    private List<Object> mFaculty;
    @SerializedName("Student")
    private List<Student> mStudent;
    @SerializedName("TraningPosts")
    private List<TraningPost> mTraningPosts;

    public List<Company> getCompany() {
        return mCompany;
    }

    public void setCompany(List<Company> company) {
        mCompany = company;
    }

    public List<Object> getFaculty() {
        return mFaculty;
    }

    public void setFaculty(List<Object> faculty) {
        mFaculty = faculty;
    }

    public List<Student> getStudent() {
        return mStudent;
    }

    public void setStudent(List<Student> student) {
        mStudent = student;
    }

    public List<TraningPost> getTraningPosts() {
        return mTraningPosts;
    }

    public void setTraningPosts(List<TraningPost> traningPosts) {
        mTraningPosts = traningPosts;
    }

}
