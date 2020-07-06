package com.example.demoproject.network;

import com.example.demoproject.contants.Constants;

import retrofit2.Retrofit;

public class ApiUtil {

    public static RetrofitInterface getProfilesResponse(){

        return RetrofitClient.getClient(Constants.GOREST_PAGE_API).create(RetrofitInterface.class);
    }

}
