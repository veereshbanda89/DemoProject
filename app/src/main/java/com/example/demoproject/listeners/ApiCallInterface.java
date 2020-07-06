package com.example.demoproject.listeners;

import com.example.demoproject.model.ProfilesResponse;

import retrofit2.Response;

public interface ApiCallInterface {

    public void getProfilesResponse(Response<ProfilesResponse> ProfilesResponseCallBack);

    public void apiFailureResponse(String message);
}
