package com.drrabny.darrbyproject.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ahmad on 10-Apr-18.
 */

public class ApiClient {

    public static final String base_url ="http://darrapny.buyabler.com/api/";
    public static Retrofit retrofit =null;

    public static Retrofit getApiClient(){

        if (retrofit == null){
            retrofit =new Retrofit.Builder().baseUrl(base_url).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
