package com.example.demoproject.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.demoproject.listeners.ApiCallInterface;
import com.example.demoproject.listeners.ApiFailureListener;
import com.example.demoproject.model.ProfilesResponse;

import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<ProfilesResponse> getProfilesResponse;
    private Context context;
    private HomeModel homeModel;
    private static final String TAG = "HomeViewModel";
    public ApiFailureListener apiFailureListener;

    public HomeViewModel() {
        homeModel = new HomeModel();
    }

    public MutableLiveData<ProfilesResponse> profilesResponse(int page){
        getProfilesResponse = new MutableLiveData<>();
        homeModel.getResponse(page,new ApiCallInterface() {
            @Override
            public void getProfilesResponse(Response<ProfilesResponse> ProfilesResponseCallBack) {
                Log.i(TAG,"getProfilesResponse:"+ ProfilesResponseCallBack.body().toString());
                getProfilesResponse.postValue(ProfilesResponseCallBack.body());
            }

            @Override
            public void apiFailureResponse(String message) {
                apiFailureListener.apiFailureResponse(message);
            }
        });

        return getProfilesResponse;
    }
}
