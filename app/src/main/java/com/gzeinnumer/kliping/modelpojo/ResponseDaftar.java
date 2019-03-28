package com.gzeinnumer.kliping.modelpojo;

import com.google.gson.annotations.SerializedName;

public class ResponseDaftar{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("user_email")
	private String userEmail;

	@SerializedName("kode")
	private int kode;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setUserEmail(String userEmail){
		this.userEmail = userEmail;
	}

	public String getUserEmail(){
		return userEmail;
	}

	public void setKode(int kode){
		this.kode = kode;
	}

	public int getKode(){
		return kode;
	}
}