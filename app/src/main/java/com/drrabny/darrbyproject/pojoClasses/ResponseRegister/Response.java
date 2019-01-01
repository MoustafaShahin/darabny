
package com.drrabny.darrbyproject.pojoClasses.ResponseRegister;

    import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("name")
    private String mName;
    @SerializedName("token")
    private String mToken;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        mToken = token;
    }

}
