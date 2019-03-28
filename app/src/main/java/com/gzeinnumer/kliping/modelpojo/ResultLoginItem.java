package com.gzeinnumer.kliping.modelpojo;

import com.google.gson.annotations.SerializedName;

public class ResultLoginItem{

	@SerializedName("user_email")
	private String userEmail;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("user_name")
	private String userName;

	@SerializedName("user_pass")
	private String userPass;

	@SerializedName("status")
	private String status;

	public void setUserEmail(String userEmail){
		this.userEmail = userEmail;
	}

	public String getUserEmail(){
		return userEmail;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setUserPass(String userPass){
		this.userPass = userPass;
	}

	public String getUserPass(){
		return userPass;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}