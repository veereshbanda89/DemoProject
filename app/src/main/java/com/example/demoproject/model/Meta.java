package com.example.demoproject.model;

import com.google.gson.annotations.SerializedName;

public class Meta{

	@SerializedName("pageCount")
	private int pageCount;

	@SerializedName("code")
	private int code;

	@SerializedName("perPage")
	private int perPage;

	@SerializedName("rateLimit")
	private RateLimit rateLimit;

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private String message;

	@SerializedName("totalCount")
	private int totalCount;

	@SerializedName("currentPage")
	private int currentPage;

	public void setPageCount(int pageCount){
		this.pageCount = pageCount;
	}

	public int getPageCount(){
		return pageCount;
	}

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setPerPage(int perPage){
		this.perPage = perPage;
	}

	public int getPerPage(){
		return perPage;
	}

	public void setRateLimit(RateLimit rateLimit){
		this.rateLimit = rateLimit;
	}

	public RateLimit getRateLimit(){
		return rateLimit;
	}

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setTotalCount(int totalCount){
		this.totalCount = totalCount;
	}

	public int getTotalCount(){
		return totalCount;
	}

	public void setCurrentPage(int currentPage){
		this.currentPage = currentPage;
	}

	public int getCurrentPage(){
		return currentPage;
	}
}