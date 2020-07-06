package com.example.demoproject.model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataTypeConverter {

    @TypeConverter
    public static Links fromString(String value) {
        Type listType = new TypeToken<Links>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(Links list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }


}
