package com.gzeinnumer.kliping.modelpojo;

import com.google.gson.annotations.SerializedName;

public class ResponsePageUpload{

	@SerializedName("success")
	private boolean success;

	@SerializedName("page_name")
	private String pageName;

	@SerializedName("hal_page")
	private String halPage;

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	public void setPageName(String pageName){
		this.pageName = pageName;
	}

	public String getPageName(){
		return pageName;
	}

	public void setHalPage(String halPage){
		this.halPage = halPage;
	}

	public String getHalPage(){
		return halPage;
	}
}