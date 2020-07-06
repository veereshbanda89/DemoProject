package com.example.demoproject.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.demoproject.R;
import com.example.demoproject.adapter.ProfileNamesAdapter;
import com.example.demoproject.contants.Util;
import com.example.demoproject.listeners.ApiFailureListener;
import com.example.demoproject.model.ProfilesResponse;
import com.example.demoproject.model.ResultItem;
import com.example.demoproject.roomdatabase.ResultDatabase;
import com.example.demoproject.roomdatabase.ResultRepository;
import com.example.demoproject.viewmodel.HomeViewModel;
import com.facebook.stetho.Stetho;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeActivity<RetrieveTask> extends AppCompatActivity implements ApiFailureListener {

    private HomeViewModel homeViewModel;
    private ProgressBar progressBar;
    private RecyclerView profileNamesRecyclerView;
    private ResultDatabase resultDatabase;
    private ResultRepository resultRepository;

    private boolean isLoading = true;
    private int pastVisibleItems=0,visibleItem_Count=0,totalItem_Count=0,previous_total=0;//Variables for Pagination
    private int viewThreshold = 10;
    int page_number =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_home);

        progressBar = findViewById(R.id.progressBar);
        profileNamesRecyclerView = findViewById(R.id.profileNamesRecyclerView);

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel.apiFailureListener = this;
        resultRepository = new ResultRepository(getApplication());

        if (!Util.isNetworkAvailable(getApplicationContext())) {
            page_number--;
            displayList();
        } else {
            callApi(page_number);
        }
    }

    private void displayList() {
        resultRepository.getResultItemList().observe(this, resultItems -> {
            if (resultItems!=null && resultItems.size()>0) {
                ArrayList<ResultItem> resultItemArrayList = new ArrayList<ResultItem>(resultItems);
                setProfileNames(resultItemArrayList);
            }else{
                if(page_number<1)
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.no_internet_connection), Toast.LENGTH_LONG).show();
            }
        });
    }


    private void callApi(int page_number) {
        progressBar.setVisibility(View.VISIBLE);
        homeViewModel.profilesResponse(page_number).observe(this, new Observer<ProfilesResponse>() {
            @Override
            public void onChanged(ProfilesResponse profilesResponse) {
                progressBar.setVisibility(View.GONE);
                if (profilesResponse != null && profilesResponse.getMeta().isSuccess() && profilesResponse.getMeta().getCode()==200 &&
                    profilesResponse.getResult() != null && profilesResponse.getResult().size()>0) {
                    insertIntoDB(profilesResponse.getResult());
                }
            }
        });
    }

    private void insertIntoDB(ArrayList<ResultItem> profileNames){
        for(ResultItem resultItem : profileNames) {
            resultRepository.insert(new ResultItem(resultItem.getWebsite(),resultItem.getAddress(),resultItem.getGender(),
                                    resultItem.getPhone(),resultItem.getDob(),resultItem.getLastName(),
                                    resultItem.getId(),resultItem.getFirstName(),resultItem.getEmail(),resultItem.getStatus(),resultItem.getLinks()));
        }
        displayList();
    }

    private void setProfileNames(ArrayList<ResultItem> profileNames) {
        ProfileNamesAdapter profileNamesListAdapter = new ProfileNamesAdapter(getApplicationContext(), profileNames);
        profileNamesRecyclerView.setHasFixedSize(true);
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        profileNamesRecyclerView.setLayoutManager(verticalLayoutManager);
        profileNamesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        profileNamesRecyclerView.setAdapter(profileNamesListAdapter);

        profileNamesRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItem_Count = verticalLayoutManager.getChildCount();
                totalItem_Count = verticalLayoutManager.getItemCount();
                pastVisibleItems = verticalLayoutManager.findFirstVisibleItemPosition();

                if(dy>0){
                    if(isLoading){
                        if(totalItem_Count > previous_total){
                            isLoading = false;
                            previous_total = totalItem_Count;
                        }
                    }
                    if(!isLoading && (totalItem_Count - visibleItem_Count) <= (pastVisibleItems + viewThreshold)){
                        page_number++;
                        if(Util.isNetworkAvailable(getApplicationContext())) {
                            callApi(page_number);
                        }
                        isLoading = true;

                    }
                }
            }
        });
    }

    @Override
    public void apiFailureResponse(String message) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
