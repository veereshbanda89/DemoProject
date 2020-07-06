package com.example.demoproject.roomdatabase;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.demoproject.model.ResultItem;

import java.util.ArrayList;
import java.util.List;

public class ResultRepository {

    private final ResultItemDao resultItemDao;
    private final LiveData<List<ResultItem>> resultItemList;
    private static final String TAG = "ResultRepository";

    public ResultRepository(Application application) {
        ResultDatabase resultDatabase = ResultDatabase.getInstance(application);
        resultItemDao = resultDatabase.getResultItemDao();
        resultItemList = resultItemDao.getAll();
    }

    public LiveData<List<ResultItem>> getResultItemList() {
        return resultItemList;
    }

    public void insert(ResultItem resultItem) {
        new insertAsyncTask(resultItemDao).execute(resultItem);
        Log.d(TAG, "success " + resultItem.getFirstName() + " " + resultItem.getLastName());
    }

    private static class insertAsyncTask extends AsyncTask<ResultItem,Void,Void> {
        private final ResultItemDao mAsyncTaskDao;

        public insertAsyncTask(ResultItemDao resultItemDao) {
            mAsyncTaskDao = resultItemDao;
        }

        @Override
        protected Void doInBackground(ResultItem... resultItems) {
            mAsyncTaskDao.insert(resultItems[0]);
            return null;
        }
    }
}
