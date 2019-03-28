package com.gzeinnumer.kliping.modelpojo;

import com.google.gson.annotations.SerializedName;

public class ResultItemPage {

	@SerializedName("nama_page")
	private String namaPage;

	@SerializedName("hal_page")
	private String halPage;

	@SerializedName("koran_id")
	private String koranId;

	public ResultItemPage(String namaPage, String halPage, String koranId) {
		this.namaPage = namaPage;
		this.halPage = halPage;
		this.koranId = koranId;
	}

	public void setNamaPage(String namaPage){
		this.namaPage = namaPage;
	}

	public String getNamaPage(){
		return namaPage;
	}

	public void setHalPage(String halPage){
		this.halPage = halPage;
	}

	public String getHalPage(){
		return halPage;
	}

	public void setKoranId(String koranId){
		this.koranId = koranId;
	}

	public String getKoranId(){
		return koranId;
	}
}