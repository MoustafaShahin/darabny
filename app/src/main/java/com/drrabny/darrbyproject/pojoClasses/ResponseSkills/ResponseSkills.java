
package com.drrabny.darrbyproject.pojoClasses.ResponseSkills;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class ResponseSkills {

    @SerializedName("skills")
    private List<Skill> mSkills;
    @SerializedName("status")
    private String mStatus;

    public List<Skill> getSkills() {
        return mSkills;
    }

    public void setSkills(List<Skill> skills) {
        mSkills = skills;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

}
