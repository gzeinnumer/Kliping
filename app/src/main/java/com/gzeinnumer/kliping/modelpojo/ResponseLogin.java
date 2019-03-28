package com.gzeinnumer.kliping.modelpojo;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseLogin{

	@SerializedName("kode")
	private int kode;

	@SerializedName("result_login")
	private List<ResultLoginItem> resultLogin;

	public void setKode(int kode){
		this.kode = kode;
	}

	public int getKode(){
		return kode;
	}

	public void setResultLogin(List<ResultLoginItem> resultLogin){
		this.resultLogin = resultLogin;
	}

	public List<ResultLoginItem> getResultLogin(){
		return resultLogin;
	}
}