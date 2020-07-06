package com.example.demoproject.model;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

public class Links{

	@SerializedName("edit")
	private Edit edit;

	@SerializedName("self")
	private Self self;

	@SerializedName("avatar")
	private Avatar avatar;

	public void setEdit(Edit edit){
		this.edit = edit;
	}

	public Edit getEdit(){
		return edit;
	}

	public void setSelf(Self self){
		this.self = self;
	}

	public Self getSelf(){
		return self;
	}

	public void setAvatar(Avatar avatar){
		this.avatar = avatar;
	}

	public Avatar getAvatar(){
		return avatar;
	}
}