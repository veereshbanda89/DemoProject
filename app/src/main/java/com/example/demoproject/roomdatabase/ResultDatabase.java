package com.example.demoproject.roomdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.demoproject.model.DataTypeConverter;
import com.example.demoproject.model.ResultItem;

@Database(entities = { ResultItem.class }, version = 1, exportSchema = false)

public abstract class ResultDatabase extends RoomDatabase {

    public abstract ResultItemDao getResultItemDao();

    private static ResultDatabase resultDB;

    public static ResultDatabase getInstance(Context context) {
        if (null == resultDB) {
            resultDB = buildDatabaseInstance(context);
        }
        return resultDB;
    }

    private static ResultDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                ResultDatabase.class,
                "result_database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries().build();
    }

    public void cleanUp(){
        resultDB = null;
    }

}
