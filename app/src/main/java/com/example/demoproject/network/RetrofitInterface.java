package com.example.demoproject.network;

import com.example.demoproject.model.ProfilesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @GET("public-api/users")
    Call<ProfilesResponse> getProfiles(@Query("access-token") String accessToken,@Query("page") int page);
}
