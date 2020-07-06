package com.example.demoproject.model;

import androidx.room.Entity;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ProfilesResponse{

	@SerializedName("result")
	private ArrayList<ResultItem> result;

	@SerializedName("_meta")
	private Meta meta;

	public void setResult(ArrayList<ResultItem> result){
		this.result = result;
	}

	public ArrayList<ResultItem> getResult(){
		return result;
	}

	public void setMeta(Meta meta){
		this.meta = meta;
	}

	public Meta getMeta(){
		return meta;
	}
}