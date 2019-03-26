package com.gzeinnumer.kliping.network;

import com.gzeinnumer.kliping.model.ItemNewKoran;
import com.gzeinnumer.kliping.modelpojo.ResponseNewKoran;
import com.gzeinnumer.kliping.modelpojo.ResponsePageUpload;
import com.gzeinnumer.kliping.modelpojo.ResponseReadKoran;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {
    @FormUrlEncoded
    @POST("insertkoran.php")
    Call<ResponseNewKoran> newKoran (@Field("koran_nama") String koran_nama,
                                     @Field("koran_tanggal") String koran_tanggal,
                                     @Field("koran_jum_hal") int koran_jum_hal);

    @Multipart
    @POST("uploadkoran.php")
    Call<ResponsePageUpload> uploadKoranHalaman (@Part("koran_nama") RequestBody koran_nama,
                                          @Part("koran_tanggal") RequestBody koran_tanggal,
                                          @Part("koran_jum_hal") RequestBody koran_jum_hal,
                                          @Part("hal_page") RequestBody hal_page,
                                          @Part MultipartBody.Part image);

    @FormUrlEncoded
    @POST("readkoran.php")
    Call<ResponseReadKoran> readKoran(@Field("koran_tanggal") String koran_tanggal);
}
