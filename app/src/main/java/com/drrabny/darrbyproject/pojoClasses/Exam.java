
package com.drrabny.darrbyproject.pojoClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Exam {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("ExameQuestions")
    @Expose
    private ExameQuestions exameQuestions;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ExameQuestions getExameQuestions() {
        return exameQuestions;
    }

    public void setExameQuestions(ExameQuestions exameQuestions) {
        this.exameQuestions = exameQuestions;
    }

}
