
package com.drrabny.darrbyproject.pojoClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Feed {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("trainingPosts")
    @Expose
    private List<TrainingPost> trainingPosts = null;
    @SerializedName("$recommendedTrainingPosts")
    @Expose
    private List<$recommendedTrainingPost> $recommendedTrainingPosts = null;
    @SerializedName("$recmondedcomp")
    @Expose
    private List<$recmondedcomp> $recmondedcomp = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<TrainingPost> getTrainingPosts() {
        return trainingPosts;
    }

    public void setTrainingPosts(List<TrainingPost> trainingPosts) {
        this.trainingPosts = trainingPosts;
    }

    public List<$recommendedTrainingPost> get$recommendedTrainingPosts() {
        return $recommendedTrainingPosts;
    }

    public void set$recommendedTrainingPosts(List<$recommendedTrainingPost> $recommendedTrainingPosts) {
        this.$recommendedTrainingPosts = $recommendedTrainingPosts;
    }

    public List<$recmondedcomp> get$recmondedcomp() {
        return $recmondedcomp;
    }

    public void set$recmondedcomp(List<$recmondedcomp> $recmondedcomp) {
        this.$recmondedcomp = $recmondedcomp;
    }
}
