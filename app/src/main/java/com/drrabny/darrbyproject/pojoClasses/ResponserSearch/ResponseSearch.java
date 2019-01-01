
package com.drrabny.darrbyproject.pojoClasses.ResponserSearch;

import com.google.gson.annotations.SerializedName;

public class ResponseSearch {

    @SerializedName("Result")
    private Result mResult;

    public Result getResult() {
        return mResult;
    }

    public void setResult(Result result) {
        mResult = result;
    }

}
