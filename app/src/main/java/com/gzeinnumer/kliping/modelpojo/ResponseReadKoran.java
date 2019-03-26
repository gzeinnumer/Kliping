package com.gzeinnumer.kliping.modelpojo;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseReadKoran{

	@SerializedName("result")
	private List<ResultItemKoran> result;

	@SerializedName("kode")
	private int kode;

	public void setResult(List<ResultItemKoran> result){
		this.result = result;
	}

	public List<ResultItemKoran> getResult(){
		return result;
	}

	public void setKode(int kode){
		this.kode = kode;
	}

	public int getKode(){
		return kode;
	}
}