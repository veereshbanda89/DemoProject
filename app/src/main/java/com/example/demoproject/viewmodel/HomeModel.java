package com.example.demoproject.viewmodel;

import android.util.Log;

import com.example.demoproject.contants.Constants;
import com.example.demoproject.listeners.ApiCallInterface;
import com.example.demoproject.model.ProfilesResponse;
import com.example.demoproject.network.ApiUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeModel {

    private static final String TAG = "HomeModel";

    public void getResponse(int page,final ApiCallInterface apiCallInterface){
        ApiUtil.getProfilesResponse().getProfiles(Constants.ACCESS_TOKEN,page).enqueue(new Callback<ProfilesResponse>() {
            @Override
            public void onResponse(Call<ProfilesResponse> call, Response<ProfilesResponse> response) {
                Log.i(TAG, "onResponse: " + response.isSuccessful());
                if(response.isSuccessful()){
                    apiCallInterface.getProfilesResponse(response);
                }
            }

            @Override
            public void onFailure(Call<ProfilesResponse> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getMessage());
                apiCallInterface.apiFailureResponse(t.getMessage());
            }
        });
    }
}
