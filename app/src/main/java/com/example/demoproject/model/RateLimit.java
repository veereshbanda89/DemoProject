package com.example.demoproject.model;

import com.google.gson.annotations.SerializedName;

public class RateLimit{

	@SerializedName("limit")
	private int limit;

	@SerializedName("reset")
	private int reset;

	@SerializedName("remaining")
	private int remaining;

	public void setLimit(int limit){
		this.limit = limit;
	}

	public int getLimit(){
		return limit;
	}

	public void setReset(int reset){
		this.reset = reset;
	}

	public int getReset(){
		return reset;
	}

	public void setRemaining(int remaining){
		this.remaining = remaining;
	}

	public int getRemaining(){
		return remaining;
	}
}