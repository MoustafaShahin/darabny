
package com.drrabny.darrbyproject.pojoClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

;

public class Error1 {

    @SerializedName("language")
    @Expose
    private List<String> language = null;
    @SerializedName("level")
    @Expose
    private List<String> level = null;

    public List<String> getLanguage() {
        return language;
    }

    public void setLanguage(List<String> language) {
        this.language = language;
    }

    public List<String> getLevel() {
        return level;
    }

    public void setLevel(List<String> level) {
        this.level = level;
    }

}
