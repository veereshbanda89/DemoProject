package com.example.demoproject.roomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.demoproject.model.ResultItem;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ResultItemDao {
    @Query("SELECT * FROM resultItem ORDER BY id ASC"  )
    LiveData<List<ResultItem>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ResultItem resultItem);
}
