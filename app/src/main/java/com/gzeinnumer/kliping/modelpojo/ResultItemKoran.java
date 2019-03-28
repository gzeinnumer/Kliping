package com.gzeinnumer.kliping.modelpojo;

import com.google.gson.annotations.SerializedName;

public class ResultItemKoran {

	@SerializedName("koran_jum_hal")
	private String koranJumHal;

	@SerializedName("koran_id")
	private String koranId;

	@SerializedName("koran_tanggal")
	private String koranTanggal;

	@SerializedName("koran_nama")
	private String koranNama;

	public ResultItemKoran(String koranJumHal, String koranId, String koranTanggal, String koranNama) {
		this.koranJumHal = koranJumHal;
		this.koranId = koranId;
		this.koranTanggal = koranTanggal;
		this.koranNama = koranNama;
	}

	public void setKoranJumHal(String koranJumHal){
		this.koranJumHal = koranJumHal;
	}

	public String getKoranJumHal(){
		return koranJumHal;
	}

	public void setKoranId(String koranId){
		this.koranId = koranId;
	}

	public String getKoranId(){
		return koranId;
	}

	public void setKoranTanggal(String koranTanggal){
		this.koranTanggal = koranTanggal;
	}

	public String getKoranTanggal(){
		return koranTanggal;
	}

	public void setKoranNama(String koranNama){
		this.koranNama = koranNama;
	}

	public String getKoranNama(){
		return koranNama;
	}
}