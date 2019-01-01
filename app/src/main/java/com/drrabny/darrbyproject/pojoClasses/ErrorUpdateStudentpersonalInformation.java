package com.drrabny.darrbyproject.pojoClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ahmad on 28-Apr-18.
 */

public class ErrorUpdateStudentpersonalInformation {


    @SerializedName("fname")
    @Expose
    private List<String> fname = null;
    @SerializedName("lname")
    @Expose
    private List<String> lname = null;
    @SerializedName("job_title")
    @Expose
    private List<String> jobTitle = null;
    @SerializedName("country")
    @Expose
    private List<String> country = null;
    @SerializedName("city")
    @Expose
    private List<String> city = null;
    @SerializedName("governorate")
    @Expose
    private List<String> governorate = null;
    @SerializedName("university")
    @Expose
    private List<String> university = null;
    @SerializedName("faculty")
    @Expose
    private List<String> faculty = null;
    @SerializedName("gender")
    @Expose
    private List<String> gender = null;
    @SerializedName("marital_status")
    @Expose
    private List<String> maritalStatus = null;
    @SerializedName("military_status")
    @Expose
    private List<String> militaryStatus = null;
    @SerializedName("phone")
    @Expose
    private List<String> phone = null;
    @SerializedName("cover_letter")
    @Expose
    private List<String> coverLetter = null;
    @SerializedName("date_of_birth")
    @Expose
    private List<String> dateOfBirth = null;

    public List<String> getFname() {
        return fname;
    }

    public void setFname(List<String> fname) {
        this.fname = fname;
    }

    public List<String> getLname() {
        return lname;
    }

    public void setLname(List<String> lname) {
        this.lname = lname;
    }

    public List<String> getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(List<String> jobTitle) {
        this.jobTitle = jobTitle;
    }

    public List<String> getCountry() {
        return country;
    }

    public void setCountry(List<String> country) {
        this.country = country;
    }

    public List<String> getCity() {
        return city;
    }

    public void setCity(List<String> city) {
        this.city = city;
    }

    public List<String> getGovernorate() {
        return governorate;
    }

    public void setGovernorate(List<String> governorate) {
        this.governorate = governorate;
    }

    public List<String> getUniversity() {
        return university;
    }

    public void setUniversity(List<String> university) {
        this.university = university;
    }

    public List<String> getFaculty() {
        return faculty;
    }

    public void setFaculty(List<String> faculty) {
        this.faculty = faculty;
    }

    public List<String> getGender() {
        return gender;
    }

    public void setGender(List<String> gender) {
        this.gender = gender;
    }

    public List<String> getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(List<String> maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public List<String> getMilitaryStatus() {
        return militaryStatus;
    }

    public void setMilitaryStatus(List<String> militaryStatus) {
        this.militaryStatus = militaryStatus;
    }

    public List<String> getPhone() {
        return phone;
    }

    public void setPhone(List<String> phone) {
        this.phone = phone;
    }

    public List<String> getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(List<String> coverLetter) {
        this.coverLetter = coverLetter;
    }

    public List<String> getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(List<String> dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}
