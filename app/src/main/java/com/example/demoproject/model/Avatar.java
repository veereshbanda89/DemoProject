package com.example.demoproject.model;

import com.google.gson.annotations.SerializedName;

public class Avatar{

	@SerializedName("href")
	private String href;

	public void setHref(String href){
		this.href = href;
	}

	public String getHref(){
		return href;
	}
}