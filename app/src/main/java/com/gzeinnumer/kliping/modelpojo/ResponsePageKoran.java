package com.gzeinnumer.kliping.modelpojo;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponsePageKoran{

	@SerializedName("result")
	private List<ResultItemPage> result;

	@SerializedName("kode")
	private int kode;

	public void setResult(List<ResultItemPage> result){
		this.result = result;
	}

	public List<ResultItemPage> getResult(){
		return result;
	}

	public void setKode(int kode){
		this.kode = kode;
	}

	public int getKode(){
		return kode;
	}
}